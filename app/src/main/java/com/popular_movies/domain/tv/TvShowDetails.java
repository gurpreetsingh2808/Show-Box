package com.popular_movies.domain.tv;

import com.popular_movies.domain.common.Genre;

import java.util.Date;

/**
 * Created by Gurpreet on 10-06-2017.
 */

public class TvShowDetails {

    private String backdrop_path;
    private ShowCreator[] created_by;
    private Integer[] episode_run_time;
    private Date first_air_date;
    private Genre[] genres;
    private String homepage;
    private Integer id;
    private Boolean in_production;
    private String[] languages;
    private String last_air_date;
    private String name;
    //  private Network[] networks;
    private Integer number_of_episodes;
    private Integer number_of_seasons;
    private String original_language;
    private String original_name;
    private String original_country;
    private String overview;
    private String popularity;
    private String poster_path;
    //    private ProductionCompanies[] productionCompanies;
    private Season[] seasons;
    private String status;
    private String type;
    private String vote_average;
    private Integer vote_count;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public ShowCreator[] getCreated_by() {
        return created_by;
    }

    public Integer[] getEpisode_run_time() {
        return episode_run_time;
    }

    public Date getFirst_air_date() {
        return first_air_date;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getIn_production() {
        return in_production;
    }

    public String[] getLanguages() {
        return languages;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber_of_episodes() {
        return number_of_episodes;
    }

    public Integer getNumber_of_seasons() {
        return number_of_seasons;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public String getOriginal_country() {
        return original_country;
    }

    public String getOverview() {
        return overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public Season[] getSeasons() {
        return seasons;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getVote_average() {
        return vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setCreated_by(ShowCreator[] created_by) {
        this.created_by = created_by;
    }

    public void setEpisode_run_time(Integer[] episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public void setFirst_air_date(Date first_air_date) {
        this.first_air_date = first_air_date;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIn_production(Boolean in_production) {
        this.in_production = in_production;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber_of_episodes(Integer number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public void setNumber_of_seasons(Integer number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public void setOriginal_country(String original_country) {
        this.original_country = original_country;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSeasons(Season[] seasons) {
        this.seasons = seasons;
    }
}
