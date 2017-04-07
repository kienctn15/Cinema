package com.example.falcon.cinema;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SigninActivity extends AppCompatActivity {

    private EditText username, password;
    private Button btn_signin, btn_signup;
    private ProgressDialog progressDialog;
    private TextView tv_signin_forgot;

    Config config;

    private Emitter.Listener onSignin = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = (JSONObject) args[0];
                String data = null;
                try {
                    data = jsonObject.getString("result");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (data == "true") {
                    Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    HideDialog();
                }
            }
        });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate_signin);
        username = (EditText) findViewById(R.id.edt_auth_signin_username);
        password = (EditText) findViewById(R.id.edt_auth_signin_password);
        btn_signin = (Button) findViewById(R.id.btn_auth_signin_signin);
        btn_signup = (Button) findViewById(R.id.btn_auth_signin_signup);
        tv_signin_forgot= (TextView) findViewById(R.id.tv_auth_signin_forgot);

        config =new Config();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        config.Connect();
        config.mSocket.on("result_signin", onSignin);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.length()>0 && pass.length()>0) {
                    CheckSignin(user, pass);
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter username and password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        tv_signin_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ForgotActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CheckSignin(final String user, final String pass) {
        progressDialog.setMessage("Sign in.....");
        ShowDialog();
        config.mSocket.emit("signin", user, pass);

    }

    private void ShowDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

    }

    private void HideDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();

        }
    }
}
