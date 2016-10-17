package com.example.neeraj.pg_guide;

import android.app.Activity;
import android.os.Handler;
// import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.*;

public class  MainActivity extends Activity {
    int splashtime=6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //this is for the ful screan
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);




        setContentView(R.layout.activity_main);
        startanim();
        Handler x= new Handler();
        x.postDelayed(new SplashActivity(), splashtime);    //this will call the function in 6000


    }
    public void startanim()
    {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animation);
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.animation1);
        anim.reset();   // to strat from starting
        anim1.reset();
        ImageView imageview = (ImageView) findViewById(R.id.logo);
        ImageView imageview1 = (ImageView) findViewById(R.id.logo1);
        imageview.startAnimation(anim);
        imageview1.startAnimation(anim1);
    }

    class SplashActivity implements Runnable
    {
        @Override
        public void run()
        {

            startActivity(new Intent(getApplicationContext(),Login.class));   //this is for to run next activity(Intent)
           // MainActivity.this.finish();
            Toast.makeText(getApplicationContext(), "Welcome to PG_GUIDE", Toast.LENGTH_SHORT).show();
        }

    }

}
