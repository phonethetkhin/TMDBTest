package com.example.tmdbtest.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tmdbtest.R;
import com.example.tmdbtest.model.response.GenresItem;
import com.example.tmdbtest.model.response.MovieDetailModel;
import com.example.tmdbtest.viewmodel.MovieViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.stream.Collectors;

public class DetailActivity extends AppCompatActivity {
    private MovieViewModel movieViewModel;
    private int movieId = 0;
    ImageView ivCoverPhoto;
    TextView tvName, tvGenre, tvRuntime, tvLanguage, tvVoteCount, tvVoteAvg, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        movieId = getIntent().getIntExtra("movieId", 0);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        movieViewModel.getDetail(movieId, this);
        observeMovieDetail();
    }

    private void initViews() {
        ivCoverPhoto = findViewById(R.id.ivCoverPhoto);

        tvName = findViewById(R.id.tvName);
        tvGenre = findViewById(R.id.tvGenre);
        tvRuntime = findViewById(R.id.tvRuntime);
        tvLanguage = findViewById(R.id.tvLanguage);
        tvVoteCount = findViewById(R.id.tvVoteCount);
        tvVoteAvg = findViewById(R.id.tvVoteAverage);
        tvDescription = findViewById(R.id.tvDescription);
    }

    private void observeMovieDetail() {
        movieViewModel.movieDetailLiveData.observe(this, movieDetailModel -> {
            setUI(movieDetailModel);
        });
    }

    private void setUI(MovieDetailModel movieDetailModel) {
        List<String> genreStringList = movieDetailModel.getGenres().stream().map(GenresItem::getName).collect(Collectors.toList());

        String genres = String.join(", ", genreStringList);

        Picasso.get().load("https://image.tmdb.org/t/p/original/" + movieDetailModel.getBackdropPath()).into(ivCoverPhoto);
        tvName.setText(movieDetailModel.getTitle());
        tvGenre.setText(genres);
        tvLanguage.setText(movieDetailModel.getOriginalLanguage());
        tvVoteCount.setText(movieDetailModel.getVoteCount() + " Vote Counts");
        tvVoteAvg.setText(movieDetailModel.getVoteAverage() + "");
        tvDescription.setText(movieDetailModel.getOverview() + "");


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}