package com.example.retrofitjsonfromarray;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitjsonfromarray.RetrofitWork.ApiModelClass;
import com.example.retrofitjsonfromarray.RetrofitWork.JSONPlaceHolder;
import com.example.retrofitjsonfromarray.RetrofitWork.JsonArrayResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    String url = "https://mocki.io/";
    List<ApiModelClass> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

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
    }
}