package com.example.falcon.cinema;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Admin on 4/4/2017.
 */

public class AccessServiceAPI {
    public String getJSONStringFromUrl_GET(String url) {
        JSONArray jsonArray = null;
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder;
        String line;
        String jsonString = "";
        try {
            URL u = new URL(url);
            httpURLConnection = (HttpURLConnection) u.openConnection();
            httpURLConnection.setRequestMethod("GET");
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            jsonString = stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // catch (ProtocolException e) {
        //            e.printStackTrace();
        //        }
        finally {
            httpURLConnection.disconnect();
        }

        return jsonString;
    }

    public JSONObject convertJSONString2Obj(String jsonString){
        JSONObject jObj=null;
        try{
            Log.w("convertJSONString2Obj","JsonString="+jsonString);
            jObj= new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jObj;
    }

}
