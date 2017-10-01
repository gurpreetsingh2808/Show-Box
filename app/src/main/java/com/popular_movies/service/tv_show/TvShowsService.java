package com.popular_movies.service.tv_show;

import android.app.Activity;

import com.popular_movies.BuildConfig;
import com.popular_movies.domain.common.CreditsResponse;
import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.TvShowDetails;
import com.popular_movies.domain.tv.ExternalIds;
import com.popular_movies.domain.tv.TvShowResponse;
import com.popular_movies.domain.tv.seasons.CommentsResponse;

import java.util.List;

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

        @GET("tv/{id}?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TvShowDetails> getTvShowDetails(@Path("id") int id);

        @GET("tv/{id}/videos?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TrailerResponse> getTrailers(@Path("id") int id);

        @GET("tv/{id}/external_ids?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<ExternalIds> getExternalIds(@Path("id") int id);

        @GET("tv/{id}/credits?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<CreditsResponse> getCredits(@Path("id") int id);

        @GET("search/tv?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TvShowResponse> getSearchResults(@Query("query") String searchQuery);

        @GET("genre/tv/list?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<GenreResponse> getGenres();

        @GET("shows/{id}/comments")
        Call<List<CommentsResponse>> getComments(@Path("id") String id);

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
     * Get tv show details model
     */
    void getTvShowDetails(int id, Activity activity, GetTvShowDetailsCallback getTvShowDetailsCallback);

    interface GetTvShowDetailsCallback {
        void onSuccess(TvShowDetails tvShowDetails);

        void onFailure(Throwable throwable);
    }

    /**
     * Get movie trailers model
     */
    void getTrailers(int id, Activity activity, GetTrailersCallback getTrailersCallback);

    interface GetTrailersCallback {
        void onSuccess(TrailerResponse trailerResponse);

        void onFailure(Throwable throwable);
    }

    /**
     * Get movie external ids model
     */
    void getTvShowExternalIds(int id, Activity activity, GetTvShowExternalIdsCallback getTvShowExternalIdsCallback);

    interface GetTvShowExternalIdsCallback {
        void onSuccess(ExternalIds tvShowExternalIds);

        void onFailure(Throwable throwable);
    }

    /**
     * Get episode reviews model
     */
    void getComments(String name, Activity activity, GetCommentsCallback getCommentsCallback);

    interface GetCommentsCallback {
        void onSuccess(List<CommentsResponse> comments);
        void onFailure(Throwable throwable);
    }


    /**
     * Get movie credits model
     */
    void getCredits(int id, Activity activity, GetCreditsCallback getCreditsCallback);

    interface GetCreditsCallback {
        void onSuccess(CreditsResponse creditResponse);
        void onFailure(Throwable throwable);
    }


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
