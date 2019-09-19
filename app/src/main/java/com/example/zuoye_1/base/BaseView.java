package com.example.zuoye_1.base;

public interface BaseView<D,S> {
    void onSuccess(D d);
    void onFail(S s);

}
