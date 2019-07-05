package com.example.r2d2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.r2d2.api.Client;
import com.example.r2d2.api.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MainActivity";
    public static final String API_KEY="f12ccb38200a5b990c5e4a737390a165";
    Toolbar toolbar;
    Context context;
    TextView textView,overview,rating;
    EditText editText;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        textView=findViewById(R.id.movie_title);
        overview=findViewById(R.id.movie_overview);
        rating=findViewById(R.id.rating);
        editText=findViewById(R.id.search_bar);
        imageView=findViewById(R.id.movie_thumb);

        SearchManager sm= (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        Log.i(TAG,"Inside Oncreate");
        loadMovie();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.search) {

        }

        return super.onOptionsItemSelected(item);
    }

    private void loadMovie(){

        Service apiService=Client.getService();
        Call<MovieResponse> call = apiService.getPopularMovie(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, retrofit2.Response<MovieResponse> response) {
                List<MoviePost> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                String s1=response.body().getResults().get(3).getOrignalITitle();
                String s2= String.valueOf(response.body().getResults().get(3).getVoteAverage());
                String s3=response.body().getResults().get(3).getOverview();

                textView.setText(s1);
                overview.setText(s3);
                rating.setText(s2);

                /*String s1=response.body().getResults().get(2).getOrignalITitle();
                textView.setText(s1);*/
            }

            @Override
            public void onFailure(Call<MovieResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }
}
