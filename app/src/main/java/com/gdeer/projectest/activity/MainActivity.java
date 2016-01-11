package com.gdeer.projectest.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.gdeer.projectest.R;
import com.gdeer.projectest.adapter.MyFragmentPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    @Bind(R.id.vpager)
    ViewPager vpager;
    @Bind(R.id.m_tablayout)
    TabLayout mTabLayout;
    private MyFragmentPagerAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        vpager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(vpager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mAdapter.getTabView(i, tab));
            }
        }
    }

}