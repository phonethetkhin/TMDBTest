package com.example.tmdbtest.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieResponseModel{

	@SerializedName("page")
	private int page;

	@SerializedName("results")
	private List<MovieModel> results;


	public int getPage() {
		return page;
	}

	public List<MovieModel> getResults() {
		return results;
	}
}