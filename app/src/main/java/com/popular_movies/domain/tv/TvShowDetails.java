package com.popular_movies.domain.tv;

import com.popular_movies.domain.common.Genre;

/**
 * Created by Gurpreet on 10-06-2017.
 */

public class TvShowDetails {

    private String backdrop_path;
    private ShowCreator[] created_by;
private Integer[] episode_run_time;
    private String first_air_date;
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
    private Integer vote_average;
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

    public String getFirst_air_date() {
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

    public Integer getVote_average() {
        return vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }
}
