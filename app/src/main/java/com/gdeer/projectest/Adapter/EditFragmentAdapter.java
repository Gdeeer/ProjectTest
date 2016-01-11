package com.gdeer.projectest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.gdeer.projectest.activity.Edit;
import com.gdeer.projectest.fragmentPage.EditContentFragment;
import com.gdeer.projectest.fragmentPage.EditTagFragment;

/**
 * Created by Gdeer on 2015/12/13.
 */
public class EditFragmentAdapter extends FragmentPagerAdapter {


    private final int PAGER_COUNT = 2;
    private EditContentFragment contentFragment = null;
    private EditTagFragment tagFragment = null;
    private String tabTitles[] = new String[]{"内容", "标签"};

    public EditFragmentAdapter(FragmentManager fm) {
        super(fm);
        contentFragment = new EditContentFragment();
        tagFragment = new EditTagFragment();
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
            case Edit.PAGE_ONE:
                fragment = contentFragment;
                break;
            case Edit.PAGE_TWO:
                fragment = tagFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}