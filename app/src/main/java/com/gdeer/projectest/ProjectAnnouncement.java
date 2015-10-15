package com.gdeer.projectest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.gdeer.projectest.adapter.SquareAdapter;
import com.gdeer.projectest.database.ProjectTestDB;
import com.gdeer.projectest.model.Announcement;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProjectAnnouncement extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerView_project)
    RecyclerView recyclerViewProject;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.id_swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.float_add_button)
    FloatingActionButton floatAddButton;

    private RecyclerView.LayoutManager mLayoutManager;
    private SquareAdapter sAdapter;

    private ProjectTestDB projectTestDB;
    private List<String> dataList = new ArrayList<>();
    private List<Announcement> announcementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_project);
        ButterKnife.bind(this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.id_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        floatAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Edit.class);
                startActivityForResult(intent, 1);
            }
        });


        mLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerViewProject.setLayoutManager(mLayoutManager);
        sAdapter = new SquareAdapter(dataList);
        recyclerViewProject.setAdapter(sAdapter);

        projectTestDB = ProjectTestDB.getInstance(getApplicationContext());
        loadAnnouncement();

        initWindow();
    }

    private SystemBarTintManager tintManager;

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(ContextCompat.getColor(this, R.color.primary));
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(true);
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                //refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 600);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Announcement announcement = new Announcement();
                    announcement.setAnnouncementContent(returnedData);
                    saveAnnouncement(projectTestDB, announcement);
                    loadAnnouncement();
                }
                break;
            default:
        }
    }

    private void saveAnnouncement(ProjectTestDB pDB, Announcement announcement) {
        pDB.saveAnnouncement(announcement);
    }

    private void loadAnnouncement() {
        announcementList = projectTestDB.loadAnnouncement();
        dataList.clear();
        for (Announcement announcement : announcementList) {
            dataList.add(0, announcement.getAnnouncementContent());
        }
        sAdapter.notifyDataSetChanged();
    }
}
