package com.example.sekhatapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.textfield.TextInputEditText;
import com.hkm.ui.processbutton.FlatButton;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import es.dmoral.toasty.Toasty;

import static androidx.constraintlayout.motion.utils.Oscillator.SIN_WAVE;
import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class SignIn extends Fragment implements View.OnClickListener {
    RelativeLayout signInProgress;
    ImageView signinBack;
    private final AtomicBoolean running = new AtomicBoolean(true);
    Handler handle = new Handler();
    TextInputEditText emailTxt,passwordTxt;
    ThreadImageChange imageChange;
    Animation animationBackground;
    FlatButton submitBtn;
    LinearLayout signinLayoutTxtHolder;
    SignupSignin mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_in, container, false);
        mainActivity=new SignupSignin();
        signinLayoutTxtHolder=view.findViewById(R.id.signInTextHolder);
        signInProgress=view.findViewById(R.id.signInProgress);
        signinBack = view.findViewById(R.id.signinBackImage);
        emailTxt=view.findViewById(R.id.emailSigninEditText);
        passwordTxt=view.findViewById(R.id.passwordSigninEditText);
        submitBtn=view.findViewById(R.id.signInSubmit);
        submitBtn.setOnClickListener(this);
        animationBackground = AnimationUtils.loadAnimation(getContext(), R.anim.back_image_anim);
        signinBack.setOnClickListener(this);
        imageChange=new ThreadImageChange(BitmapFactory.decodeResource(getResources(),R.drawable.back_test),
                BitmapFactory.decodeResource(getResources(),R.drawable.back_test_second));
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signinBackImage:
                UIUtil.hideKeyboard(getActivity());
                break;
            case R.id.signInSubmit:
                if (signInCheck()){
                    handle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            signInProgress.setVisibility(View.VISIBLE);
                        }
                    },2500);

                    signInProgress.setVisibility(View.GONE);
                    startActivity(new Intent(getContext(),MainActivity.class));
                    mainActivity.finish();
                }
                else {
                    YoYo.with(Techniques.Tada)
                            .duration(200)
                            .repeat(1)
                            .playOn(signinLayoutTxtHolder);
                }

            default:
                break;
        }
    }
    //injaro ba database ok kn vli fln
    private boolean signInCheck() {
        if (passwordTxt.getText().toString().trim().isEmpty()&&
        emailTxt.getText().toString().trim().isEmpty()){
            if(passwordTxt.getText().toString().trim().isEmpty()){
                passwordTxt.setError("Password cant be empty");
            }
            if(emailTxt.getText().toString().trim().isEmpty()){
                emailTxt.setError("Email cant be empty");
            }
            return false;
        }
        else{
            if (passwordTxt.getText().toString().trim().length()>7) {
                return true;
            }
            else {
                passwordTxt.setError("it must be more than 8");
                return false;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        running.set(true);
        imageChange.start();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        running.set(false);
        Log.i(TAG, "onPause: ");
    }

    private class ThreadImageChange extends Thread {

        List<Bitmap> bitmaps = new ArrayList<>();
        Runnable changeBackImage;

        public ThreadImageChange(Bitmap... bitmaps) {
            for (int i = 0; i < bitmaps.length; i++) {
                this.bitmaps.add(bitmaps[i]);
            }
        }

        Random r = new Random();
        int i = r.nextInt(2);
        int qbli = 0;

        @Override
        public void run() {
            super.run();
            changeBackImage = new Runnable() {
                @Override
                public void run() {

                    if (i != qbli) {
                        signinBack.setImageBitmap(bitmaps.get(r.nextInt(2)));
                        signinBack.startAnimation(animationBackground);
                        qbli = i;
                    } else {
                        if (running.get()) {
                            i = r.nextInt(2);
                        }
                    }
                    Log.i(TAG, "onPause: ");
                    handle.postDelayed(changeBackImage
                            , 500);

                }
            };
            if (running.get()) {
                handle.postDelayed(changeBackImage
                        , 1000);
            }
        }
    }

    }
