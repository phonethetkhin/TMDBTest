package com.example.tmdbtest.service;

import com.example.tmdbtest.model.response.MovieDetailModel;
import com.example.tmdbtest.model.response.MovieResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIs {
    @GET("movie/now_playing")
    Call<MovieResponseModel> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieResponseModel> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MovieResponseModel> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieDetailModel> getDetail(@Path("movie_id") int movieId, @Query("api_key") String apiKey);


}
