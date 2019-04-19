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


public class UserModel extends ViewModel {

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
            }
        });
    }
}
