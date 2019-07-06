package com.example.r2d2;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.r2d2.api.Client;
import com.example.r2d2.api.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String API_KEY = "f12ccb38200a5b990c5e4a737390a165";
    Toolbar toolbar;
    List suggestList = new ArrayList();
    Adapter listadapter;
    Context context;
    ListView lv;
    TextView textView, overview, rating;
    EditText editText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        textView = findViewById(R.id.movie_title);
        overview = findViewById(R.id.movie_overview);
        rating = findViewById(R.id.rating);
        editText = findViewById(R.id.search_bar);
        imageView = findViewById(R.id.movie_thumb);
        lv = findViewById(R.id.search_list);
        Log.i(TAG, "Inside Oncreate");
        loadMovie("av");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView sv = (SearchView) menu.findItem(R.id.search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.search);

        sv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
        sv.setQueryHint("Movie Search...");
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length() > 2) {
                    loadMovie(s);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                loadMovie(s);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);
        return true;
    }

    private void loadMovie(String keyword) {

        Service apiService = Client.getService();
        Call<MovieResponse> call = apiService.getPopularMovie(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List<MoviePost> movieNameList = response.body().getResults();
                    Log.d(TAG, "Number of movies received: " + movieNameList.size());

                    String s1 = response.body().getResults().get(2).getOrignalITitle();
                    String s2 = String.valueOf(response.body().getResults().get(2).getVoteAverage());
                    String s3 = response.body().getResults().get(2).getOverview();

                    overview.setText(s3);
                    rating.setText(s2 + "/10");
                    textView.setText(s1);

                } else {
                    Toast.makeText(MainActivity.this, "faild to respond", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
       /*Call<MovieResponse> call= apiService.getBestSearch(keyword,API_KEY);
        call.enqueue(new retrofit2.Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, retrofit2.Response<MovieResponse> response) {
                if(response.isSuccessful())
                {
                    List<MoviePost> movieNameList=response.body().getResults();
                    Log.d(TAG, "Number of movies received: " + movieNameList.size());

                    for(int i=0;i<4;i++)
                    {
                        suggestList.add(response.body().getResults().get(i).getName());
                    }
                    listadapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,suggestList);
                    lv.setAdapter((ListAdapter) listadapter);
                }
                else{
                    Toast.makeText(MainActivity.this, "faild to respond", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MovieResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });*/

    }
}
