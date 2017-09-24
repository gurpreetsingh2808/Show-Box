package com.popular_movies.ui.listing.tv_series_listing.episodes_listing;

import android.view.View;

import com.popular_movies.domain.tv.seasons.Episode;

/**
 * Created by Gurpreet on 24-09-2017.
 */

public interface EpisodeClickListener {
    void itemClicked(View view, int position, Episode episode);
}
