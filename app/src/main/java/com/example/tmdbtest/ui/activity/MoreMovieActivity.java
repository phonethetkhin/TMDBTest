package com.example.tmdbtest.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdbtest.R;
import com.example.tmdbtest.model.response.MovieModel;
import com.example.tmdbtest.ui.adapter.MovieAdapter;
import com.example.tmdbtest.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MoreMovieActivity extends AppCompatActivity {
    private int status = 0;
    private String title = "";
    private MovieViewModel movieViewModel;
    RecyclerView rvMoreMovies;
    private MovieAdapter moreMoviesAdapter;
    ProgressBar pbLoading;
    int pageNum = 1;
    int lastVisiblePosition = 0;
    private List<MovieModel> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_movie);
        rvMoreMovies = findViewById(R.id.rvMoreMovies);
        pbLoading = findViewById(R.id.pbLoading);
        status = getIntent().getIntExtra("status", 0);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        pbLoading.setVisibility(View.VISIBLE);
        setUI();
        fetchData();

        observeData();
        checkScroll();
    }

    private void setUI() {
        ActionBar actionbar = getSupportActionBar();
        if (status == 0) {
            title = "Now Playing Movies";
        } else if (status == 1) {
            title = "Popular Movies";
        } else {
            title = "Upcoming Movies";
        }

        actionbar.setTitle(title);
    }

    private void fetchData() {
        if (status == 0) {
            movieViewModel.getNowPlayingMovies(this, pageNum);
        } else if (status == 1) {
            movieViewModel.getPopularMovies(this, pageNum);
        } else {
            movieViewModel.getUpcomingMovies(this, pageNum);
        }
    }

    private void observeData() {
        if (status == 0) {
            observeNowPlayingMovies();
        } else if (status == 1) {
            observePopularMovies();
        } else {
            observeUpcomingMovies();
        }
    }

    private void observeNowPlayingMovies() {
        movieViewModel.nowPlayingLiveData.observe(this, movies -> {
            if (movies != null) {
                pbLoading.setVisibility(View.GONE);
                rvMoreMovies.setVisibility(View.VISIBLE);
                movieList.addAll(movies);
                moreMoviesAdapter = new MovieAdapter(this, movieList);
                rvMoreMovies.setAdapter(moreMoviesAdapter);
            }
        });
    }

    private void observePopularMovies() {
        movieViewModel.popularLiveData.observe(this, movies -> {
            if (movies != null) {
                pbLoading.setVisibility(View.GONE);
                rvMoreMovies.setVisibility(View.VISIBLE);
                movieList.addAll(movies);
                moreMoviesAdapter = new MovieAdapter(this, movieList);

                rvMoreMovies.setAdapter(moreMoviesAdapter);
            }
        });
    }

    private void observeUpcomingMovies() {
        movieViewModel.upcomingLiveData.observe(this, movies -> {
            if (movies != null) {
                pbLoading.setVisibility(View.GONE);
                rvMoreMovies.setVisibility(View.VISIBLE);
                movieList.addAll(movies);
                moreMoviesAdapter = new MovieAdapter(this, movieList);
                rvMoreMovies.setAdapter(moreMoviesAdapter);
            }

        });
    }

    private void checkScroll() {
        rvMoreMovies.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            GridLayoutManager layoutManager = (GridLayoutManager) rvMoreMovies.getLayoutManager();
            int visibleItemCount = layoutManager.findLastCompletelyVisibleItemPosition();
            if (visibleItemCount == movieList.size() - 1) {
                lastVisiblePosition = visibleItemCount;
                pageNum++;
                loadNextPage();
            }


        });
    }

    private void loadNextPage() {
        rvMoreMovies.scrollToPosition(lastVisiblePosition);
        pbLoading.setVisibility(View.VISIBLE);
        rvMoreMovies.setVisibility(View.GONE);
        fetchData();
    }

}