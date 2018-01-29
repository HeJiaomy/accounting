package com.accounting.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12191 on 2018/1/12.
 */

public class MyApplication extends Application {

    private Context mContext;
    private static MyApplication myApplication;
    private List<Activity> activityList= new ArrayList<>();

    public String name;
    public String password;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext= getApplicationContext();
        myApplication= this;
    }

    public static MyApplication getInstance(){
        return myApplication;
    }

    public Context getContext(){
        return mContext;
    }

    public void addActivity(Activity activity){
        if (activity!= null && !activityList.contains(activity)){
            activityList.add(activity);
        }
    }

    public void clearActivity(){
        for (Activity activity : activityList) {
            if (activity!= null && !activity.isFinishing()){
                activity.finish();
            }
        }
    }

    public void exit(){
        clearActivity();
        System.exit(0);
        Process.killProcess(Process.myPid());
    }
}
