package com.example.neeraj.pg_guide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenovo on 7/13/2016.
 */
public class Search extends AppCompatActivity implements View.OnClickListener {
    Button b1;
    ListView l1;
    String records[]={};
    private ProgressDialog pDialog;
    JSONParser jParser=new JSONParser();
    JSONArray q=null;
    private static String url="http://www.negi.esy.es/pg/view1_data.php";
    //  ArrayList data;
    prop_detail pr[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listpg);
        b1=(Button)findViewById(R.id.button4);
        l1=(ListView)findViewById(R.id.listView);
        b1.setOnClickListener(this);
        ArrayAdapter<String>a=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,records);
        l1.setAdapter(a);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Intent t1 = new Intent(getApplicationContext(),Details.class);
                sessionmanager ss = new sessionmanager(Search.this);

                ss.createloginsession(pr[position]);
                startActivity(t1);



            }
        });

    }


    @Override
    public void onClick(View v) {
        new LoadAllProducts().execute();


    }
    String master="";
    class LoadAllProducts extends AsyncTask<String, String, String> {


        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Search.this);
            pDialog.setMessage("Loading questions. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            java.util.List<NameValuePair> params = new ArrayList<NameValuePair>();

            // params.add(new BasicNameValuePair("user",t1.getText().toString()));
            // params.add(new BasicNameValuePair("pass",t2.getText().toString()));



            JSONObject json = jParser.makeHttpRequest(url, "GET", params);

            try {
                // JSONObject json = new JSONObject(t);
                Log.d("ok",json.toString());

                int success = json.getInt("success");
                JSONArray p=null;

                if (success == 1) {
                    p=json.getJSONArray("property");
                    records = new String[p.length()];
                    pr = new prop_detail[p.length()];
                    //looping through all products
                    for(int i=0;i< p.length();i++){
                        JSONObject c=p.getJSONObject(i);
                        //storing each json item in variable..
                        String name=c.getString("name");
                        String city=c.getString("city");
                        String sector=c.getString("sector");
                        String phone=c.getString("phone");

                        master="name:" +name+ " city:"+city;
                        records[i]=master;
                        pr[i] = new prop_detail();
                        pr[i].setName(name);
                        pr[i].setCity(city);
                        pr[i].setSector(sector);
                        pr[i].setPhone(phone);
                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            // t3.setText(master);
            //   l1.setAdapter(a);
            ArrayAdapter<String>a=new ArrayAdapter<String>(Search.this,android.R.layout.simple_list_item_1,records);
            l1.setAdapter(a);

        }

    }
}
