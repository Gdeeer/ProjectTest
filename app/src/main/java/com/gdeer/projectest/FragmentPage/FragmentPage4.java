package com.gdeer.projectest.fragmentPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gdeer.projectest.R;
import com.gdeer.projectest.activity.LoginActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentPage4 extends Fragment {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.profile_image)
    CircleImageView profileImage;
    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.fragment4_cv_1)
    CardView cv1;
    @Bind(R.id.fragment4_cv_2)
    CardView cv2;
    @Bind(R.id.fragment4_cv_3)
    CardView cv3;
    @Bind(R.id.rl_login_or_signup)
    RelativeLayout rlLoginOrSignup;

    private List<String> mDataList = Arrays.asList("登录", "我的关注", "我的发布", "我的简历");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4, null);
        ButterKnife.bind(this, view);

        toolbar.setTitle("我");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.inflateMenu(R.menu.menu_my_detail);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.my_settings) {

                }
                return true;
            }
        });

        rlLoginOrSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}