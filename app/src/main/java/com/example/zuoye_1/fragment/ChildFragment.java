package com.example.zuoye_1.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zuoye_1.HomeActivity;
import com.example.zuoye_1.R;
import com.example.zuoye_1.adapter.ChildRecAdapter;
import com.example.zuoye_1.bean.MainBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ChildFragment extends Fragment {

    @BindView(R.id.childIv)
    ImageView childIv;
    @BindView(R.id.childIvT)
    TextView childIvT;
    @BindView(R.id.childTv)
    TextView childTv;
    @BindView(R.id.childRec)
    RecyclerView rec;
    private MainBean.DataBean.CategoryListBean categoryListBean;
    private Unbinder unbinder;
    private View view;



    public ChildFragment(MainBean.DataBean.CategoryListBean categoryListBean) {
        this.categoryListBean = categoryListBean;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        String banner_url = categoryListBean.getWap_banner_url();
        String front_desc = categoryListBean.getFront_desc();
        String name = categoryListBean.getName();
        final List<MainBean.DataBean.CategoryListBean.SubCategoryListBeanX> subCategoryList = categoryListBean.getSubCategoryList();
        Glide.with(getActivity()).load(banner_url).into(childIv);
        childIvT.setText(front_desc);
        childTv.setText("-" + name + "分类-");

        rec.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        ChildRecAdapter adapter = new ChildRecAdapter(subCategoryList, getActivity());
        rec.setAdapter(adapter);

        adapter.setItemOnClick(new ChildRecAdapter.ItemOnClick() {
            @Override
            public void itemOnClick(int position) {
                int id = subCategoryList.get(position).getId();
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
