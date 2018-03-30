package com.accounting.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.accounting.R;
import com.accounting.base.BaseActivity;
import com.accounting.base.MyApplication;
import com.accounting.db.BillingDbHelper;
import com.accounting.db.CompanyDbHelper;
import com.accounting.entity.BaseEntity;
import com.accounting.entity.BillEntity;
import com.accounting.entity.CompanyEntity;
import com.accounting.entity.UpdateDbFileResult;
import com.accounting.exception.MyDaoException;
import com.accounting.impl.IUpdateDbFace;
import com.accounting.service.MyUsbService;
import com.accounting.utils.AllUtils;
import com.accounting.utils.HttpUtils;
import com.accounting.utils.LogUtils;
import com.accounting.utils.MyConstant;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends BaseActivity {

    @BindView(R.id.activity_main_title_tv)
    TextView mainTitleTv;
    @BindView(R.id.activity_main_tv1)
    TextView mainTv1;
    @BindView(R.id.activity_main_tv2)
    TextView mainTv2;
    @BindView(R.id.activity_main_tv3)
    TextView mainTv3;
    @BindView(R.id.activity_main_tv4)
    TextView mainTv4;
    @BindView(R.id.activity_main_tv5)
    TextView mainTv5;
    @BindView(R.id.activity_main_tv6)
    TextView mainTv6;
    //TODO mBound 什么用法？
    private boolean mBound;
    private String title;
    private CompanyEntity mCompanyEntity;
    private IUpdateDbFace iUpdateDbFace;
    private static int REQUEST_CODE = 9527;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    public int getProviderLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        Intent intent = new Intent(this, MyUsbService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
        title = getIntent().getStringExtra("title");
        iUpdateDbFace = HttpUtils.getInstance().createApi(IUpdateDbFace.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(title)) {
            try {
                mCompanyEntity = CompanyDbHelper.getInstance().queryCompany();
                if (mCompanyEntity != null) {
                    mainTitleTv.setText(mCompanyEntity.getName() + "出货记账软件");
                } else {
                    mainTitleTv.setText("出货记账软件");
                }
            } catch (MyDaoException e) {
                e.printStackTrace();
            }
        } else {
            mainTitleTv.setText(title);
        }
    }

//    private void setMainTitle(CompanyEntity mCompanyEntity) {
//        if (mCompanyEntity == null) {
//            mainTitleTv.setText("出货记账软件");
//        }else {
//            mainTitleTv.setText(mCompanyEntity.getName()+"出货记账软件");
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("MainActivity", "onDestroy执行了");
        unbindService(conn);
        if (mBound) {
            mBound = false;
        }
    }

    @OnClick({R.id.activity_main_tv1, R.id.activity_main_tv2, R.id.activity_main_tv3, R.id.activity_main_tv4, R.id.activity_main_tv5, R.id.activity_main_tv6})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.activity_main_tv1:
                intent.setClass(this, AddressActivity.class);    //开单
                intent.putExtra("isBilling", true);
                break;
            case R.id.activity_main_tv2:
                intent.setClass(this, CustomerDirectoryActivity.class);  //客户目录
                break;
            case R.id.activity_main_tv3:
                intent.setClass(this, DeliveryNoteListActivity.class);   //送货单列表
                break;
            case R.id.activity_main_tv4:
                intent.setClass(this, CompanyActivity.class);    //公司目录
                break;
            case R.id.activity_main_tv5:
                intent.setClass(this, CaptureActivity.class);    //扫描
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.activity_main_tv6:
                intent.setClass(this, AccountActivity.class);    //账目
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    BillEntity bill= BillingDbHelper.getInstance().queryBillByCode(result);
                    if(bill!= null){
                        Intent intent= new Intent(this,PrinterPreviewActivity.class);
                        intent.putExtra("bill",bill);
                        startActivity(intent);
                    }else{
                        toast("未查询到相关订单");
                    }
                }else if(bundle.getInt(CodeUtils.RESULT_TYPE)== CodeUtils.RESULT_FAILED){
                    toast("解析二维码失败");
                }
            }
        }
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long systemTime = System.currentTimeMillis();
            if (systemTime - mExitTime > 2000) {
                toast("再按一次退出程序");
                mExitTime = systemTime;
            } else {
                updateDbFile();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //上传数据
    private void updateDbFile() {
        showProgressDialog("正在上传数据", "加载中，请稍等！", false);
        File file = new File(MyConstant.getDbPath() + File.separator + MyConstant.DB_NAME);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("backup_file", file.getName(), requestBody);
        iUpdateDbFace.updateDbFile(MyApplication.getInstance().name, MyApplication.getInstance().password, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseEntity<UpdateDbFileResult>>() {
                               @Override
                               public void accept(BaseEntity<UpdateDbFileResult> updateDbFileResultBaseEntity) throws Exception {
                                   dismissProgressDialog();
                                   finish();
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                dismissProgressDialog();
                                finish();
                                AllUtils.renk(throwable.getMessage());
                            }
                        });
    }
}
