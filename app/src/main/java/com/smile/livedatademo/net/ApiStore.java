package com.smile.livedatademo.net;

import com.smile.livedatademo.base.BaseResult;
import com.smile.livedatademo.entity.UserEntity;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * api商店
 */
public interface ApiStore {

    @POST("user/login")
    Flowable<BaseResult<UserEntity>> login(@Body RequestBody body);
}
