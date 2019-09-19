package com.example.zuoye_1.mvp;


import com.example.zuoye_1.base.BaseCallback;
import com.example.zuoye_1.base.BasePresenter;
import com.example.zuoye_1.bean.HomeChlidBean;

public class HomeChildPresenter extends BasePresenter<HomeChildModel, HomeChlidView> implements BaseCallback<HomeChlidBean, String> {

    private HomeChildModel model;

    @Override
    protected void initModel() {
        model = new HomeChildModel();
    }
    public void data(int id){
        model.data(this,id);
    }

    @Override
    public void onSuccess(HomeChlidBean homeTabBean) {
        if(view!=null){
            view.onSuccess(homeTabBean);
        }
    }

    @Override
    public void onFail(String s) {
        if(view!=null){
            view.onFail(s);
        }
    }
}
