package com.popular_movies.domain.tv.seasons;

/**
 * Created by Gurpreet on 28-09-2017.
 */

public class User {

    private String username;
    private boolean _private;
    private String name;
    private boolean vip;
    private boolean vipEp;
    private Ids ids;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isPrivate() {
        return _private;
    }

    public void setPrivate(boolean _private) {
        this._private = _private;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isVipEp() {
        return vipEp;
    }

    public void setVipEp(boolean vipEp) {
        this.vipEp = vipEp;
    }

    public Ids getIds() {
        return ids;
    }

    public void setIds(Ids ids) {
        this.ids = ids;
    }

}