package com.popular_movies.ui.content_details.tv_series;

import android.view.View;

import com.popular_movies.domain.movie.Movie;
import com.popular_movies.domain.tv.Season;

/**
 * Created by Gurpreet on 30-04-2017.
 */

public interface TvShowSeasonClickListener {
        void seasonClicked(View view, int position, String title);
}
