package com.example.zuoye_1.base;

public abstract class BasePresenter<M extends BaseModel,V extends BaseView> {
    public V view;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void setView(V view) {
        this.view = view;
    }
}
