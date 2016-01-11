package com.gdeer.projectest.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gdeer.projectest.R;
import com.gdeer.projectest.adapter.EditFragmentAdapter;
import com.gdeer.projectest.application.MyApplication;
import com.gdeer.projectest.fragmentPage.EditContentFragment;
import com.gdeer.projectest.fragmentPage.EditTagFragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Edit extends AppCompatActivity {

    @Bind(R.id.edit_tabLayout)
    TabLayout editTabLayout;
    @Bind(R.id.edit_pager)
    ViewPager editPager;

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private EditFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        setToolbar();
        mAdapter = new EditFragmentAdapter(getSupportFragmentManager());
        editTabLayout.setTabsFromPagerAdapter(mAdapter);
        editPager.setAdapter(mAdapter);
        editTabLayout.setupWithViewPager(editPager);
    }

    public void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.edit);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            String tag = EditTagFragment.tagSlectedList.toString();
            String title = EditContentFragment.title.getText().toString();
            String count = EditContentFragment.count.getText().toString();
            String describe = EditContentFragment.describe.getText().toString();
            Intent intent = new Intent();
            if (tag.length() < 3) {
                Toast.makeText(MyApplication.getContext(), "请添加标签", Toast.LENGTH_SHORT).show();
            } else if (title.length() == 0) {
                Toast.makeText(MyApplication.getContext(), "请添加标题", Toast.LENGTH_SHORT).show();
            } else if (count.length() == 0) {
                Toast.makeText(MyApplication.getContext(), "请添加人数", Toast.LENGTH_SHORT).show();
            } else if (describe.length() == 0) {
                Toast.makeText(MyApplication.getContext(), "请添加描述", Toast.LENGTH_SHORT).show();
            } else {
                intent.putExtra("tag_return", tag)
                        .putExtra("title_return", title)
                        .putExtra("count_return", count)
                        .putExtra("describe_return", describe);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
