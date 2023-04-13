package com.example.tmdbtest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdbtest.R;
import com.example.tmdbtest.ui.adapter.MovieAdapter;
import com.example.tmdbtest.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {
    private MovieViewModel movieViewModel;
    private MovieAdapter nowPlayingMoviesAdapter, popularMoviesAdapter, upcomingMoviesAdapter;
    ImageView ivNowPlaying, ivPopular, ivUpcoming;
    RecyclerView rvNowPlaying, rvPopular, rvUpcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvNowPlaying = findViewById(R.id.rvNowPlaying);
        rvPopular = findViewById(R.id.rvPopular);
        rvUpcoming = findViewById(R.id.rvUpcoming);

        ivNowPlaying = findViewById(R.id.ivNowPlaying);
        ivPopular = findViewById(R.id.ivPopular);
        ivUpcoming = findViewById(R.id.ivUpcoming);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getNowPlayingMovies(this);
        movieViewModel.getPopularMovies(this);
        movieViewModel.getUpcomingMovies(this);
        observeNowPlayingMovies();
        observePopularMovies();
        observeUpcomingMovies();
        handleClicks();
    }


    private void observeNowPlayingMovies() {
        movieViewModel.nowPlayingLiveData.observe(this, movieList -> {
            nowPlayingMoviesAdapter = new MovieAdapter(this, movieList);
            rvNowPlaying.setAdapter(nowPlayingMoviesAdapter);
        });
    }

    private void observePopularMovies() {
        movieViewModel.popularLiveData.observe(this, movieList -> {
            popularMoviesAdapter = new MovieAdapter(this, movieList);
            rvPopular.setAdapter(popularMoviesAdapter);
        });
    }

    private void observeUpcomingMovies() {
        movieViewModel.upcomingLiveData.observe(this, movieList -> {
            upcomingMoviesAdapter = new MovieAdapter(this, movieList);
            rvUpcoming.setAdapter(upcomingMoviesAdapter);
        });
    }

    private void handleClicks() {
        ivNowPlaying.setOnClickListener(view -> {
            Intent i = new Intent(this, MoreMovieActivity.class);
            i.putExtra("status", 0);
            startActivity(i);
        });
        ivPopular.setOnClickListener(view -> {
            Intent i = new Intent(this, MoreMovieActivity.class);
            i.putExtra("status", 1);

            startActivity(i);
        });
        ivUpcoming.setOnClickListener(view -> {
            Intent i = new Intent(this, MoreMovieActivity.class);
            i.putExtra("status", 2);
            startActivity(i);
        });

    }

}