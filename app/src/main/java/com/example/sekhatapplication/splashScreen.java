package com.example.sekhatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splashScreen extends AppCompatActivity {
    Animation splashScreenAnim;
    ImageView imageView;
    Handler handler=new Handler();
    Intent toMainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        toMainActivity=new Intent(getApplicationContext(),MainActivity.class);
        imageView=(ImageView)findViewById(R.id.splashScreenImg);
        splashScreenAnim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_screen_anim);
        imageView.startAnimation(splashScreenAnim);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(toMainActivity);
                        finish();
                    }
                },3000);
    }
}