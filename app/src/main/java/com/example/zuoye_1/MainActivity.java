package com.example.zuoye_1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.example.zuoye_1.adapter.VpAdapter;
import com.example.zuoye_1.fragment.HomeFragment;
import com.example.zuoye_1.fragment.MainFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainVp)
    ViewPager mainVp;
    @BindView(R.id.mainTab)
    TabLayout mainTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new MainFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), fragments);
        mainVp.setAdapter(vpAdapter);
        mainTab.setupWithViewPager(mainVp);
        mainTab.getTabAt(0).setCustomView(LayoutInflater.from(this).inflate(R.layout.tab_1,null));
        mainTab.getTabAt(1).setCustomView(LayoutInflater.from(this).inflate(R.layout.tab_2,null));
        mainTab.getTabAt(2).setCustomView(LayoutInflater.from(this).inflate(R.layout.tab_3,null));
        mainTab.getTabAt(3).setCustomView(LayoutInflater.from(this).inflate(R.layout.tab_4,null));
        mainTab.getTabAt(4).setCustomView(LayoutInflater.from(this).inflate(R.layout.tab_5,null));

    }
}
