package com.example.tmdbtest.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdbtest.R;
import com.example.tmdbtest.ui.adapter.MovieAdapter;
import com.example.tmdbtest.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {
    private MovieViewModel movieViewModel;
    private MovieAdapter nowPlayingMoviesAdapter, popularMoviesAdapter, upcomingMoviesAdapter;
    RecyclerView rvNowPlaying, rvPopular, rvUpcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvNowPlaying = findViewById(R.id.rvNowPlaying);
        rvPopular = findViewById(R.id.rvPopular);
        rvUpcoming = findViewById(R.id.rvUpcoming);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getNowPlayingMovies(this);
        movieViewModel.getPopularMovies(this);
        movieViewModel.getUpcomingMovies(this);
        observeNowPlayingMovies();
        observePopularMovies();
        observeUpcomingMovies();
    }

    private void observeNowPlayingMovies() {
        movieViewModel.nowPlayingLiveData.observe(this, movieList -> {
            nowPlayingMoviesAdapter = new MovieAdapter(movieList);
            rvNowPlaying.setAdapter(nowPlayingMoviesAdapter);
        });
    }

    private void observePopularMovies() {
        movieViewModel.popularLiveData.observe(this, movieList -> {
            popularMoviesAdapter = new MovieAdapter(movieList);
            rvPopular.setAdapter(popularMoviesAdapter);
        });
    }

    private void observeUpcomingMovies() {
        movieViewModel.upcomingLiveData.observe(this, movieList -> {
            upcomingMoviesAdapter = new MovieAdapter(movieList);
            rvUpcoming.setAdapter(upcomingMoviesAdapter);
        });
    }

}