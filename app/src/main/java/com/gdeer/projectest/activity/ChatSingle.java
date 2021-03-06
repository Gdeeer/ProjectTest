package com.gdeer.projectest.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gdeer.projectest.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChatSingle extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_single);
        ButterKnife.bind(this);
        setToolbar();
    }

    public void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent intent = getIntent();
        String itemName = intent.getStringExtra("chat_name");
        toolbar.setTitle(itemName);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }
}
