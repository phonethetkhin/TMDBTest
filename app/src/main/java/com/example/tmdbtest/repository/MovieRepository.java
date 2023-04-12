package com.example.tmdbtest.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.tmdbtest.model.response.MovieModel;
import com.example.tmdbtest.model.response.MovieResponseModel;
import com.example.tmdbtest.service.APIs;
import com.example.tmdbtest.service.RetrofitObj;
import com.example.tmdbtest.util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    APIs apiService = RetrofitObj.getRetrofitInstance().create(APIs.class);

    public MutableLiveData<List<MovieModel>> nowPlayingLiveData = new MutableLiveData<>();
    public MutableLiveData<List<MovieModel>> popularLiveData = new MutableLiveData<>();
    public MutableLiveData<List<MovieModel>> upcomingLiveData = new MutableLiveData<>();

    public void getNowPlayingMovies(Context context) {

        Call<MovieResponseModel> callResponse = apiService.getNowPlayingMovies(Utils.API_KEY);

        callResponse.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                if (response.isSuccessful()) {
                    MovieResponseModel movieResponseModel = response.body();
                    nowPlayingLiveData.postValue(movieResponseModel.getResults());

                } else {
                    Toast.makeText(context, "Some error occurred!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {
                Toast.makeText(context, "Some error occurred!!!" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPopularMovie(Context context) {

        Call<MovieResponseModel> callResponse = apiService.getPopularMovies(Utils.API_KEY);

        callResponse.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                if (response.isSuccessful()) {
                    MovieResponseModel movieResponseModel = response.body();
                    popularLiveData.postValue(movieResponseModel.getResults());

                } else {
                    Toast.makeText(context, "Some error occurred!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {
                Toast.makeText(context, "Some error occurred!!!" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getUpcomingMovies(Context context) {

        Call<MovieResponseModel> callResponse = apiService.getUpcomingMovies(Utils.API_KEY);

        callResponse.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                if (response.isSuccessful()) {
                    MovieResponseModel movieResponseModel = response.body();
                    upcomingLiveData.postValue(movieResponseModel.getResults());

                } else {
                    Toast.makeText(context, "Some error occurred!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {
                Toast.makeText(context, "Some error occurred!!!" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
