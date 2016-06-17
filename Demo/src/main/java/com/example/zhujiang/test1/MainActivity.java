package com.example.zhujiang.test1;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取inflater ，布局填充器
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main,null);
        setContentView(view);

        Button btnIsNetWork = (Button) view.findViewById(R.id.id_network_conn);
        btnIsNetWork.setOnClickListener(this);


        Button btnWifi = (Button) view.findViewById(R.id.id_wifi_conn);
        btnWifi.setOnClickListener(this);


        Button btnSound = (Button) view.findViewById(R.id.id_sound_conn);
        btnSound.setOnClickListener(this);

        Button btnPackage = (Button) view.findViewById(R.id.id_package_conn);
        btnPackage.setOnClickListener(this);

    }


    public boolean isNetworkConnection(){
        // 获取系统服务网络链接
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info =cm.getActiveNetworkInfo();
        if(info!=null){
            return info.isAvailable();
        }
        return false;

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_network_conn:
                // 判断网络是否链接
                if(isNetworkConnection()){
                    Toast.makeText(MainActivity.this,"网络已链接",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"网络未链接",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_wifi_conn:
                WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                // 如果当前wifi启用了
                if(wm.isWifiEnabled()){
                    wm.setWifiEnabled(false);
                    Toast.makeText(MainActivity.this,"wifi已经打开，正在关闭",Toast.LENGTH_SHORT).show();
                }else{
                    wm.setWifiEnabled(true);
                    Toast.makeText(MainActivity.this,"wifi已关闭，正在打开",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_sound_conn:
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                // 获取系统当前最大音量值
                int max = am.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
                // 获取当前铃声音量
                int current = am.getStreamVolume(AudioManager.STREAM_RING);
                Toast.makeText(MainActivity.this,"系统的最大音量："+max+",当前音量："+current,Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_package_conn:
                ActivityManager actManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                String packageName=actManager.getRunningTasks(1).get(0).topActivity.getPackageName();
                Toast.makeText(MainActivity.this,"当前运行的actvity包名："+packageName,Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
