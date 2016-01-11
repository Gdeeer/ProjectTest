package com.gdeer.projectest.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gdeer.projectest.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.next.tagview.TagCloudView;

public class AddTag extends AppCompatActivity {

    @Bind(R.id.tag_all)
    TagCloudView tagAll;
    @Bind(R.id.tag_selected)
    TagCloudView tagSelected;

    public static List<String> tagSlectedList = new ArrayList<>();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private String[] tags = new String[]{"物理", "数学", "计算机", "化学", "建筑", "土木", "影视"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tag);
        ButterKnife.bind(this);

        setToolbar();
        setTagAll();
        setTagSelected();
    }

    public void setToolbar() {
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        toolbar.setTitle("添加知识点");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void setTagAll() {
        //将string[]转化为List
        final List<String> tagsALL = new ArrayList<>();
        Collections.addAll(tagsALL, tags);
        tagAll.setTags(tagsALL);
        tagAll.setOnTagClickListener(new TagCloudView.OnTagClickListener() {
            @Override
            public void onTagClick(int position) {
                int temp = 0;
                for (int j = 0; j < tagSlectedList.size(); j++)
                    if (tagSlectedList.get(j) == tags[position]) {
                        temp = 1;
                        break;
                    }
                if (temp == 0) {
                    tagSlectedList.add(tags[position]);
                }
                tagSelected.setTags(tagSlectedList);
            }
        });
    }

    public void setTagSelected() {
        tagSelected.setTags(tagSlectedList);
        tagSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<Integer> mSelectedItems = new ArrayList<>();
                AlertDialog.Builder builder = new AlertDialog.Builder(AddTag.this);
                builder.setTitle("删除")
                        .setMultiChoiceItems(tagSlectedList.toArray(new CharSequence[tagSlectedList.size()]), null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        if (isChecked) {
                                            mSelectedItems.add(which);
                                        } else if (mSelectedItems.contains(which)) {
                                            mSelectedItems.remove(which);
                                        }
                                    }
                                })
                        .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = mSelectedItems.size() - 1; i >= 0; i--) {
                                    tagSlectedList.remove(i);
                                }
                                tagSelected.setTags(tagSlectedList);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
