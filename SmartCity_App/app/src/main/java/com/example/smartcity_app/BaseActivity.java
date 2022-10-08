package com.example.smartcity_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.smartcity_app.utils.Constant;

public  abstract class BaseActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public  abstract    void  initView();

    public abstract void loadData();

    public  abstract  void  initAdapter();

    public  abstract void initListener();

}