package com.example.blogapp.Activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.blogapp.Adapter.ArticleRecyclerView;
import com.example.blogapp.Fragment.FavoritesFragment;
import com.example.blogapp.Fragment.HomeFragment;
import com.example.blogapp.Model.Article;
import com.example.blogapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {
    private static final String TAG = "HomeScreenActivity";

    // Initializing all Fragments
    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new FavoritesFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    private Context context = HomeScreenActivity.this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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
//                    getSupportActionBar().setTitle("Vineyard Ministries");
                    active = fragment1;
                    return true;

                case R.id.nav_favorites:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
//                    getSupportActionBar().setTitle("Songs");
                    active = fragment2;
                    return true;
            }
            return false;
        }
    };
}
