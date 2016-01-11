package com.gdeer.projectest.fragmentPage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.gdeer.projectest.R;

import java.util.Timer;
import java.util.TimerTask;

public class EditContentFragment extends Fragment {

    public static EditText title;
    public static EditText count;
    public static EditText describe;
    public TextView textView;

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_content, null);

        title = (EditText) view.findViewById(R.id.project_title);
        count = (EditText) view.findViewById(R.id.project_people_count_total);
        describe = (EditText) view.findViewById(R.id.project_describe);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(title, InputMethodManager.SHOW_IMPLICIT);
                           }
                       },
                1000);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(title.getWindowToken(), 0);
        return view;
    }
}
