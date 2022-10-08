package com.example.smartcity_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

public class FeedBackActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initView();
        loadData();
        initAdapter();
        initListener();
    }

    @Override
    public void initView() {
        toolbar = findViewById(R.id.toolbar);
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