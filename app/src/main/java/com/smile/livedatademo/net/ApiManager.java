package com.smile.livedatademo.net;

import com.smile.livedatademo.base.BaseResult;
import com.smile.livedatademo.entity.UserEntity;
import com.smile.livedatademo.params.LoginParams;
import com.smile.livedatademo.utils.NetUtils;

import io.reactivex.Flowable;

public class ApiManager {


    public static Flowable<BaseResult<UserEntity>> login(LoginParams params){
        return RetrofitClient.getInstance().login(NetUtils.ParamsToBody(params));
    }
}
