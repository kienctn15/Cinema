package com.example.falcon.cinema;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import android.util.Log;


public class SigninActivity extends AppCompatActivity {

    private EditText user,pass;
    private Button btn_signin, btn_signup;
    private InputStream is;
    private JSONObject jObj;
    private String json;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate_signin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user= (EditText) findViewById(R.id.edt_auth_signin_username);
        pass= (EditText) findViewById(R.id.edt_auth_signin_password);
        btn_signin= (Button) findViewById(R.id.btn_auth_signin_signin);
        btn_signup= (Button) findViewById(R.id.btn_auth_signin_signup);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(user.getText().toString()) || TextUtils.isEmpty(pass.getText().toString())){
                    final AlertDialog.Builder builder= new AlertDialog.Builder(SigninActivity.this);
                    builder.setTitle("ERROR");
                    builder.setMessage("Username and Password Empty");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else{
                    String userName = user.getText().toString();
                    String Password = pass.getText().toString();
                    new Signin().execute(userName,Password);
                }
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this,SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    class Signin extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SigninActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... args) {
            int success;
            String userName = user.getText().toString();
            String Password = pass.getText().toString();
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", userName));
                params.add(new BasicNameValuePair("password", Password));
                JSONObject jObject = makeHttpRequest(config.SIGNIN_URL, params);
                success = jObject.getInt("success");
                if (success == 1) {
                    config.USER = jObject.getString("user");
                    Intent intent = new Intent(SigninActivity.this,
                            HomeActivity.class);
                    finish();
                    startActivity(intent);
                    return jObject.getString("message");
                } else {
                    return jObject.getString("message");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            pDialog.dismiss();
            if(s!=null){
                Toast.makeText(SigninActivity.this,s,Toast.LENGTH_LONG).show();
            }
        }

        public JSONObject makeHttpRequest(String url, List<NameValuePair> name) {
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                post.setEntity(new UrlEncodedFormEntity(name));//set giá trị cho mảng post của php
                HttpResponse httpResponse = httpClient.execute(post);
                HttpEntity entity = httpResponse.getEntity();
                is = entity.getContent();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                json = sb.toString();//đọc giá trị trong json
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }
            try {
                jObj = new JSONObject(json);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return jObj;
        }
    }
}
