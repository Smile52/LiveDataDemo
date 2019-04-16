package com.smile.livedatademo.repository;

import com.smile.livedatademo.base.BaseResult;
import com.smile.livedatademo.entity.UserEntity;
import com.smile.livedatademo.params.LoginParams;

import androidx.lifecycle.LiveData;

public interface ILoginRepository {
    LiveData<BaseResult<UserEntity>> login(LoginParams params);
}
