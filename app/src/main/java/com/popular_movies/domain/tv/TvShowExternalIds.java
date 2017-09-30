package com.popular_movies.domain.tv;

/**
 * Created by Gurpreet on 30-09-2017.
 */

public class TvShowExternalIds {

    private String imdbId;
    private String freebaseMid;
    private Object freebaseId;
    private Integer tvdbId;
    private Integer tvrageId;
    private Integer id;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getFreebaseMid() {
        return freebaseMid;
    }

    public void setFreebaseMid(String freebaseMid) {
        this.freebaseMid = freebaseMid;
    }

    public Object getFreebaseId() {
        return freebaseId;
    }

    public void setFreebaseId(Object freebaseId) {
        this.freebaseId = freebaseId;
    }

    public Integer getTvdbId() {
        return tvdbId;
    }

    public void setTvdbId(Integer tvdbId) {
        this.tvdbId = tvdbId;
    }

    public Integer getTvrageId() {
        return tvrageId;
    }

    public void setTvrageId(Integer tvrageId) {
        this.tvrageId = tvrageId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
