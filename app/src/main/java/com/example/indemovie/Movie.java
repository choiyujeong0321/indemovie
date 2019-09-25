package com.example.indemovie;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Movie {

    private String ACTOR;
    private String AGING;
    private String DIRECTOR;
    private String MOVIE_NM;
    private String RUNTIME;
    private String POSTER;
    private String SUMMARY;


    public String getPOSTER() {
        return POSTER;
    }

    public void setPOSTER(String POSTER) {
        this.POSTER = POSTER;
    }

    public String getACTOR() {
        return ACTOR;
    }

    public void setACTOR(String ACTOR) {
        this.ACTOR = ACTOR;
    }

    public String getAGING() {
        return AGING;
    }

    public void setAGING(String AGING) {
        this.AGING = AGING;
    }

    public String getDIRECTOR() {
        return DIRECTOR;
    }

    public void setDIRECTOR(String DIRECTOR) {
        this.DIRECTOR = DIRECTOR;
    }


    public String getMOVIVE_NM() {
        return MOVIE_NM;
    }

    public void setMOVIVE_NM(String MOVIVE_NM) {
        this.MOVIE_NM = MOVIVE_NM;
    }

    public String getRUNTIME() {
        return RUNTIME;
    }

    public void setRUNTIME(String RUNTIME) {
        this.RUNTIME = RUNTIME;
    }

    public String getSUMMARY() {
        return SUMMARY;
    }

    public void setSUMMARY(String SUMMARY) {
        this.SUMMARY = SUMMARY;
    }

    public Movie() {
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }


    @NonNull
    @Override
    public String toString() {
        return "\n\n 감독: " + DIRECTOR + "\n 출연: " + ACTOR + "\n 관람등급: " + AGING + "\n 상영시간: " + RUNTIME + "\n\n";
    }
}
