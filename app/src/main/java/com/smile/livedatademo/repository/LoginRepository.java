package com.smile.livedatademo.repository;

import com.smile.livedatademo.base.BaseRepository;
import com.smile.livedatademo.base.BaseResult;
import com.smile.livedatademo.entity.UserEntity;
import com.smile.livedatademo.net.ApiManager;
import com.smile.livedatademo.params.LoginParams;
import com.smile.livedatademo.room.UserDataBases;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Flowable;

public class LoginRepository extends BaseRepository<UserEntity> implements ILoginRepository {

    private UserDataBases mDatabases;

    public LoginRepository() {
        mDatabases=UserDataBases.getInstance();
    }

    @Override
    public MutableLiveData<BaseResult<UserEntity>> login(LoginParams params) {
        return request(ApiManager.login(params)).send().get();
    }

    public void insertUser(UserEntity entity){
        mDatabases.userDao().insert(entity);
    }

    public Flowable<UserEntity> getUser(){
        return  mDatabases.userDao().getUserByRxJava();
    }

    public List<UserEntity> getUserNormal(){
        return mDatabases.userDao().getUser();
    }



    public void updateUser(UserEntity entity){
        mDatabases.userDao().update(entity);

    }

    public UserEntity getUserOne(){
        return mDatabases.userDao().getUserOne();
    }

    public LiveData<UserEntity>getUserByLiveData(){
        return mDatabases.userDao().getUserByLiveData();
    }



}
