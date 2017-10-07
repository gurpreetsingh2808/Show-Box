package com.popular_movies.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.popular_movies.BuildConfig;
import com.popular_movies.R;
import com.popular_movies.domain.movie.Movie;
import com.popular_movies.domain.search.Search;
import com.popular_movies.domain.tv.Season;
import com.popular_movies.domain.tv.TvShow;
import com.popular_movies.domain.tv.seasons.Episode;
import com.popular_movies.ui.content_details.DetailActivity;
import com.popular_movies.ui.content_details.DetailContentType;
import com.popular_movies.ui.listing.ListingActivity;
import com.popular_movies.ui.listing.search_listing.SearchResultContentType;
import com.popular_movies.util.constants.IntentKeys;

/**
 * Created by Gurpreet on 10-06-2017.
 */

public class FlowManager {

    /********************************************************************************************
     *                                     DETAILED SCREEN
     *******************************************************************************************/
    public static void moveToDetailsActivity(Context context, String contentType, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(context.getString(R.string.key_detail_content), movie);
        intent.putExtra(context.getString(R.string.key_detail_content_type), contentType);
        context.startActivity(intent);
    }

    public static void moveToDetailsActivity(Context context, String contentType, TvShow tvShow) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(context.getString(R.string.key_detail_content), tvShow);
        intent.putExtra(context.getString(R.string.key_detail_content_type), contentType);
        context.startActivity(intent);
    }

    public static void moveToDetailsActivity(Context context, String contentType, Episode episode) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(context.getString(R.string.key_detail_content), episode);
        intent.putExtra(context.getString(R.string.key_detail_content_type), contentType);
        context.startActivity(intent);
    }

    public static void moveToDetailsActivity(Context context, String contentType, Search search) {
        switch (search.getMedia_type()) {
            case SearchResultContentType.MOVIE:
                Movie movie = search.getMovieObject(search);
                moveToDetailsActivity(context, DetailContentType.MOVIE, movie);
                break;
            case SearchResultContentType.TV_SERIES:
                TvShow tvShow = search.getTvShowObject(search);
                moveToDetailsActivity(context, DetailContentType.TV_SERIES, tvShow);
                break;

        }
    }

    /********************************************************************************************
     *                                          YOUTUBE
     *******************************************************************************************/

    public static void viewTrailerInYoutube(Context context, String trailerKey, View view) {
        if (trailerKey != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(BuildConfig.BASE_URL_TRAILER + trailerKey));
            context.startActivity(intent);
        } else {
            Snackbar.make(view, context.getString(R.string.trailer_error), Snackbar.LENGTH_SHORT).show();
        }
    }


    /********************************************************************************************
     *                                     LISTING SCREEN
     *******************************************************************************************/

    public static void moveToListingActivity(Context context, String contentType, String subContentType, String title) {
        Intent intent = new Intent(context, ListingActivity.class);
        intent.putExtra(context.getString(R.string.key_listing_content_type), contentType);
        intent.putExtra(context.getString(R.string.key_listing_sub_content_type), subContentType);
        intent.putExtra(context.getString(R.string.key_title), title);
        context.startActivity(intent);
    }

    public static void moveToListingActivity(Context context, String contentType, String title,
                                             int tvId, int seasonNumber) {
        Intent intent = new Intent(context, ListingActivity.class);
        intent.putExtra(context.getString(R.string.key_listing_content_type), contentType);
        intent.putExtra(context.getString(R.string.key_title), title);
        intent.putExtra(IntentKeys.KEY_TV_ID, tvId);
        intent.putExtra(IntentKeys.KEY_SEASON_NUMBER, seasonNumber);

        context.startActivity(intent);
    }

    public static void moveToListingActivity(Context context, String contentType, String searchTerm) {
        Intent intent = new Intent(context, ListingActivity.class);
        intent.putExtra(context.getString(R.string.key_listing_content_type), contentType);
        intent.putExtra(context.getString(R.string.key_title), searchTerm);
        intent.putExtra(context.getString(R.string.key_listing_sub_content_type), searchTerm);
        context.startActivity(intent);
    }
}
