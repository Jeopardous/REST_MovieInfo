package com.example.r2d2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviePost {
    public MoviePost(String orignalITitle, String overview, Double voteAverage,String posterPath) {
        this.orignalITitle = orignalITitle;
        this.overview = overview;
        this.posterPath=posterPath;
        this.voteAverage = voteAverage;
    }


    @SerializedName("original_title")
    @Expose
    private String orignalITitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    public String getOrignalITitle() {
        return orignalITitle;
    }

    public void setOrignalITitle(String orignalITitle) {
        this.orignalITitle = orignalITitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public String toString() {
        return "MoviePost{" +
                "orignalITitle='" + orignalITitle + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}
