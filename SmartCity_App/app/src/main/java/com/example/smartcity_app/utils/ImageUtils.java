package com.example.smartcity_app.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ImageUtils {

    private ImageView imageView;
    private Handler handler =new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case 1:
                    byte [] result = (byte[]) msg.obj;
                    Bitmap bitmap  = BitmapFactory.decodeByteArray(result,0,result.length);
                    imageView.setImageBitmap(bitmap);
                    break;

            }
        }
    };

    private  String url;

    public ImageUtils(ImageView imageView, String url) {
        this.imageView = imageView;
        this.url = url;
    }

    public   void getImage(){
        NetUtils.getImage(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                if (response.isSuccessful())
                {
                    message.what =1 ;
                    message.obj = response.body().bytes();
                    handler.sendMessage(message);
                }else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });

    }
}
