package com.example.zuoye_1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zuoye_1.R;
import com.example.zuoye_1.bean.MainBean;


import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

public class ChildRecAdapter extends RecyclerView.Adapter<ChildRecAdapter.ViewHolder> {
    private List<MainBean.DataBean.CategoryListBean.SubCategoryListBeanX> list=new ArrayList<>();
    private Context context;
    private ItemOnClick itemOnClick;

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public ChildRecAdapter(List<MainBean.DataBean.CategoryListBean.SubCategoryListBeanX> subCategoryList, Context context) {

        this.list = subCategoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_child, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemTv.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getWap_banner_url()).into(holder.itemIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOnClick!=null){
                    itemOnClick.itemOnClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemIv)
        ImageView itemIv;
        @BindView(R.id.itemTv)
        TextView itemTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface ItemOnClick{
        void itemOnClick(int position);
    }
}
