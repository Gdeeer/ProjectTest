package com.gdeer.projectest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Gdeer on 2015/9/22.
 */
public class FragmentPage4 extends Fragment {

    @Bind(R.id.recyclerView_me)
    android.support.v7.widget.RecyclerView mRecyclerViewMe;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private Context context;
    private String myDataset[] = new String[]{"我的信息","我的关注","我的发布","我的这个","我的那个","还有还有"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4, null);
        ButterKnife.bind(this, view);
        // 创建一个线性布局管理器
        mLayoutManager = new LinearLayoutManager(context);
        // 设置布局管理器
        mRecyclerViewMe.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(myDataset);
        mRecyclerViewMe.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

