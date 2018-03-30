package com.accounting.entity;

/**
 * Created by Administrator on 2018/3/16.
 */

public class UpdateDbFileResult {
    private String upload_save;
    private int error;
    private int row;

    public String getUpload_save() {
        return upload_save;
    }

    public void setUpload_save(String upload_save) {
        this.upload_save = upload_save;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "UpdateDbFileResult{" +
                "upload_save='" + upload_save + '\'' +
                ", error=" + error +
                ", row=" + row +
                '}';
    }
}
