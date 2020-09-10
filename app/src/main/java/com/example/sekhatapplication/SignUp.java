package com.example.sekhatapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
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

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class SignUp extends Fragment implements View.OnClickListener {
    Handler handle = new Handler();
    ThreadImageChange imageChange;
    private final AtomicBoolean running = new AtomicBoolean(true);
    TextInputEditText emailTxt,nameTxt,phoneTxt,passwordTxt;
    FlatButton submitBtn;
    LinearLayout signupLayoutTxtHolder;
    Animation animationBackground;
    RelativeLayout signupProgress;
    SignupSignin mainActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    ImageView signupBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        mainActivity=new SignupSignin();
        signupLayoutTxtHolder=view.findViewById(R.id.signUpTextHolder);
        signupProgress=view.findViewById(R.id.signUpProgress);
        emailTxt=view.findViewById(R.id.emailSignupEditText);
        nameTxt=view.findViewById(R.id.nameSignupEditText);
        phoneTxt=view.findViewById(R.id.phoneSignupEditText);
        passwordTxt=view.findViewById(R.id.passwordSignupEditText);
        submitBtn=view.findViewById(R.id.signUpSubmit);
        submitBtn.setOnClickListener(this);
        signupBack = view.findViewById(R.id.signupBackImage);
        signupBack.setOnClickListener(this);
        animationBackground = AnimationUtils.loadAnimation(getContext(), R.anim.back_image_anim);
        imageChange=new ThreadImageChange(BitmapFactory.decodeResource(getResources(),R.drawable.back_test),
                BitmapFactory.decodeResource(getResources(),R.drawable.back_test_second));
        return view;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.signupBackImage:
                UIUtil.hideKeyboard(getActivity());
                break;
            case R.id.signInSubmit:
                if (signupCheck()){
                    handle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            signupProgress.setVisibility(View.VISIBLE);
                        }
                    },2500);
                    signupProgress.setVisibility(View.GONE);
                    startActivity(new Intent(getContext(),MainActivity.class));
                    mainActivity.finish();
                }
                else {
                    YoYo.with(Techniques.Tada)
                            .duration(200)
                            .repeat(1)
                            .playOn(signupLayoutTxtHolder);
                }
            default:
                break;
        }

    }

    private boolean signupCheck() {
        if (passwordTxt.getText().toString().trim().isEmpty()&&
                emailTxt.getText().toString().trim().isEmpty()&&
                nameTxt.getText().toString().trim().isEmpty()&&
                phoneTxt.getText().toString().trim().isEmpty()){
            if(passwordTxt.getText().toString().trim().isEmpty()){
                passwordTxt.setError("Password cant be empty");
            }
            if(emailTxt.getText().toString().trim().isEmpty()){
                emailTxt.setError("Email cant be empty");
            }
            if(nameTxt.getText().toString().trim().isEmpty()){
                nameTxt.setError("Name cant be empty");
            }
            if(phoneTxt.getText().toString().trim().isEmpty()){
                emailTxt.setError("Phone cant be empty");
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
                        signupBack.setImageBitmap(bitmaps.get(r.nextInt(2)));
                        signupBack.startAnimation(animationBackground);
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