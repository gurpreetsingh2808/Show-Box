package com.popular_movies.ui.listing.search_listing;

import com.popular_movies.domain.search.SearchResponse;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class SearchPresenter {

    public interface View {
        void onSearchAllRetreivalSuccess(SearchResponse searchResponse);

        void onSearchAllRetreivalFailure(Throwable throwable);
    }

    interface Presenter {
        void searchAll(String searchTerm, int pageNumber);
    }
}
