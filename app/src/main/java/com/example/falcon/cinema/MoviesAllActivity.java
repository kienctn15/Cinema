package com.example.falcon.cinema;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class MoviesAllActivity extends AppCompatActivity {

    private ListView listview;
    private MovieAdapter adapter;
    private ArrayList<MovieInfo> arrayList;
    private Socket mSocket;

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
        setContentView(R.layout.activity_movies_all);
        listview = (ListView) findViewById(R.id.movie_list_view);
        mSocket.connect();
        mSocket.on("data",getData);
    }
    private Emitter.Listener getData = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        String name = data.getString("name");
                        Toast.makeText(getApplicationContext(), ""+ name +"", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };
}
