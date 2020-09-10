package com.example.sekhatapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MainPageAdapter extends FragmentStatePagerAdapter {

    public MainPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new TshirtFrag();
            case 1:return new HoodiFrag();
            case 2:return new TrousersFrag();
            case 3:return new ShoesFrag();
            case 4:return new AccessoryFrag();
            case 5:return new DecorativeFrag();
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 6;
    }
}
