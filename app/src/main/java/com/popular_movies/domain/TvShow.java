package com.popular_movies.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Gurpreet on 2/21/2016.
 */

////@SimpleSQLTable(table = "movie", provider = "MovieProvider")

public class TvShow implements Parcelable {

    //@SimpleSQLColumn("col_poster_path")
    private String poster_path;
    private String popularity;
    //@SimpleSQLColumn("col_id")
    private int id;
    //@SimpleSQLColumn("col_backdrop")
    private String backdrop_path;
    //@SimpleSQLColumn("col_vote_average")
    private String vote_average;
    //@SimpleSQLColumn("col_overview")
    private String overview;
    //@SimpleSQLColumn("col_release_date")
    private Date first_air_date;
    private String[] origin_country;
    private Integer[] genre_ids;
    private String original_language;
    private String vote_count;
    private String name;
    //@SimpleSQLColumn("col_title")
    private String original_name;


    public TvShow() {
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(Integer[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }


    public TvShow(String title, String description, String thumbnailURL, String wideThumbnailURL, String userRatings, Date release_date, int id) {
        this.original_name = title;
        this.overview = description;
        this.poster_path = thumbnailURL;
        this.backdrop_path = wideThumbnailURL;
        this.vote_average = userRatings;
        this.first_air_date = release_date;
        this.id = id;
    }


    public TvShow(Parcel in) {
        original_name = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        vote_average = in.readString();
        first_air_date = new Date(in.readLong());
        id = in.readInt();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        //@Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        //@Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    //@Override
    public int describeContents() {
        return 0;
    }

    //@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original_name);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(vote_average);
        if(first_air_date != null) {
            dest.writeLong(first_air_date.getTime());
        }
        dest.writeInt(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public Date getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(Date first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String[] getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String[] origin_country) {
        this.origin_country = origin_country;
    }
}
