package com.example.zhujiang.test1;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.example.zhujiang.test1.view.ListLoadMoreView;

import java.util.ArrayList;
import java.util.List;

public class ListLoadMoreActivity extends AppCompatActivity {


    private ListLoadMoreView listView;
    private List<String> datas;
    private ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_load_more);


        initData();
        listView = (ListLoadMoreView) findViewById(R.id.id_list_view);
        listView.setAdapter(myAdapter);
        listView.setListener(new ListLoadMoreView.ILoadListener() {
            @Override
            public void onLoad() {
                if (datas != null && datas.size() > 0) {
                    updateData();
                }
            }
        });
    }

    private void updateData() {
        if(datas==null){
            return;
        }
        //postDelayed 是在延迟指定一段时间后执行post
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                datas.add("老彭");
                datas.add("老王");
                datas.add("老邓");
                myAdapter.notifyDataSetChanged();
                listView.loadComplete();
            }
        },2000);

        // postAtTime 是在未来某一精准时间段内执行post，是直接在Runnable中更新UI的  因为此时的 Runnable 是运行在UI所在的主线程中
        /*new Handler().postAtTime(new Runnable() {
            @Override
            public void run() {
                datas.add("run");
                datas.add("get");
                datas.add("fuck");
                datas.add("shift");
                myAdapter.notifyDataSetChanged();
                listView.loadComplete();
            }
        },2000);*/

    }

    /**
     * 初始化数据
     */
    private void initData() {
        datas = new ArrayList<String>();
        datas.add("嘻嘻");
        datas.add("哈哈");
        datas.add("呵呵");
        datas.add("达达");
        datas.add("呦呦");
        datas.add("哦哦");
        datas.add("恩恩");
        datas.add("嘻嘻");
        datas.add("哈哈");
        datas.add("呵呵");
        datas.add("达达");
        datas.add("呦呦");
        datas.add("哦哦");
        datas.add("恩恩");
        datas.add("嘻嘻");
        datas.add("哈哈");
        datas.add("呵呵");
        datas.add("达达");
        datas.add("呦呦");
        datas.add("哦哦");
        datas.add("恩恩");
        myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
    }
}
