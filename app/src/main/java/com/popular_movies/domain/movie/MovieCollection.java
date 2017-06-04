package com.popular_movies.domain.movie;

/**
 * Created by Gurpreet on 01-06-2017.
 */

public class MovieCollection {

    private Integer id;
    private String name;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private Movie[] parts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Movie[] getParts() {
        return parts;
    }

    public void setParts(Movie[] parts) {
        this.parts = parts;
    }
}
