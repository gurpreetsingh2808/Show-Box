package com.popular_movies.domain.tv.seasons;

/**
 * Created by Gurpreet on 22-09-2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Episode implements Parcelable {

    private String air_date;
    private List<Crew> crew = new ArrayList<Crew>();
    private Integer episode_number;
    private List<GuestStar> guest_stars = null;
    private String name;
    private String overview;
    private Integer id;
    private String production_code;
    private Integer season_number;
    private String still_path;
    private Float vote_average;
    private Integer vote_count;

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public Integer getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(Integer episode_number) {
        this.episode_number = episode_number;
    }

    public List<GuestStar> getGuest_stars() {
        return guest_stars;
    }

    public void setGuest_stars(List<GuestStar> guest_stars) {
        this.guest_stars = guest_stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduction_code() {
        return production_code;
    }

    public void setProduction_code(String production_code) {
        this.production_code = production_code;
    }

    public Integer getSeason_number() {
        return season_number;
    }

    public void setSeason_number(Integer season_number) {
        this.season_number = season_number;
    }

    public String getStill_path() {
        return still_path;
    }

    public void setStill_path(String still_path) {
        this.still_path = still_path;
    }

    public Float getVote_average() {
        return vote_average;
    }

    public void setVote_average(Float vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }


    protected Episode(Parcel in) {
        air_date = in.readString();
        in.readList(crew, getClass().getClassLoader());
        episode_number = in.readInt();
//        guest_stars = in.readList();
        id = in.readInt();
        season_number = in.readInt();
        vote_average = in.readFloat();
        vote_count = in.readInt();
        name = in.readString();
        overview = in.readString();
        production_code = in.readString();
        still_path = in.readString();
    }

    public static final Creator<Episode> CREATOR = new Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel in) {
            return new Episode(in);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(air_date.toString());
        dest.writeList(crew);
        dest.writeInt(episode_number);
//        dest.writeList(guest_stars);
        dest.writeInt(id);
        dest.writeInt(season_number);
        dest.writeFloat(vote_average);
        dest.writeInt(vote_count);
        dest.writeString(name);
        dest.writeString(overview);
        dest.writeString(production_code);
        dest.writeString(still_path);
    }
}
