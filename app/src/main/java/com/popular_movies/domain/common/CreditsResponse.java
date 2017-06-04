package com.popular_movies.domain.common;

/**
 * Created by Gurpreet on 02-06-2017.
 */

public class CreditsResponse {

    private Integer id;
    private Cast[] cast;
    //    private Crew crew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cast[] getCast() {
        return cast;
    }

    public void setCast(Cast[] cast) {
        this.cast = cast;
    }

}
