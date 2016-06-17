package com.example.pengxinkai001.smsstestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Random;


import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    String APPKEY = "12cdd5c4c5ffc";
    String appSecretKey = "84af70bc0671572fa2e7fa77806edc27";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //初始化SDK
        SMSSDK.initSDK(MainActivity.this, APPKEY, appSecretKey);

        btn = (Button) findViewById(R.id.btn_bind);
        //给按钮设置监听
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册手机号界面
                RegisterPage registerPage = new RegisterPage();

                //注册回调事件
                registerPage.setRegisterCallback(new EventHandler() {
                    @Override
                    public void afterEvent(int event, int result, Object data) {
                        //判断结果是否已经完成
                        if (result == SMSSDK.RESULT_COMPLETE) {

                            HashMap<String, Object> maps = (HashMap<String, Object>) data;

                            //获得国家编号
                            String conutry = (String) maps.get("conutry");
                            //获得手机号码
                            String phone = (String) maps.get("phone");

                            submitUserInfo(conutry, phone);
                        }
                    }
                });
                registerPage.show(MainActivity.this);
            }
        });
    }

    //提交用户信息
    public void submitUserInfo(String conutry, String phone) {
        Random random = new Random();
        String nickName = "IMOOC";
        String uid = Math.abs(random.nextInt()) + "";
        SMSSDK.submitUserInfo(uid, nickName, null, conutry, phone);
    }


}
