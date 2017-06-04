package com.popular_movies.ui.tv_shows;

import android.view.View;

import com.popular_movies.domain.tv.TvShow;

/**
 * Created by Gurpreet on 30-04-2017.
 */

public interface TvShowItemClickListener {
        void itemClicked(View view, int position, TvShow tvShow);
}
