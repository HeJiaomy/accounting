package com.accounting.utils;

import android.os.Environment;

/**
 * Created by 12191 on 2018/1/29.
 */

public class MyConstant {
    public static String getDbPath(){
        return Environment.getExternalStorageDirectory().getAbsolutePath()+"/renk";
    }

    public static final String DB_NAME= "RENKCHINA_DB.db";
}
