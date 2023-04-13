package com.example.tmdbtest.model.response;

import com.google.gson.annotations.SerializedName;

public class GenresItem{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}