package com.example.smartcity_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.smartcity_app.adapter.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity {


    private ViewPager mViewPager;

    private Button enter ;

    private List<Integer> images =new ArrayList<>();

    private  LinearLayout pointLayout;

    private  List<ImageView> imageViewList = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        loadData();
        initAdapter();
        initListener();

    }

    @Override
    public void initView() {
        mViewPager = findViewById(R.id.viewPager);
        pointLayout= findViewById(R.id.layout_points);
        enter = findViewById(R.id.enter);
    }

    @Override
    public void loadData() {
        images.add(R.mipmap.banner1);
        images.add(R.mipmap.banner2);
        images.add(R.mipmap.banner3);
        drawPoints();
    }

    @Override
    public void initAdapter() {
        mViewPager.setAdapter(new BannerAdapter(images));
    }

    @Override
    public void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updatePoint(position);
                if (position == images.size() -1)
                {
                    enter.setVisibility(View.VISIBLE);
                }else {
                    enter.setVisibility(View.GONE);
                }

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public  void updatePoint(int position){
        for (int i = 0; i < imageViewList.size(); i++) {
            ImageView imageView = imageViewList.get(i);
            if (position == i)
            {
                imageView.setImageResource(R.drawable.shape_point_selector);
            }else {
                imageView.setImageResource(R.drawable.shape_point_nomarl);
            }
        }
    }

    public  void drawPoints(){
        LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(20,20);
        params.setMargins(10,0,10,0);
        for (int i = 0; i <images.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            if (i==0)
            {
                imageView.setImageResource(R.drawable.shape_point_selector);
            }else {
                imageView.setImageResource(R.drawable.shape_point_nomarl);
            }
            imageViewList.add(imageView);
            int current = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(current);
                }
            });
            pointLayout.addView(imageView);
        }
    }
}