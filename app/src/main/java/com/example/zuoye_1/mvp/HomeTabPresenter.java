package com.example.zuoye_1.mvp;


import com.example.zuoye_1.base.BaseCallback;
import com.example.zuoye_1.base.BasePresenter;
import com.example.zuoye_1.bean.HomeTabBean;

public class HomeTabPresenter extends BasePresenter<HomeTabModel, HomeTabView> implements BaseCallback<HomeTabBean, String> {

    private HomeTabModel model;

    @Override
    protected void initModel() {
        model = new HomeTabModel();
    }
    public void data(int id){
        model.data(this,id);
    }

    @Override
    public void onSuccess(HomeTabBean homeTabBean) {
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
