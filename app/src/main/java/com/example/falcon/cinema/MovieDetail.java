package com.example.falcon.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetail extends AppCompatActivity {

    private TextView name, duration, director, actor, country, language, genres, date, imdb, format, content;
    private ImageView poster;
    private WebView trailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView) findViewById(R.id.txt_movie_detail_name);
        duration = (TextView) findViewById(R.id.txt_movie_detail_duration);
        director = (TextView) findViewById(R.id.txt_movie_detail_director);
        actor = (TextView) findViewById(R.id.txt_movie_detail_actor);
        country = (TextView) findViewById(R.id.txt_movie_detail_country);
        language = (TextView) findViewById(R.id.txt_movie_detail_language);
        genres = (TextView) findViewById(R.id.txt_movie_detail_genres);
        date = (TextView) findViewById(R.id.txt_movie_detail_date_release);
        imdb = (TextView) findViewById(R.id.txt_movie_detail_imdb_point);
        format = (TextView) findViewById(R.id.txt_movie_detail_format);
        poster = (ImageView) findViewById(R.id.img_movie_poster);
        trailer = (WebView) findViewById(R.id.vid_movie_detail_trailer);
        content = (TextView) findViewById(R.id.txt_movie_detail_content);

        Intent i = getIntent();
        name.setText(i.getStringExtra("name"));
        duration.setText("Duration: " + i.getStringExtra("duration") + " mins");
        director.setText("Director: " + i.getStringExtra("director"));
        actor.setText("Actors and Actresses: " + i.getStringExtra("actornactress"));
        country.setText("Nation: " + i.getStringExtra("nation"));
        language.setText("Language: " + i.getStringExtra("language"));
        genres.setText("Genres: " + i.getStringExtra("category"));
        date.setText("Release date: " + i.getStringExtra("startday"));
        imdb.setText("IMDb: " + i.getStringExtra("imdb") + "/10");
        format.setText("Format: " + i.getStringExtra("format"));

        content.setText(i.getStringExtra("content"));


    }


}
