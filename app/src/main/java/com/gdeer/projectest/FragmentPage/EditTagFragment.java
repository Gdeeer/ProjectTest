package com.gdeer.projectest.fragmentPage;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.gdeer.projectest.R;
import com.gdeer.projectest.application.MyApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.next.tagview.TagCloudView;

public class EditTagFragment extends Fragment {

    @Bind(R.id.tag_selected)
    TagCloudView tagSelected;
    @Bind(R.id.tag_all)
    TagCloudView tagAll;

    public static List<String> tagSlectedList = new ArrayList<>();
    private String[] tags = new String[]{"物理", "数学", "计算机", "化学", "建筑", "土木", "影视"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_tag, null);
        ButterKnife.bind(this, view);

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

        tagSelected.setTags(tagSlectedList);
        tagSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<Integer> mSelectedItems = new ArrayList<>();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tagSlectedList.clear();
        ButterKnife.unbind(this);
    }

}
