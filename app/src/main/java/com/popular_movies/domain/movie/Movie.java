package com.popular_movies.domain.movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by Gurpreet on 2/21/2016.
 */

@Builder
@AllArgsConstructor
@SimpleSQLTable(table = "movie", provider = "MovieProvider")
public class Movie implements Parcelable {

    @SimpleSQLColumn("col_poster_path")
    private String poster_path;

    private Boolean adult;

    @SimpleSQLColumn("col_overview")
    private String overview;

    @SimpleSQLColumn("col_release_date")
    private String release_date;

    private List<Integer> genre_ids;

    @SimpleSQLColumn("col_id")
    private int id;

    @SimpleSQLColumn("col_title")
    private String original_title;

    private String original_language;

    private String title;

    @SimpleSQLColumn("col_backdrop")
    private String backdrop_path;

    private Float popularity;

    private Integer vote_count;

    private Boolean video;

    @SimpleSQLColumn("col_vote_average")
    private Float vote_average;



    public Movie() {
    }

    protected Movie(Parcel in) {
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        id = in.readInt();
        original_title = in.readString();
        original_language = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Float getVote_average() {
        return vote_average;
    }

    public void setVote_average(Float vote_average) {
        this.vote_average = vote_average;
    }


    public Movie(String title, String description, String thumbnailURL, String wideThumbnailURL, Float userRatings, String release_date, int id) {
        this.original_title = title;
        this.overview = description;
        this.poster_path = thumbnailURL;
        this.backdrop_path = wideThumbnailURL;
        this.vote_average = userRatings;
        this.release_date = release_date;
        this.id = id;
    }


//    public Movie(Parcel in) {
//        original_title = in.readString();
//        overview = in.readString();
//        poster_path = in.readString();
//        backdrop_path = in.readString();
//        vote_average = in.readFloat();
//        release_date = in.readString();
//        id = in.readInt();
//    }

//    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
//        @Override
//        public Movie createFromParcel(Parcel in) {
//            return new Movie(in);
//        }
//
//        @Override
//        public Movie[] newArray(int size) {
//            return new Movie[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(original_title);
//        dest.writeString(overview);
//        dest.writeString(poster_path);
//        dest.writeString(backdrop_path);
//        dest.writeFloat(vote_average);
//        if(release_date != null) {
//            dest.writeString(release_date);
//        }
//        dest.writeInt(id);
//    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeInt(id);
        dest.writeString(original_title);
        dest.writeString(original_language);
        dest.writeString(title);
        dest.writeString(backdrop_path);
    }
}
