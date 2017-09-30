package com.popular_movies.util;

import android.content.Context;

import com.popular_movies.BuildConfig;

/**
 * Header util is a utility class which provides utility methods on headers
 */
public class HeaderUtil {

    public final static String TRAKT_API_VERSION = "trakt-api-version";
    public final static  String TRAKT_API_KEY = "trakt-api-key";

    private String traktApiVersion;
    private String traktApiKey;

    public HeaderUtil() {
        this.traktApiVersion = BuildConfig.TRAKT_API_VERSION;
        this.traktApiKey = BuildConfig.TRAKT_API_KEY;
    }

    public String getTraktApiVersion() {
        return traktApiVersion;
    }

    public void setTraktApiVersion(String traktApiVersion) {
        this.traktApiVersion = traktApiVersion;
    }

    public String getTraktApiKey() {
        return traktApiKey;
    }

    public void setTraktApiKey(String traktApiKey) {
        this.traktApiKey = traktApiKey;
    }
}
