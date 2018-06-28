package com.creatvt.ismail.webserviceexample.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.creatvt.ismail.webserviceexample.R;
import com.creatvt.ismail.webserviceexample.data.Movie;
import com.creatvt.ismail.webserviceexample.service.APIClient;
import com.creatvt.ismail.webserviceexample.service.APIInterface;

import java.util.List;


import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private APIInterface service;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        service = APIClient.getClient().create(APIInterface.class);

        Call<List<Movie>> call = service.getMovie();

        call.enqueue(new retrofit2.Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movie = response.body();
                MovieAdapter adapter = new MovieAdapter(movie,MainActivity.this,new ViewClickListener(){
                    public void onClick(Movie movie,View view){
                        showDetails(movie,view);
                    }
                });
                RecyclerView rvMovieList = findViewById(R.id.movie_list);
                rvMovieList.setAdapter(adapter);
                rvMovieList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                //rvMovieList.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
                progressBar.setVisibility(View.GONE);
            }

            public void showDetails(Movie movie,View view){
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,view.findViewById(R.id.image_movie),"image");
                intent.putExtra("name",movie.getName());
                intent.putExtra("real_name",movie.getRealname());
                intent.putExtra("team",movie.getTeam());
                intent.putExtra("bio",movie.getBio());
                intent.putExtra("publisher",movie.getPublisher());
                intent.putExtra("first_appearance",movie.getFirstappearance());
                intent.putExtra("created_by",movie.getCreatedby());
                intent.putExtra("image_url",movie.getImageurl());

                startActivity(intent,options.toBundle());
            }
            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.i("RETROFIT FAILURE",t.getStackTrace().toString());
            }
        });
    }

}
