package com.gdeer.projectest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private FragmentPage1 myFragment1 = null;
    private FragmentPage2 myFragment2 = null;
    private FragmentPage3 myFragment3 = null;
    private FragmentPage4 myFragment4 = null;
    private String tabTitles[] = new String[]{"大楼","广场","消息","我"};

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
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
