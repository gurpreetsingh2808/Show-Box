package com.popular_movies.domain.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.popular_movies.domain.movie.Movie;
import com.popular_movies.domain.tv.TvShow;

import java.util.List;

/**
 * Created by Gurpreet on 02-10-2017.
 */


public class Search implements Parcelable {

    private Float vote_average;
    private Integer vote_count;
    private Integer id;
    private Boolean video;
    private String media_type;
    private String title;
    private Float popularity;
    private String poster_path;
    private String original_language;
    private String original_title;
    private List<Integer> genre_ids = null;
    private String backdrop_path;
    private Boolean adult;
    private String overview;
    private String release_date;
    private String original_name;
    private String name;
    private String first_air_date;
    private List<String> origin_country = null;
    private String profile_path;
    private List<KnownFor> known_for = null;

    protected Search(Parcel in) {
        media_type = in.readString();
        title = in.readString();
        poster_path = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        original_name = in.readString();
        name = in.readString();
        first_air_date = in.readString();
        origin_country = in.createStringArrayList();
        profile_path = in.readString();
    }

    public static final Creator<Search> CREATOR = new Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel in) {
            return new Search(in);
        }

        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
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

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public List<KnownFor> getKnown_for() {
        return known_for;
    }

    public void setKnown_for(List<KnownFor> known_for) {
        this.known_for = known_for;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(media_type);
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(original_name);
        dest.writeString(name);
        dest.writeString(first_air_date);
        dest.writeStringList(origin_country);
        dest.writeString(profile_path);
    }

    public Movie getMovieObject(Search search) {
        return Movie.builder().id(search.getId())
                .adult(search.getAdult())
                .poster_path(search.getPoster_path())
                .overview(search.getOverview())
                .release_date(search.getRelease_date())
                .genre_ids(search.getGenre_ids())
                .original_title(search.getOriginal_title())
                .original_language(search.getOriginal_language())
                .title(search.getTitle())
                .backdrop_path(search.getBackdrop_path())
                .popularity(search.getPopularity())
                .vote_count(search.getVote_count())
                .video(search.getVideo())
                .vote_average(search.getVote_average())
                .build();
    }

    public TvShow getTvShowObject(Search search) {
        return TvShow.builder().id(search.getId())
                .poster_path(search.getPoster_path())
                .overview(search.getOverview())
                .first_air_date(search.getFirst_air_date())
                .genre_ids(search.getGenre_ids())
                .original_name(search.getOriginal_name())
                .original_language(search.getOriginal_language())
                .name(search.getName())
                .backdrop_path(search.getBackdrop_path())
                .popularity(search.getPopularity())
                .vote_count(search.getVote_count())
                .vote_average(search.getVote_average())
                .build();
    }
}
