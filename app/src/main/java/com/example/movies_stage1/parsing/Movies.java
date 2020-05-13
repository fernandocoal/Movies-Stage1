package com.example.movies_stage1.parsing;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Movies implements Parcelable{

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;


    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("popularity")
    private Double popularity;



    @SerializedName("vote_average")
    private Double voteAverage;

    public Movies(String posterPath, String overview, String releaseDate, String title, String backdropPath,
                  Double popularity, Double voteAverage, String originalLanguage, String originalTitle) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.title = title;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.originalLanguage = originalLanguage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }



    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(title);
        dest.writeDouble(voteAverage);
        dest.writeString(backdropPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);

    }

    private Movies(Parcel in){
        posterPath = in.readString();
        originalLanguage=in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        title = in.readString();
        voteAverage = in.readDouble();
        backdropPath = in.readString();
        originalTitle = in.readString();
    }


    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}