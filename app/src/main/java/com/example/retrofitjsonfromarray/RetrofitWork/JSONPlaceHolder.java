package com.example.retrofitjsonfromarray.RetrofitWork;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolder {

    @GET("v1/73feb9e6-2c2a-4165-8766-3446c2c027aa")
    Call<JsonArrayResponse> getMoviesArray();
}

/*
Here we will not get List<>
we will get array so  --> Call<JsonArrayResponse> getMovies();
 */