package com.example.zuoye_1.mvp;

import com.example.zuoye_1.base.BaseCallback;
import com.example.zuoye_1.base.BaseModel;
import com.example.zuoye_1.bean.HomeTabBean;

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

public class HomeTabModel extends BaseModel {
    public void data(final BaseCallback<HomeTabBean, String> callback, int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeTabApi.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Observable<HomeTabBean> observable = retrofit.create(HomeTabApi.class).get(id);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HomeTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeTabBean homeTabBean) {
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

    interface HomeTabApi {
        String baseUrl = "https://cdwan.cn/";

        @GET("api/goods/category")
        Observable<HomeTabBean> get(@Query("id") int id);
    }
}
