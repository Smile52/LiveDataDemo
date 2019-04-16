package com.smile.livedatademo.model;

import android.util.Log;

import com.smile.livedatademo.MyApplication;
import com.smile.livedatademo.base.BaseResult;
import com.smile.livedatademo.entity.UserEntity;
import com.smile.livedatademo.params.LoginParams;
import com.smile.livedatademo.repository.ILoginRepository;
import com.smile.livedatademo.repository.LoginRepository;

import org.reactivestreams.Subscription;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserModel extends ViewModel {

   // private LiveData<BaseResult<UserEntity>> mUserLiveData;
    public LoginRepository mLoginRepository;

    private MutableLiveData<BaseResult<UserEntity>> mutableLiveData;
    private MutableLiveData<UserEntity> userEntityMutableLiveData;

    public UserModel(){
        mLoginRepository=new LoginRepository();
        userEntityMutableLiveData=new MutableLiveData<>();


    }

    public LiveData<BaseResult<UserEntity>> getUserLiveData(){
        return mutableLiveData;
    }

    public LiveData<UserEntity> getUserEntityData(){
        return userEntityMutableLiveData;
    }


    public void login(String phone, String pwd){
        LoginParams params=new LoginParams();
        params.setPhone(phone);
        params.setPassword(pwd);

        this.mutableLiveData=mLoginRepository.login(params);

    }

    public void insertUser(UserEntity userEntity){
        MyApplication.getAppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                userEntity.setId(1);

                UserEntity userOne = mLoginRepository.getUserOne();
                if (userOne==null){
                    mLoginRepository.insertUser(userEntity);

                }else {
                    mLoginRepository.updateUser(userEntity);

                }
                Thread th=Thread.currentThread();
                Log.e("dandy","当前线程 "+th.getId());
               // insertUser(userEntity);


            }
        });
    }

    public void read(){

/*
        mLoginRepository.getUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(entity -> {
                    Log.e("dandy", "数据库读取出来 " + entity.toString());
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("dandy","ee "+throwable.toString());
                    }
                });*/

    }
}
