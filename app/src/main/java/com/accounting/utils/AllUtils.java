package com.accounting.utils;

import android.util.Log;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by 12191 on 2018/1/29.
 */

public class AllUtils {

    private static final String TAG="renk";

    public static boolean isEmpty(List list){
        return list== null || list.size()<1;
    }

    public static void renk(Object object) {
        StackTraceElement element = new Throwable().getStackTrace()[1];
        StringBuilder builder = new StringBuilder();
        builder.append("fileName:");
        builder.append(element.getFileName());
        builder.append(", ");
        builder.append("Line:");
        builder.append(element.getLineNumber());
        Log.e(TAG, builder.toString());
        if (object != null)
            Log.e(TAG, object.toString());
        else
            Log.e(TAG, "打印为空");
    }
}
