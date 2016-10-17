package com.example.neeraj.pg_guide;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends Activity  implements OnClickListener
{
    Button b1,b2;
    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);

        t1=(TextView)findViewById(R.id.mobile);
        t2=(TextView)findViewById(R.id.name);
        t3=(TextView)findViewById(R.id.city);
        t4=(TextView)findViewById(R.id.sector);

        sessionmanager session;
        session= new sessionmanager(this);

        HashMap<String, String> user =session.getuserdetail();
        t2.setText(user.get("name"));
        t3.setText(user.get("city"));
       t4.setText(user.get("sector"));
        t1.setText(user.get("mobileno"));

          master=user.get("mobileno");
       //master="4343433434";

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);


    }
    String master="";
    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub

        switch(arg0.getId())
        {
            case R.id.button1:

                sendSmsByManager();
                break;
            case R.id.button2:

                try
                {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+master));
                    try {
                        startActivity(callIntent);
                    }catch (Exception e){}

                }catch(android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "mobile no. not valid",Toast.LENGTH_LONG).show();
                }


                break;
            case R.id.bb11:
                sessionmanager session = new sessionmanager(this);
                session.logoutuser();


                Intent m = new Intent(this,Login.class);
                startActivity(m);

                break;


        }


    }

    public void sendSmsByManager() {
        try {
            SmsManager t = SmsManager.getDefault();
            //t.sendTextMessage(destinationAddress, scAddress, text, sentIntent, deliveryIntent)
            t.sendTextMessage(master, null
                    ,
                    "contact me I am interested to contact u",
                    null,
                    null);
            Toast.makeText(this, "Your sms has successfully sent to "+master,Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
