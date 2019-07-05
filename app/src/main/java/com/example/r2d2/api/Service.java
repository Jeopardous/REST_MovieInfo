package com.example.r2d2.api;

import com.example.r2d2.MoviePost;
import com.example.r2d2.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovie(@Query("api_key") String apikey);

    @GET("search/keyword")
    Call<MovieResponse> getBestSearch(@Query("key_word") String keyword,@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
