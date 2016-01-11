package com.gdeer.projectest.fragmentPage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdeer.projectest.R;
import com.gdeer.projectest.activity.AnnounceList;
import com.gdeer.projectest.activity.FunList;
import com.gdeer.projectest.adapter.SquareAdapter;
import com.gdeer.projectest.application.MyApplication;
import com.gdeer.projectest.database.ProjectTestDB;
import com.gdeer.projectest.model.ItemSquare;
import com.gdeer.projectest.util.Contant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FragmentPage1 extends Fragment implements SquareAdapter.MyItemClickListener,
        SquareAdapter.MyItemLongClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_my_follow)
    RecyclerView rvMyFollow;
    @Bind(R.id.refresh_my_follow)
    SwipeRefreshLayout refreshMyFollow;
    public static SquareAdapter sAdapter;

    private static ProjectTestDB projectTestDB;
    public static List<ItemSquare> mDataList = new ArrayList<>();
    private String[] mChoices = new String[]{"置顶", "删除"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, null);
        ButterKnife.bind(this, view);

        toolbar.setTitle("首页");
        toolbar.setTitleTextColor(Color.WHITE);
        setSwipeRefreshLayout();

        //设置 layoutManager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        rvMyFollow.setLayoutManager(mLayoutManager);
        //设置 adapter
        sAdapter = new SquareAdapter(mDataList);
        sAdapter.setOnItemClickListener(this);
        sAdapter.setOnItemLongClickListener(this);
        rvMyFollow.setAdapter(sAdapter);

        projectTestDB = ProjectTestDB.getInstance(MyApplication.getContext());
        loadMyFollow();

        return view;
    }

    public void setSwipeRefreshLayout() {
        refreshMyFollow.setOnRefreshListener(this);
        refreshMyFollow.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public static void loadMyFollow() {
        List<ItemSquare> mList = projectTestDB.loadMyFollow();
        mDataList.clear();
        for (ItemSquare item : mList) {
            mDataList.add(0, item);
        }
        sAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(View view, int postion) {
        ItemSquare data = mDataList.get(postion);
        if (data != null) {
            if (data.getItemType() == Contant.LIST_TYPE_ANNOUNCE) {
                Intent intent = new Intent(getActivity(), AnnounceList.class);
                intent.putExtra("item_name", data.getItemName());
                startActivity(intent);
            }else if (data.getItemType() == Contant.LIST_TYPE_FUN){
                Intent intent = new Intent(getActivity(), FunList.class);
                intent.putExtra("item_name", data.getItemName());
                startActivity(intent);
            }
        }
    }

    @Override
    public void onItemLongClick(View view, final int postion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(mDataList.get(postion).getItemName());
        builder.setItems(mChoices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 1) {
                    //删除
                    projectTestDB.deleteMyFollow(mDataList.get(postion).getItemName());
                    loadMyFollow();
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("followOrNot", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("followed", false);
                    editor.apply();
                } else {
                    //置顶
                    ItemSquare itemSquare = new ItemSquare();
                    itemSquare.setItemType(mDataList.get(postion).getItemType());
                    itemSquare.setItemName(mDataList.get(postion).getItemName());
//                    itemSquare = mDataList.get(postion);
                    projectTestDB.deleteMyFollow(mDataList.get(postion).getItemName());
                    projectTestDB.saveMyFollow(itemSquare);
                    loadMyFollow();
                }
            }
        }).setCancelable(true);
        builder.create().show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshMyFollow.setRefreshing(false);
            }
        },600);
    }
}