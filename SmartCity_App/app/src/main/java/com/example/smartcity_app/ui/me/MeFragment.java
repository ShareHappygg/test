package com.example.smartcity_app.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.smartcity_app.EditPasswordActivity;
import com.example.smartcity_app.FeedBackActivity;
import com.example.smartcity_app.LoginActivity;
import com.example.smartcity_app.PersonalActivity;
import com.example.smartcity_app.R;
import com.example.smartcity_app.fragment.BaseFragment;
import com.example.smartcity_app.ui.notifications.NotificationsViewModel;

public class MeFragment extends BaseFragment {

    private TextView text_me_btn;

    private LinearLayout layout_personal,layout_logout,layout_edit_password,layout_feedback;

    private  View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_me, container, false);
        initView();
        initAdapter();
        initListener();

        return root;
    }

    @Override
    public void initView() {
        text_me_btn = root.findViewById(R.id.text_me_btn);
        layout_personal = root.findViewById(R.id.layout_personal);
        layout_logout = root.findViewById(R.id.layout_logout);
        layout_edit_password = root.findViewById(R.id.layout_edit_password);
        layout_feedback = root.findViewById(R.id.layout_feedback);
    }

    @Override
    public void loadData() {
        super.loadData();
    }

    @Override
    public void initListener() {
        text_me_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent();
               intent.setClass(getContext(), LoginActivity.class);
               startActivity(intent);
           }
       });
        layout_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), PersonalActivity.class);
                startActivity(intent);
            }
        });
        layout_edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), EditPasswordActivity.class);
                startActivity(intent);
            }
        });
        layout_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), FeedBackActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initAdapter() {
        super.initAdapter();
    }
}