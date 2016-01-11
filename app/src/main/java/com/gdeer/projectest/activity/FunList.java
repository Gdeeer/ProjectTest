package com.gdeer.projectest.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gdeer.projectest.R;
import com.gdeer.projectest.adapter.ChatAdapter;
import com.gdeer.projectest.adapter.FunAdapter;
import com.gdeer.projectest.adapter.SquareAdapter;
import com.gdeer.projectest.application.MyApplication;
import com.gdeer.projectest.database.ProjectTestDB;
import com.gdeer.projectest.fragmentPage.FragmentPage1;
import com.gdeer.projectest.model.ItemChat;
import com.gdeer.projectest.model.ItemFun;
import com.gdeer.projectest.model.ItemSquare;
import com.gdeer.projectest.util.Contant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FunList extends AppCompatActivity implements FunAdapter.MyItemClickListener,
        FunAdapter.MyItemLongClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_fun_list)
    RecyclerView rvFunList;
    @Bind(R.id.refresh_fun)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.fun_edit)
    EditText funEdit;
    @Bind(R.id.fun_send)
    Button funSend;

    private ProjectTestDB projectTestDB;
    private FunAdapter fAdapter;
    private List<ItemFun> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_list);
        ButterKnife.bind(this);
        setToolbar();
        setSwipeRefreshLayout();

        for (int i = 0; i < 30; i++) {
            ItemFun item = new ItemFun();
            item.setPublisherName(toolbar.getTitle().toString());
            item.setPublishTime("2016.1.1");
            item.setPublishContent("爱你一万年");
            mDataList.add(item);
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FunList.this);
        rvFunList.setLayoutManager(mLayoutManager);
        fAdapter = new FunAdapter(mDataList);
        fAdapter.setOnItemClickListener(this);
        fAdapter.setOnItemLongClickListener(this);
        rvFunList.setAdapter(fAdapter);

        projectTestDB = ProjectTestDB.getInstance(MyApplication.getContext());

        funEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length()>0){
                    funSend.setBackgroundResource(R.color.primary);
                }else{
                    funSend.setBackgroundResource(R.color.button_background);
                }
            }
        });

        funSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemFun item = new ItemFun();
                item.setPublisherName("唐僧");
                item.setPublishTime("2016.1.1");
                if (funEdit.getText().toString().trim().length()>0) {
                    item.setPublishContent(funEdit.getText().toString());
                    mDataList.add(0, item);
                    fAdapter.notifyDataSetChanged();
                }
                funEdit.setText("");
            }
        });

    }

    public void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent intent = getIntent();
        String itemName = intent.getStringExtra("item_name");
        toolbar.setTitle(itemName);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    public void setSwipeRefreshLayout() {
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fun_list, menu);
        SharedPreferences preferences = getSharedPreferences(toolbar.getTitle().toString() + "followOrNot", MODE_PRIVATE);
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
            SharedPreferences preferences = getSharedPreferences(toolbar.getTitle().toString() + "followOrNot", MODE_PRIVATE);
            SharedPreferences.Editor editor = getSharedPreferences(toolbar.getTitle().toString() + "followOrNot", MODE_PRIVATE).edit();
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
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                //refresh();
                swipeRefresh.setRefreshing(false);
            }
        }, 600);
    }

    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    @Override
    public void onBackPressed() {
        SharedPreferences preferences = getSharedPreferences(toolbar.getTitle().toString() + "followOrNot", MODE_PRIVATE);
        boolean changed = preferences.getBoolean("changed", false);
        if (changed) {
            boolean followed = preferences.getBoolean("followed", true);
            if (followed) {
                ItemSquare item = new ItemSquare();
                item.setItemName(toolbar.getTitle().toString());
                item.setItemType(Contant.LIST_TYPE_FUN);
                projectTestDB.saveMyFollow(item);
            } else {
                projectTestDB.deleteMyFollow(toolbar.getTitle().toString());
            }
            FragmentPage1.loadMyFollow();
            SharedPreferences.Editor editor = getSharedPreferences(toolbar.getTitle().toString() + "followOrNot", MODE_PRIVATE).edit();
            editor.putBoolean("changed", false);
            editor.apply();
        }
        finish();
    }
}
