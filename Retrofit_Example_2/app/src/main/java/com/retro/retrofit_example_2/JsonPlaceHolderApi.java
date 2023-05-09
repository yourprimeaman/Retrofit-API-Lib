package com.retro.retrofit_example_2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("comments/{id}/comments")
    Call<List<Comment>> getcomments(@Path("id") int postId);
}
