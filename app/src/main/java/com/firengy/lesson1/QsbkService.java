package com.firengy.lesson1;

import com.firengy.lesson1.entity.ZhuanXiangItem;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by firengy
 * on 15-12-29.
 * Email: 18811372352@163.com
 */
public interface QsbkService {
    @GET("article/list/{type}")
    Call<ZhuanXiangItem> getList(@Path("type") String type, @Query("page") int page);
}
