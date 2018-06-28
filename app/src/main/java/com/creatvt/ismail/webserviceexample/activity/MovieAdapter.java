package com.creatvt.ismail.webserviceexample.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.creatvt.ismail.webserviceexample.R;
import com.creatvt.ismail.webserviceexample.data.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private Context context;
    private ViewClickListener listener;
    public MovieAdapter(List<Movie> movies,Context context,ViewClickListener listener){
        this.movies = movies;
        this.context = context;
        this.listener = listener;
    }
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.txtName.setText(movie.getName());
        holder.txtRealName.setText(movie.getRealname());
        holder.txtTeam.setText(movie.getTeam());
        Glide.with(context).load(movie.getImageurl()).into(holder.imageMovie);
        holder.bind(movie,listener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView imageMovie;
        TextView txtName,txtRealName,txtTeam;
        View view;
        public MovieViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageMovie = itemView.findViewById(R.id.image_movie);
            txtName = itemView.findViewById(R.id.txt_name);
            txtRealName = itemView.findViewById(R.id.txt_real_name);
            txtTeam = itemView.findViewById(R.id.txt_team);

        }

        public void bind(final Movie movie,final ViewClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(movie,view);
                }
            });
        }
    }
}
