package com.example.neeraj.pg_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by oops on 7/18/2016.
 */
public class Options extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.options);
        b1 = (Button) findViewById(R.id.button3);
        b2 = (Button) findViewById(R.id.button5);
b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button3:

                Intent tr = new Intent(Options.this, Search.class);
                startActivity(tr);

                break;

            case R.id.button5:
                Intent t = new Intent(Options.this, registered.class);
                startActivity(t);
                break;
        }
    }}

