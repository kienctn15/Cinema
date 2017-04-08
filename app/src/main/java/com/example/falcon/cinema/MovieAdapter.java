package com.example.falcon.cinema;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Optimus on 4/7/2017.
 */

public class MovieAdapter extends ArrayAdapter {

    Activity activity;
    int layout;
    ArrayList<MovieInfo> data;

    public MovieAdapter(Activity context, int resource, ArrayList<MovieInfo> object) {
        super(context, resource, object);
        activity = context;
        layout = resource;
        data = object;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = activity.getLayoutInflater().inflate(layout, null);

        ImageView img_movie_poster = (ImageView) v.findViewById(R.id.img_movie_poster);
        img_movie_poster.setImageResource(R.drawable.imagexample);

        TextView movie_name = (TextView) v.findViewById(R.id.movie_name);
        movie_name.setText(String.valueOf(data.get(position).getName()));

        TextView movie_release = (TextView) v.findViewById(R.id.movie_date_release);
        movie_release.setText(String.valueOf(data.get(position).getName()));

        TextView movie_imdb = (TextView) v.findViewById(R.id.movie_imdb_point);
        movie_imdb.setText(String.valueOf(data.get(position).getName()));

        TextView movie_duration = (TextView) v.findViewById(R.id.movie_duration);
        movie_duration.setText(String.valueOf(data.get(position).getName()));

        ImageView img_movie_age = (ImageView) v.findViewById(R.id.img_movie_age);
        img_movie_age.setImageResource(R.drawable.p);

        ImageView img_movie_format = (ImageView) v.findViewById(R.id.img_movie_format);
        img_movie_format.setImageResource(R.drawable.bg2d);

        return v;
    }
}
