package com.example.zhujiang.test1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.zhujiang.test1.R;

/**
 * Created by zhujiang on 16-4-26.
 */
public class ListLoadMoreView extends ListView implements AbsListView.OnScrollListener {

    private int lastVisibleItem;
    private int totalItemCount;
    private View footView;
    private boolean isLoading;



    public ListLoadMoreView(Context context) {
        super(context);
        initView(context);
    }

    public ListLoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ListLoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    /**
     * 添加底部布局
     *
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footView = inflater.inflate(R.layout.footer_layout, null);

        footView.findViewById(R.id.id_load_layout).setVisibility(View.GONE); //隐藏
        this.addFooterView(footView);
        this.setOnScrollListener(this);
    }

    private ILoadListener listener;

    public void setListener(ILoadListener listener) {


        this.listener = listener;
    }
        @Override
        public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        // 如果加载到总数据的最后一条并且不在滚动，则显示底部布局，并且加载数据加载完成后隐藏布局
        if (!isLoading) {
            if (lastVisibleItem == totalItemCount && scrollState == SCROLL_STATE_IDLE) {

                isLoading = true;
                footView.findViewById(R.id.id_load_layout).setVisibility(View.VISIBLE);  //加载时显示
                listener.onLoad(); // 开始加载数据
            }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    /**
     * 加载完成
     */
    public void loadComplete() {
        isLoading = false;

        //  隐藏view
        footView.findViewById(R.id.id_load_layout).setVisibility(View.GONE);
    }


    //加载更多数据的回调借口
    public interface ILoadListener {
         void onLoad();
    }


}
