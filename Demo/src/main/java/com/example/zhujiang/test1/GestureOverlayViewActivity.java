package com.example.zhujiang.test1;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class GestureOverlayViewActivity extends AppCompatActivity {



    private GestureOverlayView gestureOverlayView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_overlay_view);


        gestureOverlayView = (GestureOverlayView) findViewById(R.id.id_gesture);
        // 获取raw文件夹下的手势文件
        final GestureLibrary gestureLibrary=GestureLibraries.fromRawResource(this,R.raw.gestures);
        gestureLibrary.load(); // 加载

        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
                // 读取手势内容，识别手势
                ArrayList<Prediction> predictions=gestureLibrary.recognize(gesture);
                Prediction prediction = predictions.get(0);
                // 相似度大于5.0,值越大，越相似，不易过大
                if(prediction.score>=5.0){
                    if(prediction.name.equals("Exit")){
                        finish();
                    }
                    if(prediction.name.equals("Next")){
                        Toast.makeText(GestureOverlayViewActivity.this,"下一个",Toast.LENGTH_SHORT).show();
                    }
                    if(prediction.name.equals("Open")){
                        Toast.makeText(GestureOverlayViewActivity.this,"打开",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(GestureOverlayViewActivity.this,"该手势不存在",Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
}
