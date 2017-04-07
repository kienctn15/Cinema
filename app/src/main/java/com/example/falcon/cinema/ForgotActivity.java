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

    private Emitter.Listener sendMailChangePass = new Emitter.Listener() {
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
                            Toast.makeText(getApplicationContext(), "COmplete", Toast.LENGTH_SHORT).show();
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
        config.mSocket.on("result_findByEmail", sendMailChangePass);

        btn_sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Waiting.....");
                ShowDialog();
                config.mSocket.emit("sendMailChangePass", edt_email);
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
