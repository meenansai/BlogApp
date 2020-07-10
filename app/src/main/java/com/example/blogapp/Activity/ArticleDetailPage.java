package com.example.blogapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blogapp.Model.Article;
import com.example.blogapp.R;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
public class ArticleDetailPage extends AppCompatActivity {
    private static final String TAG = "ArticleDetailPage";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail_page);
        Article article = new Article();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.d(TAG, "onCreate: Title:" +extras.getString("title"));
            article.setTitle(extras.getString("title"));
            article.setSubTitle(extras.getString("subtitle"));
            article.setArticleData(extras.getString("article_data"));
            article.setAuthor(extras.getString("author"));
            article.setImgUrl(extras.getString("img"));
            article.setVideoUrl(extras.getString("videoUrl"));
        }

        //Image
        ImageView imgView = (ImageView) findViewById(R.id.articleDetailImageView);
        Glide.with(this).load(article.getImgUrl()).apply(new RequestOptions().centerCrop()).into(imgView);

        //Title
        TextView titletv = (TextView) findViewById(R.id.titleTextViewValue);
        titletv.setText(article.getTitle());

        //subtitle
        TextView subtitletv = (TextView) findViewById(R.id.subtitileTextViewValue);
        subtitletv.setText(article.getSubTitle());

        //author
        TextView authortv = (TextView) findViewById(R.id.authorTextViewValue);
        authortv.setText(article.getAuthor());

        //description
        TextView description = (TextView) findViewById(R.id.descriptionTextViewValue);
        description.setText(article.getArticleData());

        //subtitle
        TextView videoUrl = (TextView) findViewById(R.id.videoUrlTextViewValue);
        videoUrl.setText(article.getVideoUrl());
    }
}
