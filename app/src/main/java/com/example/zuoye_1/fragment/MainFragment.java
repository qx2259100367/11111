package com.example.zuoye_1.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zuoye_1.R;
import com.example.zuoye_1.adapter.VpAdapter;
import com.example.zuoye_1.base.BaseView;
import com.example.zuoye_1.bean.MainBean;
import com.example.zuoye_1.mvp.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class MainFragment extends Fragment implements BaseView<MainBean, String> {


    @BindView(R.id.frVtab)
    VerticalTabLayout frVtab;
    @BindView(R.id.frVp)
    ViewPager frVp;
    private Unbinder unbind;
    private View view;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        MainPresenter presenter = new MainPresenter();
        presenter.setView(this);
        presenter.data();
    }

    @Override
    public void onSuccess(MainBean mainBean) {
        List<MainBean.DataBean.CategoryListBean> categoryList = mainBean.getData().getCategoryList();
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            titles.add(categoryList.get(i).getName());
            fragments.add(new ChildFragment(categoryList.get(i)));
        }
        VpAdapter adapter = new VpAdapter(getChildFragmentManager(), fragments, titles);
        frVp.setAdapter(adapter);
        frVtab.setupWithViewPager(frVp);
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }
}
