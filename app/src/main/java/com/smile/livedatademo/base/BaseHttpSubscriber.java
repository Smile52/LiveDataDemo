package com.smile.livedatademo.base;


import com.smile.livedatademo.constant.NetConstant;
import com.smile.livedatademo.exception.ApiException;
import com.smile.livedatademo.exception.ExceptionEngine;
import com.smile.livedatademo.exception.ServerException;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import androidx.lifecycle.MutableLiveData;

public class BaseHttpSubscriber<T> implements Subscriber<BaseResult<T>> {

    private ApiException apiException;

    private MutableLiveData<BaseResult<T>> data;

    public BaseHttpSubscriber(){
        data= new MutableLiveData();
    }

    public MutableLiveData<BaseResult<T>> get(){
        return data;
    }



    public void set(BaseResult<T> d){
        data.setValue(d);
    }



    @Override
    public void onSubscribe(Subscription s) {
            s.request(1);
    }

    @Override
    public void onNext(BaseResult<T> tBaseResult) {
        if (tBaseResult.getCode()== NetConstant.SUCCESS_CODE){
            onFinish(tBaseResult);
        }else {
            apiException= ExceptionEngine.handleException(new ServerException(tBaseResult.getCode(),tBaseResult.getMessage()));
            getErrorResult(apiException);
        }
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }

    public void onFinish(BaseResult<T> t){
        set(t);
    }

    private void getErrorResult(ApiException e){
        BaseResult baseResult=new BaseResult();
        baseResult.setCode(e.getCode());
        baseResult.setMessage(e.getMessage());
        onFinish(baseResult);
    }
}
