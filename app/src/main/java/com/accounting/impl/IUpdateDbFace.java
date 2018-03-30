package com.accounting.impl;

import com.accounting.entity.BaseEntity;
import com.accounting.entity.UpdateDbFileResult;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/3/16.
 */

public interface IUpdateDbFace {
    @Multipart
    @POST("companyEntity")
    Observable<BaseEntity<UpdateDbFileResult>> updateDbFile(@Query("app_name") String name,
                                                            @Query("app_encrypt_key") String password,
                                                            @Part MultipartBody.Part file);
}
