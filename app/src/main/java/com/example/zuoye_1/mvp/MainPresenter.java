package com.example.zuoye_1.mvp;

import com.example.zuoye_1.base.BaseCallback;
import com.example.zuoye_1.base.BasePresenter;
import com.example.zuoye_1.base.BaseView;
import com.example.zuoye_1.bean.MainBean;

public class MainPresenter extends BasePresenter<MainModel, BaseView<MainBean, String>> implements BaseCallback<MainBean, String> {

    private MainModel model;

    @Override
    protected void initModel() {
        model = new MainModel();
    }

    public void data() {
        model.data(this);
    }

    @Override
    public void onSuccess(MainBean mainBean) {
        if (view != null) {
            view.onSuccess(mainBean);
        }
    }

    @Override
    public void onFail(String s) {
        if (view != null) {
            view.onFail(s);
        }
    }
}
