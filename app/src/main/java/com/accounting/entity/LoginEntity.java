package com.accounting.entity;

/**
 * Created by 12191 on 2018/1/26.
 */

public class LoginEntity {

    private int error;
    private String error_msg;
    private String app_describe;
    private String app_start_time;
    private String app_end_time;
    private int row;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getApp_describe() {
        return app_describe;
    }

    public void setApp_describe(String app_describe) {
        this.app_describe = app_describe;
    }

    public String getApp_start_time() {
        return app_start_time;
    }

    public void setApp_start_time(String app_start_time) {
        this.app_start_time = app_start_time;
    }

    public String getApp_end_time() {
        return app_end_time;
    }

    public void setApp_end_time(String app_end_time) {
        this.app_end_time = app_end_time;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "error=" + error +
                ", error_msg='" + error_msg + '\'' +
                ", app_describe='" + app_describe + '\'' +
                ", app_start_time='" + app_start_time + '\'' +
                ", app_end_time='" + app_end_time + '\'' +
                ", row=" + row +
                '}';
    }
}
