# Android_Retrofit_Json_From_Array
Fetching Data From Json Array in the API Using Retrofit

This topic is a part of [My Complete Andorid Course](https://github.com/ananddasani/Android_Apps)

# Dependency
```
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

# Code

### ApiModelClass.java
- Main Class (The name should be exact of the API Data to be fetched)
```
public class ApiModelClass {

    String id, name, rating;

    public ApiModelClass(String id, String name, String rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
```

### JsonArrayResponse.java
- Stroing Array we fetched from the API (Array Model Class like)
```
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
```

### JSONPlaceholder Inerface
- Call will be of type JsonArrayResponse be we intended to fetch Array Response form the API
```
public interface JSONPlaceHolder {

    @GET("v1/73feb9e6-2c2a-4165-8766-3446c2c027aa")
    Call<JsonArrayResponse> getMoviesArray();
}

/*
Here we will not get List<>
we will get array so  --> Call<JsonArrayResponse> getMovies();
 */
```

#### MainActivity.java
```
button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JSONPlaceHolder jsonPlaceHolder = retrofit.create(JSONPlaceHolder.class);

                Call<JsonArrayResponse> call = jsonPlaceHolder.getMoviesArray();

                call.enqueue(new Callback<JsonArrayResponse>() {
                    @Override
                    public void onResponse(Call<JsonArrayResponse> call, Response<JsonArrayResponse> response) {

                        if (response.isSuccessful()) {

                            textView.setText("");
                            button.setVisibility(View.GONE);

                            JsonArrayResponse jsonArrayResponse = response.body();
                            movieList = new ArrayList<>(Arrays.asList(jsonArrayResponse.getMoviesArray()));

                            for (int i = 0; i < movieList.size(); i++)
                                textView.append("Id :: " + movieList.get(i).getId() + "\nName :: " + movieList.get(i).getName() + "\nRating :: " + movieList.get(i).getRating() + "\n\n\n");

                        } else
                            Toast.makeText(MainActivity.this, "Response Unsuccessful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<JsonArrayResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "onFailure Executed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
```

# App Highlight

![Retrofit Json From Array App1](https://user-images.githubusercontent.com/74413402/192093621-19d265d5-02b7-4e55-9870-f0afea401456.png)
![Retrofit Json From Array App2](https://user-images.githubusercontent.com/74413402/192093622-64e8478f-41eb-48b7-b45c-1db6a6f11d3b.png)
![Retrofit Json From Array Code](https://user-images.githubusercontent.com/74413402/192093624-e0e11b5c-ac64-4520-9005-512599411d07.png)

