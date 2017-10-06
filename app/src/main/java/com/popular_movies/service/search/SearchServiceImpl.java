package com.popular_movies.service.search;

import android.app.Activity;

import com.popular_movies.domain.search.SearchResponse;
import com.popular_movies.service.ResourceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gurpreet on 22-09-2017.
 */

public class SearchServiceImpl implements SearchService {

    @Override
    public void getSearchResults(String searchTerm, int pageNumber, Activity activity, final GetSearchResultsCallback getSearchResultsCallback) {
        SearchResource searchResource = ResourceBuilder.buildResource(SearchResource.class, activity);
        Call<SearchResponse> call = searchResource.searchGlobally(searchTerm, pageNumber);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() != null && response.isSuccessful())
                    getSearchResultsCallback.onSuccess(response.body());
                else
                    getSearchResultsCallback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    getSearchResultsCallback.onFailure(t);
                }
            }
        });
    }
}
