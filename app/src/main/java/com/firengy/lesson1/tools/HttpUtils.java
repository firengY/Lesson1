package com.firengy.lesson1.tools;

import com.firengy.lesson1.entity.CommentItem;
import com.firengy.lesson1.entity.ZhuanXiangItem;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by firengy
 * on 16-1-1.
 * Email: 18811372352@163.com
 */
public class HttpUtils {
    public interface QsbkService {
        @GET("article/list/{type}")
        Call<ZhuanXiangItem> getList(@Path("type") String type, @Query("page") int page);
    }

    public interface CommentService {
        @GET("article/{commentId}/comments")
        Call<CommentItem> getCommentList(@Path("commentId") String commentId, @Query("page") int page);
    }

    private static QsbkService qsbkService;
    private static CommentService commentService;

    static {
        qsbkService = new Retrofit.Builder()
                .baseUrl("http://m2.qiushibaike.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(QsbkService.class);

        commentService = new Retrofit.Builder()
                .baseUrl("http://m2.qiushibaike.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CommentService.class);
    }

    public static QsbkService getQsbkService(){
        return qsbkService;
    }

    public static CommentService getCommentService(){
        return commentService;
    }
}
