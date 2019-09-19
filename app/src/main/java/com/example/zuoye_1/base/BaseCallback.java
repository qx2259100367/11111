package com.example.zuoye_1.base;

public interface BaseCallback<D,S> {
    void onSuccess(D d);
    void onFail(S s);

}
