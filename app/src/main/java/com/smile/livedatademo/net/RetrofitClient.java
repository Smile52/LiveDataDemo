package com.smile.livedatademo.net;

import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.smile.livedatademo.MyApplication;
import com.smile.livedatademo.constant.NetConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String TAG="client";
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    public static ApiStore getInstance(){
        if (okHttpClient==null){

            ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.getContext()));

            okHttpClient=new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor(msg-> {
                        //访问网络请求，和服务端响应请求时。将数据拦截并输出
                            Log.e(TAG, "log: " + msg);

                    }).setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(NetConstant.TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(NetConstant.TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(NetConstant.TIME_OUT, TimeUnit.SECONDS)
                    .cookieJar(cookieJar)
                    .build();
        }  if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(NetConstant.BASE_URL)         //BaseUrl
                    .client(okHttpClient)                       //请求的网络框架
                    .addConverterFactory(GsonConverterFactory.create())     //解析数据格式
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 使用RxJava作为回调适配器
                    .build();
        }

        return retrofit.create(ApiStore.class);
    }
}
