package com.creatvt.ismail.webserviceexample.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.creatvt.ismail.webserviceexample.R;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imgMovie;
    private TextView txtName,txtRealName,txtTeam,txtPublisher,txtCreatedBy,txtBio,txtFirstAppearance;
    private String imageUrl,name,realName,team,publisher,createdBy,bio,firstAppearance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        supportPostponeEnterTransition();
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

        Glide.with(this).load(imageUrl).apply(new RequestOptions().dontAnimate()).listener(new RequestListener<Drawable>(){

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                startPostponedEnterTransition();
                return false;
            }

            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                startPostponedEnterTransition();
                return false;
            }


        }).into(imgMovie);
        txtName.setText(name);
        txtBio.setText(bio);
        txtPublisher.setText(publisher);
        txtCreatedBy.setText(createdBy);
        txtFirstAppearance.setText(firstAppearance);
        txtRealName.setText(realName);
        txtTeam.setText(team);

    }
}
