package com.example.blogapp.Model;

public class Article {

    private String title;
    private String subTitle;
    private String author;
    private String imgUrl;
    private String videoUrl;
    private String articleData;
    private boolean isFav;
    //Empty Constructors
    public Article() {
    }

    //Constructor with arguments
    public Article(String title, String subTitle, String author, String imgUrl, String videoUrl, String articleData,boolean isFav) {
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.articleData = articleData;
        this.isFav = isFav;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    //Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getArticleData() {
        return articleData;
    }

    public void setArticleData(String articleData) {
        this.articleData = articleData;
    }

    //toSTring
    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", author='" + author + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", articleData='" + articleData + '\'' +
                '}';
    }
}

