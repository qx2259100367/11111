package com.example.zuoye_1.mvp;


import com.example.zuoye_1.base.BaseCallback;
import com.example.zuoye_1.base.BaseModel;
import com.example.zuoye_1.bean.MainBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainModel extends BaseModel {
    public void data(final BaseCallback<MainBean,String> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MainApi.baseUrl)
                .build();
        MainApi mainApi = retrofit.create(MainApi.class);
        Observable<MainBean> observable = mainApi.get();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MainBean mainBean) {
                        callback.onSuccess(mainBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail("请求错误"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    interface MainApi{
        String baseUrl="https://cdwan.cn/";
        @GET("api/catalog/index")
        Observable<MainBean> get();
    }
}
