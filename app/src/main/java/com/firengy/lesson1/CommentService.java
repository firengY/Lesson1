package com.firengy.lesson1;

import com.firengy.lesson1.entity.CommentItem;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by firengy
 * on 15-12-31.
 * Email: 18811372352@163.com
 */
public interface CommentService {
    @GET("article/{commentId}/comments")
    Call<CommentItem> getCommentList(@Path("commentId") String commentId, @Query("page") int page);
}
