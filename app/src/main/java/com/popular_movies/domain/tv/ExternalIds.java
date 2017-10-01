package com.popular_movies.domain.tv;

/**
 * Created by Gurpreet on 30-09-2017.
 */

public class ExternalIds {

    private String imdb_id;
    private String freebase_mid;
    private Object freebase_d;
    private Integer tvdb_id;
    private Integer tvrage_id;
    private Integer id;


    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getFreebase_mid() {
        return freebase_mid;
    }

    public void setFreebase_mid(String freebase_mid) {
        this.freebase_mid = freebase_mid;
    }

    public Object getFreebase_d() {
        return freebase_d;
    }

    public void setFreebase_d(Object freebase_d) {
        this.freebase_d = freebase_d;
    }

    public Integer getTvdb_id() {
        return tvdb_id;
    }

    public void setTvdb_id(Integer tvdb_id) {
        this.tvdb_id = tvdb_id;
    }

    public Integer getTvrage_id() {
        return tvrage_id;
    }

    public void setTvrage_id(Integer tvrage_id) {
        this.tvrage_id = tvrage_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
