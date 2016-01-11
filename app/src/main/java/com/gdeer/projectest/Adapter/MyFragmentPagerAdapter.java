package com.gdeer.projectest.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdeer.projectest.R;
import com.gdeer.projectest.application.MyApplication;
import com.gdeer.projectest.fragmentPage.FragmentPage1;
import com.gdeer.projectest.fragmentPage.FragmentPage2;
import com.gdeer.projectest.fragmentPage.FragmentPage3;
import com.gdeer.projectest.fragmentPage.FragmentPage4;
import com.gdeer.projectest.activity.MainActivity;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private FragmentPage1 myFragment1 = null;
    private FragmentPage2 myFragment2 = null;
    private FragmentPage3 myFragment3 = null;
    private FragmentPage4 myFragment4 = null;
    private String[] tabTitle = new String[]{"首页","广场","消息","我"};
    private int[] tabIcon = {
            R.drawable.tab_icon_1,
            R.drawable.tab_icon_2,
            R.drawable.tab_icon_3,
            R.drawable.tab_icon_4};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new FragmentPage1();
        myFragment2 = new FragmentPage2();
        myFragment3 = new FragmentPage3();
        myFragment4 = new FragmentPage4();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }
    /*
    @Override
    pic_public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }*/

    public View getTabView(int position, TabLayout.Tab tab){
        View v = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.tab_item_view,null);
        ImageView img = (ImageView)v.findViewById(R.id.tab_icon);
        img.setImageResource(tabIcon[position]);
        TextView tv = (TextView)v.findViewById(R.id.tab_text);
        tv.setText(tabTitle[position]);
        if(position == 0){
            v.setSelected(true);
        }
        return v;
    }
}