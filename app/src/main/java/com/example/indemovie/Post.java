package com.example.indemovie;

public class Post {



    private String title;
    private String body;
    private Float star;

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    private String pw;

    public Post(String title, float star,String body,String pw) {

        this.title =title;
        this.star = star;
        this.body = body;
        this.pw=pw;

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }



    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }




}
