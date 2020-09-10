package com.example.sekhatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SignupSignin extends AppCompatActivity implements View.OnClickListener {
    FrameLayout frm;
    SignIn signin;
    SignUp signup;
    Button signupButton,signinButton,closeSignBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.greyAppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_signin);

        signinButton=findViewById(R.id.signInBtn);
        signupButton=findViewById(R.id.signUpBtn);
        closeSignBtn=findViewById(R.id.signinSignupClose);
        signinButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);
        closeSignBtn.setOnClickListener(this);

        frm=(FrameLayout) findViewById(R.id.signInFrame);
        signin=new SignIn();
        signup=new SignUp();

        setFrag(signin);

    }

    public void setFrag(Fragment frg){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.signInFrame,frg).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signInBtn :
                setFrag(signin);
                break;
            case R.id.signUpBtn:
                setFrag(signup);
                break;
            case R.id.signinSignupClose:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}