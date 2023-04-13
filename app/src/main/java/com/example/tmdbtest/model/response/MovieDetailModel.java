package com.example.tmdbtest.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailModel {

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("genres")
    private List<GenresItem> genres;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("overview")
    private String overview;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("vote_average")
    private Object voteAverage;


    public String getOriginalLanguage() {
        return originalLanguage;
    }


    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public List<GenresItem> getGenres() {
        return genres;
    }


    public int getId() {
        return id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getOverview() {
        return overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Object getVoteAverage() {
        return voteAverage;
    }

}