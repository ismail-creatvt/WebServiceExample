package com.creatvt.ismail.webserviceexample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.creatvt.ismail.webserviceexample.R;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imgMovie;
    private TextView txtName,txtRealName,txtTeam,txtPublisher,txtCreatedBy,txtBio,txtFirstAppearance;
    private String imageUrl,name,realName,team,publisher,createdBy,bio,firstAppearance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        imageUrl = intent.getStringExtra("image_url");
        name = intent.getStringExtra("name");
        realName = intent.getStringExtra("real_name");
        team = intent.getStringExtra("team");
        publisher = intent.getStringExtra("publisher");
        createdBy = intent.getStringExtra("created_by");
        bio = intent.getStringExtra("bio");
        firstAppearance = intent.getStringExtra("first_appearance");

        imgMovie = findViewById(R.id.movie_detail_image);
        txtName = findViewById(R.id.movie_detail_name);
        txtRealName = findViewById(R.id.movie_detail_real_name);
        txtTeam = findViewById(R.id.movie_detail_team);
        txtPublisher = findViewById(R.id.movie_detail_publisher);
        txtCreatedBy = findViewById(R.id.movie_detail_created_by);
        txtFirstAppearance = findViewById(R.id.movie_detail_first_appearance);
        txtBio = findViewById(R.id.movie_detail_bio);

        Glide.with(this).load(imageUrl).into(imgMovie);
        txtName.setText(name);
        txtBio.setText(bio);
        txtPublisher.setText(publisher);
        txtCreatedBy.setText(createdBy);
        txtFirstAppearance.setText(firstAppearance);
        txtRealName.setText(realName);
        txtTeam.setText(team);

    }
}
