package com.popular_movies.ui.listing.search_listing;

import android.app.Activity;

import com.popular_movies.domain.search.SearchResponse;
import com.popular_movies.service.search.SearchService;
import com.popular_movies.service.search.SearchServiceImpl;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class SearchPresenterImpl implements SearchPresenter.Presenter {

    private final SearchPresenter.View view;
    private final Activity activity;
    private SearchService searchService;

    public SearchPresenterImpl(SearchPresenter.View view, Activity activity) {
        this.view = view;
        this.activity = activity;
        this.searchService = new SearchServiceImpl();
    }

    @Override
    public void searchAll(String searchTerm, int pageNumber) {
        searchService.getSearchResults(searchTerm, pageNumber, activity, new SearchService.GetSearchResultsCallback() {
            @Override
            public void onSuccess(SearchResponse searchResponse) {
                view.onSearchAllRetreivalSuccess(searchResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onSearchAllRetreivalFailure(throwable);
            }
        });
    }
}
