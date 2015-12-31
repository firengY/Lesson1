package com.firengy.lesson1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.firengy.lesson1.adapter.CommentAdapter;
import com.firengy.lesson1.adapter.ZhuanXiangAdapter;
import com.firengy.lesson1.entity.CommentItem;
import com.firengy.lesson1.entity.ZhuanXiangItem;

import java.util.LinkedList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class CommentActivity extends AppCompatActivity implements Callback<CommentItem> {

    //private PullToZoomScrollViewEx scrollComment;
    private ListView listComment;
    private Call<CommentItem> call;
    private CommentAdapter adapter;

    private static final String TAG = "CommentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //scrollComment = (PullToZoomScrollViewEx) findViewById(R.id.scroll_comment);
        listComment = (ListView) findViewById(R.id.list_comment);

        //获取从ZhuanxiangFragment传过来的Item数据
        Intent result = getIntent();
        ZhuanXiangItem.ItemsEntity item = null;
        if (result != null) {
            Bundle bundle = result.getExtras();
            item = (ZhuanXiangItem.ItemsEntity)bundle.getSerializable("item");
            //Toast.makeText(CommentActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
        }

        //获取Item的id，拼接comment接口
        int commentId = item.getId();

        //Retrofit实现网络连接并获取数据
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://m2.qiushibaike.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CommentService service = build.create(CommentService.class);
        call = service.getCommentList(String.valueOf(commentId), 1);
        call.enqueue(this);


        //获取ListView的headerView
        List<ZhuanXiangItem.ItemsEntity> header = new LinkedList();
        header.add(item);
        ZhuanXiangAdapter zhuanXiangAdapter = new ZhuanXiangAdapter(this);
        zhuanXiangAdapter.addAll(header);
        View headView = zhuanXiangAdapter.getItemView();

        //构建ListView
//        Toast.makeText(CommentActivity.this, headView.toString(), Toast.LENGTH_SHORT).show();
        listComment.addHeaderView(headView);
        adapter = new CommentAdapter(this);

        listComment.setAdapter(adapter);
    }

    @Override
    public void onResponse(Response<CommentItem> response, Retrofit retrofit) {
        adapter.addAll(response.body().getItems());
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Log.d(TAG, "onFailure: " + t.toString());
    }
}
