package com.example.neeraj.pg_guide;

import android.app.ProgressDialog;
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
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by NEERAJ on 11-07-2016.
 */
public class registered  extends AppCompatActivity implements View.OnClickListener {
Button b1;
    EditText t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14;
    private ProgressDialog pDialog;  //it  is a class for dilog box
    JSONParser jParser = new JSONParser();  //it wilcl take data from server
    //JSONArray q = null;
    private static String url = "http://negi.esy.es/pg/Register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    b1=(Button)findViewById(R.id.bbb2);

        t1=(EditText) findViewById(R.id.name);
        t2=(EditText) findViewById(R.id.city);
        t3=(EditText) findViewById(R.id.sector);
        t4=(EditText) findViewById(R.id.address);
        t5=(EditText) findViewById(R.id.size);
        t6=(EditText) findViewById(R.id.floors);
        t7=(EditText) findViewById(R.id.rent);
        t8=(EditText) findViewById(R.id.facilities);
        t9=(EditText) findViewById(R.id.type);
        t10=(EditText) findViewById(R.id.sharing);
        t11=(EditText) findViewById(R.id.sharing_people);
        t12=(EditText) findViewById(R.id.terms);
        t13=(EditText) findViewById(R.id.phone);
        t14=(EditText) findViewById(R.id.email);

        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        name=t1.getText().toString();
        city=t2.getText().toString();
        sector=t3.getText().toString();
        address=t4.getText().toString();
        size=t5.getText().toString();
        floors=t6.getText().toString();
        rent=t7.getText().toString();
        facilities=t8.getText().toString();
        type=t9.getText().toString();
        sharing=t10.getText().toString();
        sharingp=t11.getText().toString();
        terms=t12.getText().toString();
        phone=t13.getText().toString();
        email=t14.getText().toString();

        new LoadAllProducts().execute();
    }
    String name="";
    String city="";
    String sector="";
    String address="";
    String size="";
    String floors="";
    String rent="";
    String facilities="";
    String type="";
    String sharing="";
    String sharingp="";
    String terms="";
    String phone="";
    String email="";


    class LoadAllProducts extends AsyncTask<String, String, String> { //LoadAllProducts is our class
        // angular bracket called generic which means which have no type ,they are user define type


        protected void onPreExecute() {   //to show dilog box
            super.onPreExecute();

            pDialog = new ProgressDialog(registered.this);
            pDialog.setMessage
                    ("Registering Please wait...");
            pDialog.setIndeterminate(false);   //it will run till doInBackground function take data from server

            pDialog.setCancelable(false); //user cannot disconnect it
            pDialog.show();

        }

        protected String doInBackground(String... args) { //it will give requst to the server in background

            java.util.List<NameValuePair> params = new ArrayList<NameValuePair>();
// parameter has value as well as name(NameValuePair)

            params.add(new BasicNameValuePair("name",name));

            params.add(new BasicNameValuePair("city",city));
            params.add(new BasicNameValuePair("sector",sector));
            params.add(new BasicNameValuePair("address",address));

            params.add(new BasicNameValuePair("size",size));
            params.add(new BasicNameValuePair("floors",floors));
            params.add(new BasicNameValuePair("rent",rent));

            params.add(new BasicNameValuePair("type",type));
            params.add(new BasicNameValuePair("sharing",sharing));
            params.add(new BasicNameValuePair("sharingp",sharingp));

            params.add(new BasicNameValuePair("terms",terms));
            params.add(new BasicNameValuePair("facilities",facilities));
            params.add(new BasicNameValuePair("phone",phone));

            params.add(new BasicNameValuePair("email",email));
         //   params.add(new BasicNameValuePair("image",image));


            JSONObject json = jParser.makeHttpRequest(url, "POST" +
                    "", params);
            //makeHttpRequest will give requst to given URL with "get" parameters
            //data com in JSON Format and go to json


            try {
                // JSONObject json = new JSONObject(t);

                Log.d("ok", json.toString());


                int s = json.getInt("success");
// value of success stored in s

                if (s == 1) {



                }

            } catch
                    (JSONException e) {
                e.printStackTrace();

            }
            return null;
        }

        /**
         * After completing background
         * task Dismiss the progress dialog
         **/
        protected void
        onPostExecute(String file_url) { //this function called automatically at the end
            pDialog.dismiss();  //dialog box closed

            Toast.makeText(registered.this,"PG Registered",Toast.LENGTH_LONG).show();

        }

    }
}



