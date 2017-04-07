package com.example.falcon.cinema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Admin on 4/6/2017.
 */


public class SignupActivity extends AppCompatActivity {

    private EditText edt_username, edt_password, edt_email, edt_phone, edt_fullname, edt_passport, edt_address;
    private Button btn_signin, btn_signup;
    private ProgressDialog progressDialog;
   Config config;


    private Emitter.Listener onSignup = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0];
                    String data = null;
                    try {
                        data = jsonObject.getString("result1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
                    if (data == "true") {
                        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                        startActivity(intent);

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
        setContentView(R.layout.activity_authenticate_signup);

        FindViewByID();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        config = new Config();
        config.Connect();
        config.mSocket.on("result_signup", onSignup);

        ButtonEvent();
    }

    private void FindViewByID() {
        edt_username = (EditText) findViewById(R.id.edt_auth_signup_username);
        edt_password = (EditText) findViewById(R.id.edt_auth_signup_password);
        edt_email = (EditText) findViewById(R.id.edt_auth_signup_email);
        edt_phone = (EditText) findViewById(R.id.edt_auth_signup_phone);
        edt_fullname = (EditText) findViewById(R.id.edt_auth_signup_fullname);
        edt_passport = (EditText) findViewById(R.id.edt_auth_signup_passport);
        edt_address = (EditText) findViewById(R.id.edt_auth_signup_address);

        btn_signin = (Button) findViewById(R.id.btn_auth_signup_signin);
        btn_signup = (Button) findViewById(R.id.btn_auth_signup);

    }

    private void ButtonEvent() {
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_username.getText().toString().trim();
                String pass = edt_password.getText().toString().trim();
                String email =edt_email.getText().toString().trim() ;
                String phone = edt_phone.getText().toString().trim();
                String fullname =  edt_fullname.getText().toString().trim();
                String passport = edt_passport.getText().toString().trim();
                String address =  edt_address.getText().toString().trim();


                if (user.length() > 0 && pass.length() > 0 && email.length() > 0 && phone.length() > 0 && fullname.length() > 0 && passport.length() > 0 && address.length() > 0) {
                    if (CheckValidEmail(email)) {
                        try {
                            progressDialog.setMessage("Sign up....");
                            ShowDialog();
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("user",user);
                            jsonObject.put("pass",pass );
                            jsonObject.put("email",email );
                            jsonObject.put("phone", phone);
                            jsonObject.put("fullname",fullname);
                            jsonObject.put("passport", passport);
                            jsonObject.put("address",address);
                            jsonObject.toString();
                            config.mSocket.emit("signup", jsonObject);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Please Enter Valid Email. ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Information.", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    private boolean CheckValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern)) {
            return true;
        }
        return false;
    }


}
