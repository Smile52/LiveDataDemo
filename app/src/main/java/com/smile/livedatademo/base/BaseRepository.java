package com.smile.livedatademo.base;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseRepository<T> {
    private BaseHttpSubscriber<T> baseHttpSubscriber;
    private Flowable<BaseResult<T>> flowable;


    public BaseHttpSubscriber<T> send(){
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseHttpSubscriber);
        return baseHttpSubscriber;

    }

    /**
     * 初始化
     * commonHttpSubscriber = new Common
     */
    public BaseRepository() {
        baseHttpSubscriber = new BaseHttpSubscriber<>();
    }

    public BaseRepository<T> request(Flowable<BaseResult<T>> flowable) {
        this.flowable = flowable;
        return this;
    }


}
