package com.example.blogapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.blogapp.Activity.ArticleDetailPage;
import com.example.blogapp.Activity.HomeScreenActivity;
import com.example.blogapp.Model.Article;
import com.example.blogapp.R;

import java.util.List;

public class ArticleRecyclerView extends RecyclerView.Adapter<ArticleRecyclerView.MyViewHolder> {

    private static final String TAG = "ArticleRecyclerView";
    private RequestOptions options;
    private Context mContext;
    private List<Article> mData;

    public ArticleRecyclerView(Context mContext, List<Article> mData) {
        this.mContext = mContext;
        this.mData = mData;
        //options for image loading through glides
        this.options = new RequestOptions().centerCrop().placeholder(R.drawable.article_image_loading_background).error(R.drawable.article_image_loading_background);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        Log.d(TAG, "onCreateViewHolder: Started");
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.article_single_item,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ArticleDetailPage.class);
                i.putExtra("title", mData.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("subtitle", mData.get(viewHolder.getAdapterPosition()).getSubTitle());
                i.putExtra("img", mData.get(viewHolder.getAdapterPosition()).getImgUrl());
                i.putExtra("videoUrl", mData.get(viewHolder.getAdapterPosition()).getVideoUrl());
                i.putExtra("author", mData.get(viewHolder.getAdapterPosition()).getAuthor());
                i.putExtra("article_data", mData.get(viewHolder.getAdapterPosition()).getArticleData());
                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Started.");
        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.tvSubTitle.setText(mData.get(position).getSubTitle());

        //load image from the internet using Glide
        Log.d(TAG, "onBindViewHolder: Image Fetching started..");
        Glide.with(mContext)
                .load(mData.get(position).getImgUrl())
                .apply(options)
                .into(holder.ArticleThumbnail);
        Log.d(TAG, "onBindViewHolder: Image fetched through the Glide");

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //MyViewHolder class to hold the items from article screen
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSubTitle;
        ImageView ArticleThumbnail;
        LinearLayout view_container;


        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.articleSingleItem);
            tvTitle = itemView.findViewById(R.id.articleSingleItemTitle);
            tvSubTitle = itemView.findViewById(R.id.articleSingleItemSubTitle);
            ArticleThumbnail = itemView.findViewById(R.id.articleSingleItemImage);
        }
    }
}
