package com.example.zuoye_1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zuoye_1.R;
import com.example.zuoye_1.bean.HomeChlidBean;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

public class HomeRecAdapter extends RecyclerView.Adapter<HomeRecAdapter.ViewHolder> {
    private List<HomeChlidBean.DataBeanX.DataBean> list = new ArrayList<>();
    private Context context;

    public HomeRecAdapter(List<HomeChlidBean.DataBeanX.DataBean> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_child,  parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemHomeName.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getList_pic_url()).into(holder.itemHomeIv);
        holder.itemHomePrice.setText("$"+list.get(position).getRetail_price());
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemHomeIv)
        ImageView itemHomeIv;
        @BindView(R.id.itemHomeName)
        TextView itemHomeName;
        @BindView(R.id.itemHomePrice)
        TextView itemHomePrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
