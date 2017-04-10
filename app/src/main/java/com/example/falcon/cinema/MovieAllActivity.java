package com.example.falcon.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieAllActivity extends AppCompatActivity {
    private ListView listview;
    private MovieAdapter adapter;
    private ArrayList<MovieInfo> arrayList;
    private Socket mSocket;
    private JSONArray jsonarray;
    private Emitter.Listener getData = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        arrayList = new ArrayList<MovieInfo>();
                        jsonarray = data.getJSONArray("listmovie");
                        for (int i = 0; i < jsonarray.length(); i++) {
                            MovieInfo movies = new MovieInfo(
                                    jsonarray.getJSONObject(i).getString("name"),
                                    jsonarray.getJSONObject(i).getString("startday"),
                                    jsonarray.getJSONObject(i).getDouble("imdb"),
                                    jsonarray.getJSONObject(i).getInt("duration"),
                                    "http://192.168.16.109:3000" + jsonarray.getJSONObject(i).getString("image"),
                                    jsonarray.getJSONObject(i).getInt("ages"),
                                    jsonarray.getJSONObject(i).getInt("format")
                            );
                            arrayList.add(movies);
                        }
                        setMoviesList();
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };

    {
        try {
            mSocket = IO.socket("http://192.168.16.109:3000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_all);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listview = (ListView) findViewById(R.id.movie_list_view);
        mSocket.connect();
        mSocket.on("data", getData);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MovieAllActivity.this, MovieDetail.class);

                try {
                    i.putExtra("name", jsonarray.getJSONObject(position).getString("name"));
                    i.putExtra("duration", String.valueOf(jsonarray.getJSONObject(position).getInt("duration")));
                    i.putExtra("director", jsonarray.getJSONObject(position).getString("director"));
                    i.putExtra("actornactress", jsonarray.getJSONObject(position).getString("actornactress"));
                    i.putExtra("nation", jsonarray.getJSONObject(position).getString("nation"));
                    i.putExtra("language", jsonarray.getJSONObject(position).getString("language"));
                    i.putExtra("category", jsonarray.getJSONObject(position).getString("category"));
                    i.putExtra("startday", jsonarray.getJSONObject(position).getString("startday"));
                    i.putExtra("format", jsonarray.getJSONObject(position).getString("format"));
                    i.putExtra("imdb", String.valueOf(jsonarray.getJSONObject(position).getDouble("imdb")));
                    i.putExtra("urltrailer", jsonarray.getJSONObject(position).getString("urltrailer"));
                    i.putExtra("content", jsonarray.getJSONObject(position).getString("content"));
                    i.putExtra("poster", "http://192.168.16.109:3000" + jsonarray.getJSONObject(position).getString("image"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(i);
            }
        });

    }

    private void setMoviesList() {
        adapter = new MovieAdapter(this, R.layout.movie_item, arrayList);
        listview.setAdapter(adapter);
    }

}
