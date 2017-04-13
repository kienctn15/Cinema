package com.example.falcon.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Optimus on 4/12/2017.
 */

public class BookingChairActivity extends AppCompatActivity implements View.OnClickListener {

    private Button a1, a2, a3, a4, a5, a6, a7, a8, b1, b2, b3, b4, b5, b6, b7, b8, c1, c2, c3, c4, c5, c6, c7, c8, d1, d2, d3, d4, d5, d6, d7, d8, s1, s2, s3, s4, s5, s6, s7, s8;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketbooking_chairs);

        a1 = (Button) findViewById(R.id.A1);
        a2 = (Button) findViewById(R.id.A2);
        a3 = (Button) findViewById(R.id.A3);
        a4 = (Button) findViewById(R.id.A4);
        a5 = (Button) findViewById(R.id.A5);
        a6 = (Button) findViewById(R.id.A6);
        a7 = (Button) findViewById(R.id.A7);
        a8 = (Button) findViewById(R.id.A8);
        b1 = (Button) findViewById(R.id.B1);
        b2 = (Button) findViewById(R.id.B2);
        b3 = (Button) findViewById(R.id.B3);
        b4 = (Button) findViewById(R.id.B4);
        b5 = (Button) findViewById(R.id.B5);
        b6 = (Button) findViewById(R.id.B6);
        b7 = (Button) findViewById(R.id.B7);
        b8 = (Button) findViewById(R.id.B8);
        c1 = (Button) findViewById(R.id.C1);
        c2 = (Button) findViewById(R.id.C2);
        c3 = (Button) findViewById(R.id.C3);
        c4 = (Button) findViewById(R.id.C4);
        c5 = (Button) findViewById(R.id.C5);
        c6 = (Button) findViewById(R.id.C6);
        c7 = (Button) findViewById(R.id.C7);
        c8 = (Button) findViewById(R.id.C8);
        d1 = (Button) findViewById(R.id.D1);
        d2 = (Button) findViewById(R.id.D2);
        d3 = (Button) findViewById(R.id.D3);
        d4 = (Button) findViewById(R.id.D4);
        d5 = (Button) findViewById(R.id.D5);
        d6 = (Button) findViewById(R.id.D6);
        d7 = (Button) findViewById(R.id.D7);
        d8 = (Button) findViewById(R.id.D8);
        s1 = (Button) findViewById(R.id.S1);
        s2 = (Button) findViewById(R.id.S2);
        s3 = (Button) findViewById(R.id.S3);
        s4 = (Button) findViewById(R.id.S4);
        s5 = (Button) findViewById(R.id.S5);
        s6 = (Button) findViewById(R.id.S6);
        s7 = (Button) findViewById(R.id.S7);
        s8 = (Button) findViewById(R.id.S8);

        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        a3.setOnClickListener(this);
        a4.setOnClickListener(this);
        a5.setOnClickListener(this);
        a6.setOnClickListener(this);
        a7.setOnClickListener(this);
        a8.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
        c7.setOnClickListener(this);
        c8.setOnClickListener(this);
        d1.setOnClickListener(this);
        d2.setOnClickListener(this);
        d3.setOnClickListener(this);
        d4.setOnClickListener(this);
        d5.setOnClickListener(this);
        d6.setOnClickListener(this);
        d7.setOnClickListener(this);
        d8.setOnClickListener(this);
        s1.setOnClickListener(this);
        s2.setOnClickListener(this);
        s3.setOnClickListener(this);
        s4.setOnClickListener(this);
        s5.setOnClickListener(this);
        s6.setOnClickListener(this);
        s7.setOnClickListener(this);
        s8.setOnClickListener(this);
        Intent intent = getIntent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.A1:
                Toast.makeText(getApplicationContext(), "A1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.A2:
                Toast.makeText(getApplicationContext(), "A2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.A3:
                Toast.makeText(getApplicationContext(), "A3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.A4:
                Toast.makeText(getApplicationContext(), "A4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.A5:
                Toast.makeText(getApplicationContext(), "A5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.A6:
                Toast.makeText(getApplicationContext(), "A2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.A7:
                Toast.makeText(getApplicationContext(), "A2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.A8:
                Toast.makeText(getApplicationContext(), "A2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.B1:

                break;
            case R.id.B2:

                break;
            case R.id.B3:

                break;
            case R.id.B4:

                break;
            case R.id.B5:

                break;
            case R.id.B6:

                break;
            case R.id.B7:

                break;
            case R.id.B8:

                break;
            case R.id.C1:

                break;
            case R.id.C2:

                break;
            case R.id.C3:

                break;
            case R.id.C4:

                break;
            case R.id.C5:

                break;
            case R.id.C6:

                break;
            case R.id.C7:

                break;
            case R.id.C8:

                break;
            case R.id.D1:

                break;
            case R.id.D2:

                break;
            case R.id.D3:

                break;
            case R.id.D4:

                break;
            case R.id.D5:

                break;
            case R.id.D6:

                break;
            case R.id.D7:

                break;
            case R.id.D8:

                break;

            case R.id.S1:

                break;
            case R.id.S2:

                break;
            case R.id.S3:

                break;
            case R.id.S4:

                break;
            case R.id.S5:

                break;
            case R.id.S6:

                break;
            case R.id.S7:

                break;
            case R.id.S8:

                break;
            default:
                break;
        }
    }
}
