package com.example.zuoye_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.example.zuoye_1.adapter.VpAdapter;
import com.example.zuoye_1.bean.HomeTabBean;
import com.example.zuoye_1.fragment.HomeChildFragment;
import com.example.zuoye_1.mvp.HomeTabPresenter;
import com.example.zuoye_1.mvp.HomeTabView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeTabView {

    @BindView(R.id.homeTab)
    TabLayout homeTab;
    @BindView(R.id.homeVp)
    ViewPager homeVp;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        initData();
    }

    private void initData() {
        HomeTabPresenter presenter = new HomeTabPresenter();
        presenter.setView(this);
        presenter.data(id);
    }

    @Override
    public void onSuccess(HomeTabBean homeTabBean) {
        List<HomeTabBean.DataBean.BrotherCategoryBean> brotherCategory = homeTabBean.getData().getBrotherCategory();
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<brotherCategory.size();i++){
            String name = brotherCategory.get(i).getName();
            titles.add(name);
            fragments.add(new HomeChildFragment(brotherCategory.get(i).getId()));
        }
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments, titles);
        homeVp.setAdapter(adapter);
        homeTab.setupWithViewPager(homeVp);
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
