package com.example.zuoye_1.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zuoye_1.R;
import com.example.zuoye_1.adapter.HomeRecAdapter;
import com.example.zuoye_1.bean.HomeChlidBean;
import com.example.zuoye_1.mvp.HomeChildPresenter;
import com.example.zuoye_1.mvp.HomeChlidView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class HomeChildFragment extends Fragment implements HomeChlidView {
    @BindView(R.id.rec)
    RecyclerView rec;
    private int id;
    private Unbinder unbinder;

    public HomeChildFragment(int id) {
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_child, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        HomeChildPresenter presenter = new HomeChildPresenter();
        presenter.setView(this);
        presenter.data(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(HomeChlidBean homeChlidBean) {
        List<HomeChlidBean.DataBeanX.DataBean> data = homeChlidBean.getData().getData();
        rec.setLayoutManager(new GridLayoutManager(getActivity(),2));
        HomeRecAdapter adapter = new HomeRecAdapter(data, getActivity());
        rec.setAdapter(adapter);
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
    }
}
