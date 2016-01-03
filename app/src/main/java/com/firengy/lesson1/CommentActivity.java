package com.firengy.lesson1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.firengy.lesson1.adapter.CommentAdapter;
import com.firengy.lesson1.adapter.ZhuanXiangAdapter;
import com.firengy.lesson1.entity.CommentItem;
import com.firengy.lesson1.entity.ZhuanXiangItem;
import com.firengy.lesson1.tools.HttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CommentActivity extends AppCompatActivity implements Callback<CommentItem>, PullToRefreshBase.OnRefreshListener {

    //private PullToZoomScrollViewEx scrollComment;
    private PullToRefreshListView listComment;
    //private Call<CommentItem> call;
    private CommentAdapter adapter;

    private static final String TAG = "CommentActivity";
    //private int currentCount = 0;
    private int page = 1;
    private int commentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //scrollComment = (PullToZoomScrollViewEx) findViewById(R.id.scroll_comment);
        listComment = (PullToRefreshListView) findViewById(R.id.list_comment);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_comment);
            setSupportActionBar(toolbar);
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //获取从ZhuanxiangFragment传过来的Item数据
        Intent result = getIntent();
        ZhuanXiangItem.ItemsEntity item = null;
        if (result != null) {
            Bundle bundle = result.getExtras();
            item = (ZhuanXiangItem.ItemsEntity) bundle.getSerializable("item");
            //Toast.makeText(CommentActivity.this, item.toString(), Toast.LENGTH_SHORT).show();

            if (item != null) {
                //获取Item的id，拼接comment接口
                commentId = item.getId();
            }
        }


        //Retrofit实现网络连接并获取数据
        HttpUtils.getCommentService()
                .getCommentList(String.valueOf(commentId), page)
                .enqueue(this);


        //获取ListView的headerView
        List<ZhuanXiangItem.ItemsEntity> header = new LinkedList();
        header.add(item);
        ZhuanXiangAdapter zhuanXiangAdapter = new ZhuanXiangAdapter(this);
        zhuanXiangAdapter.addAll(header);
        View headView = zhuanXiangAdapter.getItemView();

        //构建ListView
//        Toast.makeText(CommentActivity.this, headView.toString(), Toast.LENGTH_SHORT).show();
        listComment.getRefreshableView().addHeaderView(headView);
        adapter = new CommentAdapter(this);

        listComment.setAdapter(adapter);
        listComment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(CommentActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });

        listComment.setOnRefreshListener(this);
    }

    @Override
    public void onResponse(Response<CommentItem> response, Retrofit retrofit) {
        adapter.addAll(response.body().getItems());
        //currentCount = adapter.getCount();
        listComment.onRefreshComplete();
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Log.d(TAG, "onFailure: " + t.toString());
        listComment.setRefreshing(true);
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        //Toast.makeText(CommentActivity.this, "刷新", Toast.LENGTH_SHORT).show();
        page++;
        HttpUtils.getCommentService()
                .getCommentList(String.valueOf(commentId), page)
                .enqueue(this);
    }
}
