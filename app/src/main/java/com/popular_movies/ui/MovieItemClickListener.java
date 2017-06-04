package com.popular_movies.ui;

import android.view.View;

import com.popular_movies.domain.movie.Movie;

/**
 * Created by Gurpreet on 30-04-2017.
 */

public interface MovieItemClickListener {
        void itemClicked(View view, int position, Movie movieData);
}
