package com.accounting.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.accounting.utils.AllUtils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.List;

public class MyUsbService extends Service {

    private UsbManager mUsbManager;
    private InnerReceiver mReceiver;
    private final String USB_ACTION = "android.hardware.usb.action.USB_STATE";
    private List<UsbDevice> lists;
    private UsbDevice mUsbDevice;
    //代表USB设备的一个接口
    private UsbInterface mInterface;
    //
    private UsbDeviceConnection mDeviceConnection;
    //代表一个接口的某个节点的类:写数据节点
    private UsbEndpoint usbEpOut;
    //代表一个接口的某个节点的类:读数据节点
    private UsbEndpoint usbEpIn;
    //要发送信息字节
    private byte[] sendbytes;
    //接收到的信息字节
    private byte[] receiveytes;
    private boolean isConnected;

    private MyRunnable mRunnable;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 998:
                    String content = (String) msg.obj;
                    showTmsg(content);
                    break;
            }
        }
    };


    @Override
    public void onCreate() {
        mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        mReceiver = new InnerReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(USB_ACTION);
        registerReceiver(mReceiver, filter);
        mRunnable = new MyRunnable();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        isConnected = false;
        super.onDestroy();
    }


    public class InnerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e("renk", action);
            if (USB_ACTION.equals(action)) {
                if (intent.getExtras().getBoolean("connected")) {
                    // usb 插入
                    lists = (List<UsbDevice>) mUsbManager.getDeviceList().values();
                    if (!AllUtils.isEmpty(lists)) {
                        mUsbDevice = lists.get(0);
                        initUsbData();
                        new Thread(mRunnable);
                    } else {
                        isConnected = false;
                    }
                } else {
                    //   usb 拔出
                    isConnected = false;
                }
            }
        }
    }

    private void initUsbData() {
        //获取设备接口
        for (int i = 0; i < mUsbDevice.getInterfaceCount(); ) {
            // 一般来说一个设备都是一个接口，你可以通过getInterfaceCount()查看接口的个数
            // 这个接口上有两个端点，分别对应OUT 和 IN
            UsbInterface usbInterface = mUsbDevice.getInterface(i);
            mInterface = usbInterface;
            break;
        }
        //用UsbDeviceConnection 与 UsbInterface 进行端点设置和通讯
        if (mInterface.getEndpoint(1) != null) {
            usbEpOut = mInterface.getEndpoint(1);
        }
        if (mInterface.getEndpoint(0) != null) {
            usbEpIn = mInterface.getEndpoint(0);
        }
        if (mInterface != null) {
            // 判断是否有权限
            if (mUsbManager.hasPermission(mUsbDevice)) {
                // 打开设备，获取 UsbDeviceConnection 对象，连接设备，用于后面的通讯
                mDeviceConnection = mUsbManager.openDevice(mUsbDevice);
                isConnected = true;
                if (mDeviceConnection == null) {
                    return;
                }
                if (mDeviceConnection.claimInterface(mInterface, true)) {
                    showTmsg("找到设备接口");
                } else {
                    mDeviceConnection.close();
                }
            } else {
                showTmsg("没有权限");
            }
        } else {
            showTmsg("没有找到设备接口！");
        }
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            while (isConnected) {
                readFromUsb();
                Log.e("renk", "run");
            }
        }
    }

    private void readFromUsb() {
        //读取数据
        int outMax = usbEpOut.getMaxPacketSize();
        int inMax = usbEpIn.getMaxPacketSize();
        ByteBuffer byteBuffer = ByteBuffer.allocate(inMax);
        UsbRequest usbRequest = new UsbRequest();
        usbRequest.initialize(mDeviceConnection, usbEpIn);
        usbRequest.queue(byteBuffer, inMax);
        if (mDeviceConnection.requestWait() == usbRequest) {
            byte[] retData = byteBuffer.array();
            try {
                Message message = new Message();
                message.obj = new String(retData, "UTF-8");
                mHandler.sendMessage(message);
                Log.e("renk", message.obj.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private void showTmsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void sendToUsb(String content) {
        sendbytes = content.getBytes();
        int ret = -1;
        // 发送准备命令
        ret = mDeviceConnection.bulkTransfer(usbEpOut, sendbytes, sendbytes.length, 5000);
//        showTmsg("指令已经发送！");
        // 接收发送成功信息(相当于读取设备数据)
        receiveytes = new byte[128];   //根据设备实际情况写数据大小
        ret = mDeviceConnection.bulkTransfer(usbEpIn, receiveytes, receiveytes.length, 10000);
//        result_tv.setText(String.valueOf(ret));
        Toast.makeText(this, String.valueOf(ret), Toast.LENGTH_SHORT).show();
    }
}
