package com.gdeer.projectest.fragmentPage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.gdeer.projectest.R;
import com.gdeer.projectest.activity.AnnounceList;
import com.gdeer.projectest.activity.FunList;
import com.gdeer.projectest.adapter.SquareAdapter;
import com.gdeer.projectest.application.MyApplication;
import com.gdeer.projectest.model.ItemSquare;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FragmentPage2 extends Fragment implements SquareAdapter.MyItemClickListener,
        SquareAdapter.MyItemLongClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_square)
    RecyclerView rvSquare;

    private final Integer TYPE_ANNOUNCE = 1;
    private final Integer TYPE_FUN = 2;
    @Bind(R.id.refresh_square)
    SwipeRefreshLayout refreshSquare;
    private RecyclerView.LayoutManager mLayoutManager;
    private SquareAdapter sAdapter;
    private ItemSquare item = new ItemSquare("项目", TYPE_ANNOUNCE);
    private ItemSquare item2 = new ItemSquare("元旦咯", TYPE_FUN);
    //    private List<String> mDataList = new ArrayList<String>(){{add("项目");}};
    private List<ItemSquare> mDataList = new ArrayList<ItemSquare>() {{
        add(item);add(item2);
    }};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, null);
        hasOptionsMenu();
        ButterKnife.bind(this, view);

        setToolbar();
        setSwipeRefreshLayout();

        //设置 layoutManager
        mLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        rvSquare.setLayoutManager(mLayoutManager);
        //设置 adapter
        sAdapter = new SquareAdapter(mDataList);
        sAdapter.setOnItemClickListener(this);
        sAdapter.setOnItemLongClickListener(this);
        rvSquare.setAdapter(sAdapter);
        return view;
    }

    public void setToolbar() {
        toolbar.setTitle("广场");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.inflateMenu(R.menu.menu_fragment_2);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_type_1) {
                    showAddDialog(TYPE_ANNOUNCE);
                } else if (id == R.id.action_type_2) {
                    showAddDialog(TYPE_FUN);
                }
                return true;
            }
        });
    }

    public void setSwipeRefreshLayout() {
        refreshSquare.setOnRefreshListener(this);
        refreshSquare.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void showAddDialog(final Integer type) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle("创建");
        View dialogView = inflater.inflate(R.layout.dialog_create, null);
        builder.setView(dialogView);
        //final EditText name = new EditText(getActivity());
        final EditText name = (EditText) dialogView.findViewById(R.id.name);
        //builder.setView(name);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Log.d("something", name.getText().toString());
                if (name.getText().toString().trim().equals("")) {
                } else {
                    ItemSquare item1 = new ItemSquare();
                    item1.setItemName(name.getText().toString());
                    item1.setItemType(type);
                    mDataList.add(item1);
                    sAdapter.notifyDataSetChanged();
                }
            }
        })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //FragmentPage2.this.getDialog().cannel();
                    }
                });
        builder.create().show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager =
                        (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 600);

    }

    @Override
    public void onItemClick(View view, int postion) {
        ItemSquare data = mDataList.get(postion);
        if (data.getItemType().equals(TYPE_ANNOUNCE)) {
            Intent intent = new Intent(getActivity(), AnnounceList.class);
            intent.putExtra("item_name", data.getItemName());
            startActivity(intent);
        } else if (data.getItemType().equals(TYPE_FUN)) {
            Intent intent = new Intent(getActivity(), FunList.class);
            intent.putExtra("item_name", data.getItemName());
            startActivity(intent);
        }
    }

    @Override
    public void onItemLongClick(View view, int postion) {
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
                refreshSquare.setRefreshing(false);
            }
        }, 600);
    }
}