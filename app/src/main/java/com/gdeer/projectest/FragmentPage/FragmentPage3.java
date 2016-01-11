package com.gdeer.projectest.fragmentPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdeer.projectest.R;
import com.gdeer.projectest.activity.ChatSingle;
import com.gdeer.projectest.adapter.ChatAdapter;
import com.gdeer.projectest.model.ItemChat;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentPage3 extends Fragment implements ChatAdapter.MyItemClickListener,
        ChatAdapter.MyItemLongClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_chat)
    RecyclerView rvChat;
    @Bind(R.id.refresh_chat)
    SwipeRefreshLayout refreshChat;

    private List<ItemChat> mDataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, null);
        ButterKnife.bind(this, view);

        toolbar.setTitle("消息");
        toolbar.setTitleTextColor(Color.WHITE);

        setSwipeRefreshLayout();

        mDataList.clear();
        for (int i = 0; i < 30; i++) {
            ItemChat itemChat = new ItemChat();
            itemChat.setChatName("小鹿" + i);
            itemChat.setChatTime("下午13:" + i);
            itemChat.setChatLastMessage("新年快乐。");
            mDataList.add(itemChat);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvChat.setLayoutManager(layoutManager);
        ChatAdapter cAdapter = new ChatAdapter(mDataList);
        cAdapter.setOnItemClickListener(this);
        cAdapter.setOnItemLongClickListener(this);
        rvChat.setAdapter(cAdapter);

        return view;
    }

    public void setSwipeRefreshLayout() {
        refreshChat.setOnRefreshListener(this);
        refreshChat.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
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
                // 停止刷新
                //refresh();
                refreshChat.setRefreshing(false);
            }
        }, 600);
    }

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent(getActivity(), ChatSingle.class);
        intent.putExtra("chat_name", mDataList.get(postion).getChatName());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
