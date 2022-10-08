package com.example.smartcity_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.EventLog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smartcity_app.adapter.BannerAdapter;
//import com.example.smartcity_app.adapter.BannerBusAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BusActivity extends BaseActivity {


    private ViewPager mViewPager ;

    private List<Integer> urls = new ArrayList<>();

    private  Handler mHandler = new Handler(Looper.getMainLooper());

    private EditText edit_date,edit_start,edit_end;

    private TextView search_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        initView();
        loadData();
        initAdapter();
        initListener();
    }

    @Override
    public void initView() {
        mViewPager = findViewById(R.id.banner_bus);
        edit_date = findViewById(R.id.edit_date);
        edit_start = findViewById(R.id.edit_start);
        edit_end = findViewById(R.id.edit_end);
        search_btn = findViewById(R.id.btn_search);
    }

    @Override
    public void loadData() {
//        urls.add("http://example.cheifsteam.cn/smartcity/banner%20%2802%29%402x.png");
//        urls.add("http://example.cheifsteam.cn/smartcity/banner%20%2803%29%402x.png");
//        urls.add("http://example.cheifsteam.cn/smartcity/banner%402x.png");
        urls.add(R.drawable.banner1);
        urls.add(R.drawable.banner2);
        urls.add(R.drawable.banner3);
    }

    @Override
    public void initAdapter() {
        mViewPager.setAdapter(new BannerAdapter(urls));
        mViewPager.setCurrentItem(Integer.MAX_VALUE/2);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mHandler.post(mLoopTask);
    }

    private Runnable mLoopTask = new Runnable() {
        @Override
        public void run() {
            int currentItem = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(++currentItem,true);
            mHandler.postDelayed(this,3000);
        }
    };

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacks(mLoopTask);
    }

    @Override
    public void initListener() {
        edit_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    showDateDlg();
                }
            }
        });
        edit_date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    showDateDlg();
                    return  true;
                }
                return  false;
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("from",edit_start.getText().toString());
                intent.putExtra("to",edit_end.getText().toString());
                intent.putExtra("time",edit_date.getText().toString());
                intent.setClass(BusActivity.this,BusInfoActivity.class);
                startActivity(intent);
            }
        });
    }


    private void showDateDlg()
    {
        LocalDate localDate = LocalDate.now();
        DatePickerDialog datePickerDialog = new DatePickerDialog(BusActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String format =String.format("%d/%d/%d",year,month+1,day);
                edit_date.setText(format);
                edit_date.setSelection(format.length());

            }
        },localDate.getYear(),localDate.getMonthValue(),localDate.getDayOfMonth());
        datePickerDialog.show();
    }







}