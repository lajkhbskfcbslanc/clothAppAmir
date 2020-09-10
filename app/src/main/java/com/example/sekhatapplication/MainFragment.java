package com.example.sekhatapplication;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.nicolettilu.hiddensearchwithrecyclerview.HiddenSearchWithRecyclerView;

public class MainFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        TabLayout tabLayout=(TabLayout) view.findViewById(R.id.maintablayout);
        final ViewPager viewPager=view.findViewById(R.id.mainviewpager);
        MainPageAdapter mAdapter=new MainPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}