package com.example.falcon.cinema;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

//import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;
//YouTubeBaseActivity

public class MovieDetail extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    public String API_KEY = "AIzaSyBZj3624Oh1C0-sib0dqQn0xPGqdEA5LCk";
    public String VIDEO_ID = "rJdTUnrrISk";
    private TextView name, duration, director, actor, country, language, genres, date, imdb, format, content;
    private ImageView poster;
    private YouTubePlayerView trailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

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
        poster = (ImageView) findViewById(R.id.img_movie_detail_poster);
        trailer = (YouTubePlayerView) findViewById(R.id.vid_movie_detail_trailer);
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
        if (i.getStringExtra("format") == "1") {
            format.setText("Format: 2D");
        } else {
            format.setText("Format: 3D");
        }
        VIDEO_ID = i.getStringExtra("urltrailer");
        VIDEO_ID = VIDEO_ID.split("v=")[1];

        content.setText(i.getStringExtra("content"));
        trailer.initialize(API_KEY, MovieDetail.this);

        /*URL url = null;
        try {
            url = new URL(i.getStringExtra("poster"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "" + url + "", Toast.LENGTH_SHORT).show();*/
        Picasso.with(MovieDetail.this).load(i.getStringExtra("poster")).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(poster);

    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (null == player) return;

        // Start buffering
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }

}
