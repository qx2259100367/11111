package com.example.zuoye_1.mvp;

import com.example.zuoye_1.base.BaseCallback;
import com.example.zuoye_1.base.BaseModel;
import com.example.zuoye_1.bean.HomeChlidBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class HomeChildModel extends BaseModel {
    public void data(final BaseCallback<HomeChlidBean, String> callback, int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeChildApi.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Observable<HomeChlidBean> observable = retrofit.create(HomeChildApi.class).get(id);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HomeChlidBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeChlidBean homeTabBean) {
                        callback.onSuccess(homeTabBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail("请求失败：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    interface HomeChildApi {
        String baseUrl = "https://cdwan.cn/";
        //https://cdwan.cn/api/goods/list?categoryId=1008008&page=1&size=10000
        @GET("api/goods/list?page=1&size=10000")
        Observable<HomeChlidBean> get(@Query("categoryId") int id);
    }
}
