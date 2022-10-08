package com.example.smartcity_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

public class PersonalActivity extends BaseActivity {

    private EditText edit_name,edit_phone,edit_email,edit_date;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initView();
        loadData();
        initAdapter();
        initListener();
    }


    @Override
    public void initView() {
        toolbar = findViewById(R.id.toolbar);
        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);
        edit_email = findViewById(R.id.edit_email);
        edit_date = findViewById(R.id.edit_date);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initListener() {
        toolbar.setNavigationIcon(R.mipmap.left_jiantou);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}