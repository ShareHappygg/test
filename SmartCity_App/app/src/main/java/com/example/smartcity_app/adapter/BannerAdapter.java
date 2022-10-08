package com.example.smartcity_app.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.smartcity_app.utils.ImageUtils;

import java.util.List;

public class BannerAdapter extends PagerAdapter {



    private List<Integer> images;

    public BannerAdapter(List<Integer> images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (View)object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        String image = images.get(position%3);
        Integer image = images.get(position%3);
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(image);
        container.addView(imageView);
//        new ImageUtils(imageView,image).getImage();
        return  imageView;
    }
}
