package com.accounting.utils;

import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 12191 on 2018/1/26.
 */

public class HttpUtil {

    private static final String BASE_URL = "http://www.jxieyang.cn/";

    private Map<Class, Object> apis = new ConcurrentHashMap<>();

    private Retrofit retrofit;

    private static class SingleHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    public static synchronized HttpUtil getInstance() {
        return SingleHolder.INSTANCE;
    }

    private HttpUtil() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(15, TimeUnit.SECONDS);
        client.readTimeout(20, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("renk", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), "file");

        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                okhttp3.Response originalResponse = chain.proceed(request);
                return originalResponse.newBuilder().header("mobileFlag", "adfsaeefe").addHeader("type", "4").build();
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }
    public <T> T createApi(Class<T> service){
        if (apis.containsKey(service)){
            T instance= retrofit.create(service);
            apis.put(service,instance);
        }
        return (T) apis.get(service);
    }
}
