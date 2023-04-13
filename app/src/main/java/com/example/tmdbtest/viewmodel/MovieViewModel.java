package com.example.tmdbtest.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tmdbtest.model.response.MovieDetailModel;
import com.example.tmdbtest.model.response.MovieModel;
import com.example.tmdbtest.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {
    MovieRepository movieRepository = new MovieRepository();

    public LiveData<List<MovieModel>> nowPlayingLiveData = movieRepository.nowPlayingLiveData;
    public LiveData<List<MovieModel>> popularLiveData = movieRepository.popularLiveData;
    public LiveData<List<MovieModel>> upcomingLiveData = movieRepository.upcomingLiveData;
    public LiveData<MovieDetailModel> movieDetailLiveData = movieRepository.movieDetailLiveData;

    public void getNowPlayingMovies(Context context) {
        movieRepository.getNowPlayingMovies(context);
    }

    public void getPopularMovies(Context context) {
        movieRepository.getPopularMovie(context);
    }

    public void getUpcomingMovies(Context context) {
        movieRepository.getUpcomingMovies(context);
    }

    public void getDetail(int movieId, Context context) {
        movieRepository.getDetail(movieId, context);
    }

}
