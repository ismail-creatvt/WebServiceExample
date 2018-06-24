package com.creatvt.ismail.webserviceexample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.creatvt.ismail.webserviceexample.R;

public class HomeActivity extends AppCompatActivity {

    CardView movieDetails,googleApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        movieDetails = findViewById(R.id.movie_response);
        googleApi = findViewById(R.id.google_api);

        movieDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        googleApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,RouteDistance.class);
                startActivity(intent);
            }
        });
    }
}
