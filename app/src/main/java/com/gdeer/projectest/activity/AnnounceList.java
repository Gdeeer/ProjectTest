package com.gdeer.projectest.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gdeer.projectest.R;
import com.gdeer.projectest.adapter.SquareAdapter;
import com.gdeer.projectest.application.MyApplication;
import com.gdeer.projectest.database.ProjectTestDB;
import com.gdeer.projectest.fragmentPage.FragmentPage1;
import com.gdeer.projectest.model.Announcement;
import com.gdeer.projectest.model.ItemSquare;
import com.gdeer.projectest.util.Contant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnnounceList extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_announce)
    RecyclerView rvAnnounce;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.refresh_fun)
    SwipeRefreshLayout refreshAnnounce;
    @Bind(R.id.float_add_button)
    FloatingActionButton floatAddButton;

    private SquareAdapter sAdapter;

    private ProjectTestDB projectTestDB;
    private List<ItemSquare> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_list);
        ButterKnife.bind(this);

        setToolbar();
        setSwipeRefreshLayout();

        floatAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Edit2.class);
                startActivityForResult(intent, 1);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        rvAnnounce.setLayoutManager(mLayoutManager);
        sAdapter = new SquareAdapter(mDataList);
        sAdapter.setOnItemClickListener(new SquareAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                ItemSquare data = mDataList.get(postion);
                if (data != null) {
                    Intent intent = new Intent(AnnounceList.this, OneAnnounce.class);
                    intent.putExtra("title", mDataList.get(postion).getItemName());
                    startActivityForResult(intent, 2);
                }
            }
        });

        rvAnnounce.setAdapter(sAdapter);
        projectTestDB = ProjectTestDB.getInstance(MyApplication.getContext());

        loadAnnouncement();
    }

    public void setToolbar() {
        String itemName = getIntent().getStringExtra("item_name");
        toolbar.setTitle(itemName);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    public void setSwipeRefreshLayout() {
        refreshAnnounce.setOnRefreshListener(this);
        refreshAnnounce.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                //refresh();
                refreshAnnounce.setRefreshing(false);
            }
        }, 600);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedTitle = data.getStringExtra("title_return");
                    String returnedTag = data.getStringExtra("tag_return");
                    String returnedCount = data.getStringExtra("count_return");
                    String returnedDescribe = data.getStringExtra("describe_return");
                    Announcement announcement = new Announcement();
                    announcement.setTitle(returnedTitle);
                    announcement.setTag(returnedTag);
                    announcement.setCount(returnedCount);
                    announcement.setDescribe(returnedDescribe);
                    saveAnnouncement(projectTestDB, announcement);
                    loadAnnouncement();
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    boolean isFromDelete = data.getBooleanExtra("from_delete", false);
                    if (isFromDelete) {
                        Log.d("from_delete", "has been deleted");
                        loadAnnouncement();
                    } else {
                        Log.d("from_delete", "has not been deleted");
                    }
                }
                break;
            default:
                break;
        }
    }

    private void saveAnnouncement(ProjectTestDB pDB, Announcement announcement) {
        pDB.saveAnnouncement(announcement);
    }

    private void loadAnnouncement() {
        if (toolbar.getTitle().toString().equals("项目")) {
            List<Announcement> announcementList = projectTestDB.loadAnnouncement();
            mDataList.clear();
            for (Announcement announcement : announcementList) {
                ItemSquare item = new ItemSquare();
                item.setItemName(announcement.getTitle());
                mDataList.add(0, item);
            }
            sAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_announcement_list, menu);
        SharedPreferences preferences = getSharedPreferences("followOrNot", MODE_PRIVATE);
        boolean followed = preferences.getBoolean("followed", false);
        if (followed) {
            menu.getItem(0).setTitle("取消关注");
        } else {
            menu.getItem(0).setTitle("关注");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_follow) {
            SharedPreferences preferences = getSharedPreferences("followOrNot", MODE_PRIVATE);
            SharedPreferences.Editor editor = getSharedPreferences("followOrNot", MODE_PRIVATE).edit();
            if (item.getTitle().toString().equals("关注")) {
                //改状态为已关注
                editor.putBoolean("followed", true);
                editor.apply();
                //改动作为取消关注
                item.setTitle("取消关注");
            } else {
                //改状态为未关注
                editor.putBoolean("followed", false);
                editor.apply();
                //改动作为关注
                item.setTitle("关注");
            }
            //点击一次，在变与不变间转换
            boolean changed = preferences.getBoolean("changed", false);
            if (changed) {
                editor.putBoolean("changed", false);
            } else {
                editor.putBoolean("changed", true);
            }
            editor.apply();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        SharedPreferences preferences = getSharedPreferences("followOrNot", MODE_PRIVATE);
        boolean changed = preferences.getBoolean("changed", false);
        if (changed) {
            boolean followed = preferences.getBoolean("followed", true);
            if (followed) {
                ItemSquare item = new ItemSquare();
                item.setItemName(toolbar.getTitle().toString());
                item.setItemType(Contant.LIST_TYPE_ANNOUNCE);
                projectTestDB.saveMyFollow(item);
            } else {
                projectTestDB.deleteMyFollow(toolbar.getTitle().toString());
            }
            FragmentPage1.loadMyFollow();
            SharedPreferences.Editor editor = getSharedPreferences("followOrNot", MODE_PRIVATE).edit();
            editor.putBoolean("changed", false);
            editor.apply();
        }
        finish();
    }
}
