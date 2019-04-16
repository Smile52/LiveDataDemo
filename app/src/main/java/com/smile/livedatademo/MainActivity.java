package com.smile.livedatademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.util.Log;

import com.smile.livedatademo.base.BaseResult;
import com.smile.livedatademo.entity.UserEntity;
import com.smile.livedatademo.model.UserModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.login_user_num_et)
    AppCompatEditText mPhoneText;
    @BindView(R.id.login_pwd_num_et)
    AppCompatEditText mPwdText;


    private UserModel mModel;
    Observer<BaseResult<UserEntity>> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mModel=ViewModelProviders.of(this).get(UserModel.class);


       observer =new Observer<BaseResult<UserEntity>>() {
            @Override
            public void onChanged(BaseResult<UserEntity> userEntityBaseResult) {
                Log.e("dandy","success ");
                mModel.insertUser(userEntityBaseResult.getData());

            }
        };
        mModel.mLoginRepository.getUserByLiveData().observe(this, new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity entity) {
                Log.e("dandy","数据库中读取出来的 "+entity.toString());
            }
        });

    }

    @OnClick(R.id.login_submit_btn)
    public void onLogin(){

        mModel.login(mPhoneText.getText().toString(), mPwdText.getText().toString());
        Log.e("dandy","eee");

        mModel.getUserLiveData().observe(this, observer);


    }

    @OnClick(R.id.login_read_btn)
    public void read(){

    }
}
