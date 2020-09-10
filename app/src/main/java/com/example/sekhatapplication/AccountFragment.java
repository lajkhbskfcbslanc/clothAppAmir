package com.example.sekhatapplication;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AccountFragment extends Fragment implements colorIDs {
    ImageView backDown;
    View backUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_account, container, false);
        backDown = view.findViewById(R.id.backDown);
        backUp=view.findViewById(R.id.backTop);
        setBackColor(4);
        return view;
    }
    public void setBackColor(int i){
        backUp.setBackgroundColor(getResources().getColor(colorColor[i]));
        backDown.setColorFilter(getResources().getColor(colorColor[i]), PorterDuff.Mode.SRC_IN);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(colorColor[i]));


    }
}