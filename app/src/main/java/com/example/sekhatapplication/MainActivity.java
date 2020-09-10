package com.example.sekhatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView homeNavBar;
    private MainFragment mainFragment;
    private ShopListFragment shopListFragment;
    private AccountFragment accountFragment;
    //check with sharedPreference if it is logged in or  not
    boolean loginCheck=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment=new MainFragment();
        shopListFragment=new ShopListFragment();
        accountFragment=new AccountFragment();
        homeNavBar=(BottomNavigationView) findViewById(R.id.homeNavBar);
        homeNavBar.inflateMenu(R.menu.menu_navbar_main);
        setFragment(mainFragment);
        homeNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_navbarmenu:

                        setFragment(mainFragment);
                        break;
                    case R.id.shop_navbarmenu:
                        setFragment(shopListFragment);
                        break;
                    case R.id.account_navbarmenu:
                        //check with sharedPreference if it is logged in or  not
                        if(loginCheck){
                            setFragment(accountFragment);
                        }
                        else {
                            startActivity(new Intent(getApplicationContext(),SignupSignin.class));
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }

        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolder,fragment).commit();
    }


}