package com.example.blogapp.Activity;

import android.content.Context;
import android.os.Bundle;

import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.blogapp.Fragment.FavoritesFragment;
import com.example.blogapp.Fragment.HomeFragment;

import com.example.blogapp.Fragment.ProfileFragment;
import com.example.blogapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeScreenActivity extends AppCompatActivity {
    private static final String TAG = "HomeScreenActivity";

    // Initializing all Fragments
    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new FavoritesFragment();
    final Fragment fragment3 = new ProfileFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    private Context context = HomeScreenActivity.this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.mainFrameLayout, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.mainFrameLayout, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.mainFrameLayout, fragment1, "1").commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_articles:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

                case R.id.nav_favorites:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;

                case R.id.nav_profile:
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;
            }
            return false;
        }
    };
}
