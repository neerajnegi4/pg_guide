package com.example.neeraj.pg_guide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Signup extends AppCompatActivity implements View.OnClickListener{
    Button b1;
    EditText t1,t2;
    private ProgressDialog pDialog;

    JSONParser jParser = new JSONParser();

    JSONArray q = null;


    private static String url = "http://www.negi.esy.es/pg/start.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        b1 = (Button) findViewById(R.id.bb2);
        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);

        b1.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        u = t1.getText().toString();
        p = t2.getText().toString();
        zozo z1 =  new zozo();
        z1.execute();
    }
    String u="";
    String p="";
    String msg="kahani";
    class zozo extends AsyncTask<String, String, String> {


        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Signup.this);
            pDialog.setMessage("Signing up Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            java.util.List<NameValuePair> pr = new ArrayList<NameValuePair>();

            pr.add(new BasicNameValuePair("user", u));
            pr.add(new BasicNameValuePair("pass", p));


            JSONObject json
                    = jParser.makeHttpRequest(url, "GET", pr);

            try {
                // JSONObject json = new JSONObject(t);
                Log.d("ok", json.toString());

                int success = json.getInt("success");
                JSONArray p = null;
                if (success == 1) {
                    msg="User Registered";
                }else

                    msg = "invalid User try again";



        } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            Toast.makeText(Signup.this,msg, Toast.LENGTH_LONG).show();
            if (msg.equals("User Registered")) {
                Intent t1 = new Intent(Signup.this, Login.class);
                startActivity(t1);
            }

        }


    }}

