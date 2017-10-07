package com.popular_movies.service.search;

import android.app.Activity;

import com.popular_movies.BuildConfig;
import com.popular_movies.domain.search.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gurpreet on 22-09-2017.
 */

public interface SearchService {

    public interface SearchResource {

        @GET("search/multi?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<SearchResponse> searchGlobally(@Query("query") String query, @Query("page") int page);
    }


    /**
     * Get episode details model
     */
    void getSearchResults(String searchTerm, int pageNumber, Activity activity, GetSearchResultsCallback getSearchResultsCallback);

    interface GetSearchResultsCallback {
        void onSuccess(SearchResponse searchResponse);

        void onFailure(Throwable throwable);
    }

}
