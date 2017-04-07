package com.example.falcon.cinema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ForgotActivity extends AppCompatActivity {

    private EditText edt_email;
    private Button btn_sendcode;
    private TextView tv_incorrectEmail;
    private ProgressDialog progressDialog;
    Config config;

    private Emitter.Listener result_findByEmail = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0];
                    String data = null;
                    try {
                        data = jsonObject.getString("result_findByEmail");
                        if (data == "true") {
                            tv_incorrectEmail.setText("correct Email.");

                        } else {
                            tv_incorrectEmail.setText("Incorrect Email. Put your Email .");
                            HideDialog();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    };
    private Emitter.Listener result_sendmail = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0];
                    String data = null;
                    try {
                        data = jsonObject.getString("result1");
                        if (data == "true") {


                        } else {

                            HideDialog();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate_forgot);

        edt_email = (EditText) findViewById(R.id.edt_auth_forgot_email);
        btn_sendcode = (Button) findViewById(R.id.btn_auth_forgot_sendcode);
        tv_incorrectEmail = (TextView) findViewById(R.id.tv_auth_forgot_incorrectEmail);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        config = new Config();
        config.Connect();
        config.mSocket.on("result_findByEmail", result_findByEmail);
        config.mSocket.on("result_sendmail",result_sendmail);
        btn_sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_incorrectEmail.setText("");
                progressDialog.setMessage("Waiting.....");
                ShowDialog();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("email", edt_email.getText().toString());
                    jsonObject.toString();

                    config.mSocket.emit("sendMailChangePass",jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
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
}
