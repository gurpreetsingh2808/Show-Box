package com.popular_movies.domain.movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Gurpreet on 2/21/2016.
 */

@Builder
@Getter
@Setter
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


    @SimpleSQLColumn("col_vote_average")
    private Float vote_average;

    @SimpleSQLColumn("col_backdrop")
    private String backdrop_path;

    private Float popularity;

    private Integer vote_count;

    private Boolean video;



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
        vote_average = in.readFloat();
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
        dest.writeFloat(vote_average);
    }


    public Movie(String title, String description, String thumbnailURL, String wideThumbnailURL, Float vote_average, String release_date, int id) {
        this.original_title = title;
        this.overview = description;
        this.poster_path = thumbnailURL;
        this.backdrop_path = wideThumbnailURL;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.id = id;
    }

}
