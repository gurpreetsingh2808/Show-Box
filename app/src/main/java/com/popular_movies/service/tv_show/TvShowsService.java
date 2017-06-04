package com.popular_movies.service.tv_show;

import android.app.Activity;

import com.popular_movies.BuildConfig;
import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Gurpreet on 20-01-2017.
 */

public interface TvShowsService {

    public interface TvSeriesResource {
        @GET("tv/{type}?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TvShowResponse> getTvSeries(@Path("type") String type, @Query("page") String page);

        @GET("tv/popular?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TvShowResponse> getPopularTvSeries();

        @GET("tv/top_rated?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TvShowResponse> getTopRatedTvSeries();

        @GET("tv/airing_today?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TvShowResponse> getShowsAiringToday();

        @GET("tv/latest?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TvShowResponse> getLatestTvSeries();

        @GET("tv/on_the_air?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TvShowResponse> getOnTheAirShows();

        @GET("tv/{id}/videos?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TrailerResponse> getTrailers(@Path("id") int id);
/*

        @GET("tv/{id}/reviews?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<ReviewResponse> getReview(@Path("id") int id);
*/

        @GET("search/tv?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TvShowResponse> getSearchResults(@Query("query") String searchQuery);

        @GET("genre/tv/list?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<GenreResponse> getGenres();
    }

    /**
     * Get tv series model
     */
    void getTvSeries(String type, String pageNumber, Activity activity, GetTvSeriesCallback getTvSeriesCardCalback);

    interface GetTvSeriesCallback {
        void onSuccess(TvShowResponse tvSeriesResponse);

        void onFailure(Throwable throwable);
    }

    void getPopularTvSeries(Activity activity, GetTvSeriesCallback getTvSeriesCalback);

    void getTopRatedTvSeries(Activity activity, GetTvSeriesCallback getTvSeriesCalback);

    void getOnTheAirShows(Activity activity, GetTvSeriesCallback getOnTheAirShowsCallback);

    void getShowsAiringToday(Activity activity, GetTvSeriesCallback getShowsAiringTodayCallback);

    void getLatestTvSeries(Activity activity, GetTvSeriesCallback getLatestTvSeriesCalback);


    /**
     * Get movie trailers model
     */
    void getTrailers(int id, Activity activity, GetTrailersCallback getTrailersCallback);

    interface GetTrailersCallback {
        void onSuccess(TrailerResponse trailerResponse);

        void onFailure(Throwable throwable);
    }


//    /**
//     * Get movie reviews model
//     */
//    void getReviews(int id, Activity activity, GetReviewsCallback getReviewsCallback);
//
//    interface GetReviewsCallback {
//        void onSuccess(ReviewResponse reviewResponse);
//        void onFailure(Throwable throwable);
//    }


    /**
     * search model
     */
    void getSearchResults(String query, Activity activity, GetTvSeriesCallback getTvSeriesCardCalback);

    /**
     * genre model
     */
    void getGenre(Activity activity, FetchGenresCallback fetchGenresCallback);

    interface FetchGenresCallback {
        void onSuccess(GenreResponse genreResponse);

        void onFailure(Throwable throwable);
    }

}
