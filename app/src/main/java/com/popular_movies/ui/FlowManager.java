package com.popular_movies.ui;

import android.content.Context;
import android.content.Intent;

import com.popular_movies.R;
import com.popular_movies.domain.dictionary.DetailContentType;
import com.popular_movies.domain.movie.Movie;
import com.popular_movies.domain.tv.TvShow;
import com.popular_movies.ui.content_details.MovieDetailActivity;

/**
 * Created by Gurpreet on 10-06-2017.
 */

public class FlowManager {

    public static void moveToDetailsActivity(Context context, String contentType, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(context.getString(R.string.key_detail_content), movie);
        intent.putExtra(context.getString(R.string.key_detail_content_type), contentType);
        context.startActivity(intent);
    }

    public static void moveToDetailsActivity(Context context, String contentType, TvShow tvShow) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(context.getString(R.string.key_detail_content), tvShow);
        intent.putExtra(context.getString(R.string.key_detail_content_type), contentType);
        context.startActivity(intent);
    }
}
