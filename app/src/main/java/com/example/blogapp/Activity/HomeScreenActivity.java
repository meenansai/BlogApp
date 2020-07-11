package com.example.blogapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.blogapp.Fragment.FavoritesFragment;
import com.example.blogapp.Fragment.HomeFragment;

import com.example.blogapp.Fragment.ProfileFragment;
import com.example.blogapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class HomeScreenActivity extends AppCompatActivity {
    private static final String TAG = "HomeScreenActivity";
    Context mContext = HomeScreenActivity.this;

    //views
    Toolbar mAppBar;

    // Initializing all Fragments
    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new FavoritesFragment();
    final Fragment fragment3 = new ProfileFragment();

    private FirebaseAuth mAuth;

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    private Context context = HomeScreenActivity.this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mAppBar = findViewById(R.id.topAppBar);

        mAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.appBar_logout:
                        mAuth.signOut();
                        Intent i = new Intent(mContext, MainActivity.class);
                        i.setFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
//                        mContext.finish();

//                        Toast.makeText(mContext, "Logout Pressed",Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

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
