package com.example.blogapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.blogapp.Adapter.ArticleRecyclerView;
import com.example.blogapp.Model.Article;
import com.example.blogapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private List<Article> lstArticle = new ArrayList<>();
    private RecyclerView myRecyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container, false);
        myRecyclerView = view.findViewById(R.id.articleRecyclerView);
        jsonCall();
        setRecyclerViewAdapter(lstArticle);
        return view;
    }

    /*
     * Method for JsonCallRequest
     */
    public void jsonCall(){
        final List<Article> tmpList = new ArrayList<>();
        Log.d(TAG, "jsonCall: Method Called");
        String JSON_URL = "https://raw.githubusercontent.com/RakeshSirikonda/json1/master/blogapp_article_dummy_data.json";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "onResponse: Started");
                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Article article = new Article();
                        article.setTitle(jsonObject.getString("title"));
                        article.setSubTitle(jsonObject.getString("subtitle"));
                        article.setAuthor(jsonObject.getString("author"));
                        article.setImgUrl(jsonObject.getString("img"));
                        article.setVideoUrl(jsonObject.getString("videoUrl"));
                        article.setArticleData(jsonObject.getString("article_data"));

                        Log.d(TAG, "onResponse: Article Name:" +jsonObject.getString("title"));
                        Log.d(TAG, "onResponse: Article Img Url:" +jsonObject.getString("img"));
                        Log.d(TAG, "onResponse: Article author:" +jsonObject.getString("author"));

                        tmpList.add(article);
                        lstArticle = tmpList;
                    } catch (JSONException e) {
                        Log.d(TAG, "onResponse: JSONException Raised: " +e.toString());
                        e.printStackTrace();
                    }
                }
                // Toast.makeText(getContext(), "Loaded Articles: " + lstArticle.size(), Toast.LENGTH_SHORT).show();

                setRecyclerViewAdapter(lstArticle);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(arrayRequest);
    }

    /*
     * Method for RecyclerViewAdapter (Attaching adapter to RecyclerView)
     */
    private void setRecyclerViewAdapter(final List<Article> lstArticle) {
        final ArticleRecyclerView myAdapter = new ArticleRecyclerView(getContext(),lstArticle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setAdapter(myAdapter);
        myRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation()));
    }

}
