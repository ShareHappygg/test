package com.example.smartcity_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RegisterActivity extends BaseActivity {

    private EditText edit_username,edit_password;

    private  String username;

    private  String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void initView() {
        edit_username = findViewById(R.id.edit_phone);
        edit_password = findViewById(R.id.edit_password);

    }

    @Override
    public void loadData() {
        username = edit_username.getText().toString();
        password = edit_password.getText().toString();

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initListener() {

    }

    private void validate()
    {

    }
}