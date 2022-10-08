package com.example.smartcity_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.aware.DiscoverySession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.smartcity_app.bean.AjaxData;
import com.example.smartcity_app.bean.AjaxResult;
import com.example.smartcity_app.bean.UserBean;
import com.example.smartcity_app.utils.Constant;
import com.example.smartcity_app.utils.NetUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {


    private EditText edit_phone,edit_password;

    private TextView login_btn,register_btn;

    private  String username,password;

    private Toolbar toolbar;

    private RadioGroup mRadioGroup;

    private  boolean flag;

    private CheckBox mCheckBox;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case  1:
                    UserBean userBean  = ((AjaxData<UserBean>)msg.obj).getUserInfo();
                    SharedPreferences sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("token",userBean.getToken());
                    edit.apply();
                    SharedPreferences userInfo  = getSharedPreferences("userInfo",MODE_PRIVATE);
                    SharedPreferences.Editor userEdit = userInfo.edit();
                    if (flag)
                    {

                        userEdit.putString("username",username);
                        userEdit.putString("password",password);
                    }else {
                        userEdit.clear();
                    }
                    userEdit.apply();
                    Toast.makeText(getBaseContext(),"登录成功",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent();
                    intent.setClass(getBaseContext(),BusActivity.class);
                    startActivity(intent);
                    break;
                default:
                    Toast.makeText(getBaseContext(),msg.obj.toString(),Toast.LENGTH_LONG).show();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
        initValue();

    }

    @Override
    public void initView() {
        toolbar = findViewById(R.id.toolbar);
        edit_phone = findViewById(R.id.edit_phone);
        edit_password = findViewById(R.id.edit_password);
        login_btn = findViewById(R.id.login_btn);
        register_btn = findViewById(R.id.register_btn);
        mCheckBox = findViewById(R.id.remember_userInfo);
    }

    @Override
    public void loadData() {

        username = edit_phone.getText().toString();
        password = edit_password.getText().toString();
    }

    private  void  initValue()
    {
        flag = mCheckBox.isChecked();
        if (flag)
        {
            SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
            username = userInfo.getString("username", "");
            password = userInfo.getString("password", "");
            edit_phone.setText(username);
            edit_password.setText(password);
        }
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
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                flag = b;
            }
        });
//        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                switch (i)
//                {
//
//                }
//            }
//        });
    }


    public void login (){
        loadData();
        String url = Constant.BASE_URL+Constant.LOGIN;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username",username);
            jsonObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
        NetUtils.doPost(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.what = -1;
                message.obj = "请求失败";
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                if (response.code() ==200)
                {
                    String jsonStr = response.body().string().toString();
                    Log.i("login ", jsonStr);
                    AjaxResult<UserBean> ajaxResult = new Gson().fromJson(jsonStr, new TypeToken<AjaxResult<UserBean>>() {
                    }.getType());
                    if (ajaxResult.getData().isVerifySuccess()) {
                        message.obj = ajaxResult.getData();
                        message.what =1;
                    }else {
                        message.what = -1;
                        message.obj = "请求失败";
                    }

                }
                else
                {
                    message.what = -1;
                    message.obj = "请求失败+1";

                }
                handler.sendMessage(message);
            }
        });

    }


}