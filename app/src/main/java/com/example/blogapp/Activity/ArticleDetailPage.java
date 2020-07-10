package com.example.blogapp.Activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blogapp.R;

public class ArticleDetailPage extends AppCompatActivity {
    private static final String TAG = "ArticleDetailPage";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail_page);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.d(TAG, "onCreate: Title:" +extras.getString("title"));
        }
    }
}
