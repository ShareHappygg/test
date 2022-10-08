package com.example.smartcity_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity_app.adapter.BusListAdapter;
import com.example.smartcity_app.bean.AjaxData;
import com.example.smartcity_app.bean.AjaxResult;
import com.example.smartcity_app.bean.BusBean;
import com.example.smartcity_app.bean.BusInfoBean;
import com.example.smartcity_app.bean.UserBean;
import com.example.smartcity_app.utils.Constant;
import com.example.smartcity_app.utils.NetUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BusInfoActivity extends BaseActivity {

    private Toolbar mToolbar;

    private TextView mTvTimeSort,mTvPriceSort,mTvRailway,mTvDate;

    private ListView mListView;

    private  String from ,to , time;

    private  List<BusBean> data;

    private  BusListAdapter busListAdapter;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case  1:
                   AjaxData<BusInfoBean> ajaxData =(AjaxData<BusInfoBean>)msg.obj;
                   data = ajaxData.getUserInfo().getData();
                   busListAdapter  = new BusListAdapter(getBaseContext(), R.layout.item_bus, data);
                   mListView.setAdapter(busListAdapter);
                   break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_info);
        initView();
        initAdapter();
        initListener();
        loadData();
    }

    @Override
    public void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mTvRailway = findViewById(R.id.text_railway);
        mTvPriceSort = findViewById(R.id.text_sort_price);
        mTvTimeSort = findViewById(R.id.text_sort_time);
        mListView = findViewById(R.id.list_view_bus);
        mTvDate = findViewById(R.id.text_date);

    }

    @Override
    public void loadData() {
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        to = intent.getStringExtra("to");
        time = intent.getStringExtra("time");
        mTvRailway.setText(String.format(getString(R.string.railway),from,to));
        mTvDate.setText(String.format(getString(R.string.date),time));
        getBusInfo();

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mToolbar.setNavigationIcon(R.mipmap.left_jiantou);

        mTvTimeSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<BusBean> busBeanList = new ArrayList<>();
                busBeanList.addAll(data);
                List<BusBean> collect = busBeanList.stream().sorted(new Comparator<BusBean>() {
                    @Override
                    public int compare(BusBean busBean, BusBean t1) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        Date date1 = null;
                        Date date2 = null;
                        try {
                            date1 = simpleDateFormat.parse(busBean.getStarttime());
                            date2 = simpleDateFormat.parse(t1.getStarttime());
                            return (int) (date1.getTime() - date2.getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                }).collect(Collectors.toList());
                busListAdapter.clear();
                busListAdapter.addAll(collect);
                busListAdapter.notifyDataSetChanged();
            }
        });

        mTvPriceSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<BusBean> busBeanList = new ArrayList<>();
                busBeanList.addAll(data);
                List<BusBean> collect = busBeanList.stream().sorted(new Comparator<BusBean>() {
                    @Override
                    public int compare(BusBean busBean, BusBean t1) {
                        return Integer.parseInt(busBean.getLowestprice()) - Integer.parseInt(t1.getLowestprice());
                    }
                }).collect(Collectors.toList());
                busListAdapter.clear();
                busListAdapter.addAll(collect);
                busListAdapter.notifyDataSetChanged();

            }
        });
    }

    private  void getBusInfo()
    {
        String url = Constant.BASE_URL+Constant.TICKET;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("from",from);
            jsonObject.put("to",to);
            jsonObject.put("time",time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
        NetUtils.doPost(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                if (response.isSuccessful())
                {
                    String jsonStr =response.body().string();
                    AjaxResult<BusInfoBean> ajaxResult =new Gson().fromJson(jsonStr,new TypeToken<AjaxResult<BusInfoBean>>(){}.getType());
                    if (ajaxResult.getData().isVerifySuccess())
                    {
                        message.what = 1;
                        message.obj = ajaxResult.getData();
                    }else {
                        message.what = -1;
                        message.obj = "请求失败";
                    }
                }else {
                    message.what = -1;
                    message.obj = "请求失败";
                }
                handler.sendMessage(message);
            }
        });
    }



}