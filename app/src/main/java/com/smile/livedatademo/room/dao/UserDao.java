package com.smile.livedatademo.room.dao;

import com.smile.livedatademo.entity.UserEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Insert
    void insert(UserEntity entity);

    @Query("SELECT * FROM UserEntity WHERE id=1")
    UserEntity getUserOne();

    @Update
    void update(UserEntity entity);

    @Query("SELECT * FROM UserEntity ")
    List<UserEntity> getUser();

    @Query("SELECT * FROM UserEntity")
    Flowable<UserEntity> getUserByRxJava();

    @Query("SELECT * FROM UserEntity WHERE id=1")
    LiveData<UserEntity> getUserByLiveData();
}
