package com.popular_movies.ui.listing.search_listing;

import android.view.View;

import com.popular_movies.domain.search.Search;
import com.popular_movies.domain.tv.TvShow;

/**
 * Created by Gurpreet on 04-10-2017.
 */

public interface SearchItemClickListener {
        void itemClicked(View view, int position, Search search);
}
