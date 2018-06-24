package com.creatvt.ismail.webserviceexample.service;

import com.creatvt.ismail.webserviceexample.data.DirectionResponse;
import com.creatvt.ismail.webserviceexample.data.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIInterface {
    @GET("demos/marvel")
    public Call<List<Movie>> getMovie();

    @GET()
    public Call<DirectionResponse> getDirectionData(@Url String url);


}
