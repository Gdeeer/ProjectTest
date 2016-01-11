package com.gdeer.projectest.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gdeer.projectest.R;
import com.gdeer.projectest.application.MyApplication;
import com.gdeer.projectest.database.ProjectTestDB;
import com.gdeer.projectest.model.Announcement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.next.tagview.TagCloudView;

public class OneAnnounce extends AppCompatActivity {

    private TagCloudView tagShow;
    private TextView titleShow;
    private TextView counrShow;
    private TextView describeShow;
    private ProjectTestDB projectTestDB;
    private Button join;
    private final List<String> tagsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_announce);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("详情");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        tagShow = (TagCloudView) findViewById(R.id.project_tag_one);
        titleShow = (TextView) findViewById(R.id.project_title_one);
        counrShow = (TextView) findViewById(R.id.project_people_count_one);
        describeShow = (TextView) findViewById(R.id.project_describe_one);
        join = (Button) findViewById(R.id.join);

        projectTestDB = ProjectTestDB.getInstance(this);
        Load();
        tagShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApplication.getContext(),"申请已经发出，请等候通知。", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OneAnnounce.this);
        builder.setTitle("标签")
                .setItems(tagsList.toArray(new CharSequence[tagsList.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();
    }

    public void Load() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        Announcement announcement = projectTestDB.searchAndSet(title);
        titleShow.setText(announcement.getTitle());
        counrShow.setText(announcement.getcount());
        describeShow.setText(announcement.getDescribe());

        String stags = announcement.getTag();
        Log.d("myString", stags);
        if (stags.length() > 2) {
            String[] tags = stags.substring(1, stags.length() - 1).split(", ");
            //将string[]转化为List
            Collections.addAll(tagsList, tags);
            tagShow.setTags(tagsList);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_one_announcement, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            Log.d("delete", titleShow.getText().toString());
            projectTestDB.deleteAnnouncement(titleShow.getText().toString());
            Intent intent = new Intent();
            intent.putExtra("from_delete", true);
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
