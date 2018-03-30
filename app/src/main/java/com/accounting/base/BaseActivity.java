package com.accounting.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.accounting.R;

/**
 * Created by 12191 on 2018/1/12.
 */

public abstract class BaseActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ActionBar actionBar;
    ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getProviderLayoutId());
        MyApplication.getInstance().addActivity(this);
        mToolbar= findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        actionBar= getSupportActionBar();
        //第二种写法①
        if (actionBar!= null){
            actionBar.setDisplayHomeAsUpEnabled(true);//让导航按钮显示出来
//            actionBar.setHomeAsUpIndicator(R.mipmap.ic_back);
        }
        //第一种写法
//        mToolbar.setNavigationIcon(R.mipmap.ic_back);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    finish();
//            }
//        });
        init();
    }

    //第二种写法②对HomeAsUp按钮的点击事件进行处理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    /**
     * 初始化控件
     */
    public abstract void init();

    /**
     * 加载布局
     * @return id
     */
    public abstract int getProviderLayoutId();

    /**
     * 设置title
     */
    public void setTitle(String title){
        if (!TextUtils.isEmpty(title)){
            actionBar.setTitle(title);
        }
    }

    public void toast(Object object){
        if (object== null){
            return;
        }
        if (TextUtils.isEmpty(object.toString())){
            Toast.makeText(this," ",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,object.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * activity跳转
     */
    public void gotoActivity(Class goClass,boolean isFinish,Bundle bundle){
        Intent intent= new Intent(this,goClass);
        if (bundle!= null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isFinish){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showProgressDialog(String title,String msg,boolean cancel){
        if (pd!= null){
            pd= ProgressDialog.show(this,title,msg);
        }else{
            pd.setTitle(title);
            pd.setMessage(msg);
        }
        pd.setCancelable(cancel);
        pd.setCanceledOnTouchOutside(cancel);
        if (!pd.isShowing()){
            pd.show();
        }
    }

    public void dismissProgressDialog(){
        if (pd!= null){
            if (pd.isShowing()){
                pd.dismiss();
            }
        }
    }
}
