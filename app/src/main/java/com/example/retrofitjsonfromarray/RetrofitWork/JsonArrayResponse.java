package com.example.retrofitjsonfromarray.RetrofitWork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonArrayResponse {

    @SerializedName("moviz")
    @Expose
    private ApiModelClass[] moviesArray;

    public JsonArrayResponse(ApiModelClass[] moviesArray) {
        this.moviesArray = moviesArray;
    }

    public ApiModelClass[] getMoviesArray() {
        return moviesArray;
    }

    public void setMoviesArray(ApiModelClass[] moviesArray) {
        this.moviesArray = moviesArray;
    }
}

/*
either you can name ApiModelClass[] moviz
 */