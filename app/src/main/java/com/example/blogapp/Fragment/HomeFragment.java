package com.example.blogapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private List<Article> lstArticle = new ArrayList<>();
    private RecyclerView myRecyclerView;

    List<DocumentSnapshot> myListOfDocuments;

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

//        addDocumentToFirestore(); [extract the string from eedit text and add it dynamically]

        myRecyclerView = view.findViewById(R.id.articleRecyclerView);
//        jsonCall(); [ for dummy data ]
        retreive();
//        setRecyclerViewAdapter(lstArticle);
        setRecyclerViewAdapter(lstArticle);
        return view;
    }

    /*
     * FireStore add document
     */
    private void addDocumentToFirestore() {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("title", "How to learn HTML/CSS/MERN Stack/JS");
        user.put("subtitle", "I am ultra thop in above stack!!! 😎");
        user.put("author", "Rahul Sai Pratap");
        user.put("img", "https://i.morioh.com/bb436ff064.png");
        user.put("videoUrl", "https://www.youtube.com/watch?v=7CqJlxBYj-M");
        user.put("article_data", "If you want to learn above stack , please dont hesitate to contact me");

// Add a new document with a generated ID
        db.collection("articles")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    /*
     * Retrieve Data from Firestore Collection
     */
    private void retreive() {
         final ArrayList<Article> tmpList = new ArrayList<>();
        db.collection("articles")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                Article article = new Article();
                                article.setTitle(document.getData().get("title").toString());
                                article.setSubTitle(document.getData().get("subtitle").toString());
                                article.setAuthor(document.getData().get("author").toString());
                                article.setImgUrl(document.getData().get("img").toString());
                                article.setVideoUrl(document.getData().get("videoUrl").toString());
                                article.setArticleData(document.getData().get("article_data").toString());
//                                Log.d(TAG, "onComplete: Article Title:" +article.getTitle());
                                tmpList.add(article);
                                lstArticle = tmpList;
                            }
                            setRecyclerViewAdapter(lstArticle);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    /*
     * Method for JsonCallRequest
     */
    public void jsonCall() {
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

                        Log.d(TAG, "onResponse: Article Name:" + jsonObject.getString("title"));
                        Log.d(TAG, "onResponse: Article Img Url:" + jsonObject.getString("img"));
                        Log.d(TAG, "onResponse: Article author:" + jsonObject.getString("author"));

                        tmpList.add(article);
                        lstArticle = tmpList;
                    } catch (JSONException e) {
                        Log.d(TAG, "onResponse: JSONException Raised: " + e.toString());
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
        final ArticleRecyclerView myAdapter = new ArticleRecyclerView(getContext(), lstArticle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setAdapter(myAdapter);
        myRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation()));
    }

}
