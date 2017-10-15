package com.popular_movies.domain.tv;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Gurpreet on 2/21/2016.
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@SimpleSQLTable(table = "tvShows", provider = "MovieProvider")

public class TvShow implements Parcelable {

    @SimpleSQLColumn("col_poster_path")
    private String poster_path;

    private Float popularity;

    @SimpleSQLColumn("col_id")
    private int id;

    @SimpleSQLColumn("col_backdrop")
    private String backdrop_path;

    @SimpleSQLColumn("col_vote_average")
    private Float vote_average;

    @SimpleSQLColumn("col_overview")
    private String overview;

    @SimpleSQLColumn("col_first_air_date")
    private String first_air_date;

    private String[] origin_country;
    private List<Integer> genre_ids;
    private String original_language;
    private Integer vote_count;

    private String name;

    @SimpleSQLColumn("col_original_name")
    private String original_name;


    public TvShow() {
    }


    public TvShow(String title, String description, String thumbnailURL, String wideThumbnailURL, Float userRatings, String release_date, int id) {
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
        vote_average = in.readFloat();
        first_air_date = in.readString();
        id = in.readInt();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original_name);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeFloat(vote_average);
        if (first_air_date != null) {
            dest.writeString(first_air_date);
        }
        dest.writeInt(id);
    }

}
