package com.accounting.impl;

import com.accounting.entity.BaseEntity;
import com.accounting.entity.LoginEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 12191 on 2018/1/26.
 */

public interface LoginApi {
    @GET("admin/Basic/appValidate")
    Observable<BaseEntity<LoginEntity>> loginAppRequest(@Query("app_name")String name, @Query("app_encrypt_key")String password);
}
