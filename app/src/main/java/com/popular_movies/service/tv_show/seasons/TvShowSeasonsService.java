package com.popular_movies.service.tv_show.seasons;

import android.app.Activity;

import com.popular_movies.BuildConfig;
import com.popular_movies.domain.common.CreditsResponse;
import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.seasons.TvShowSeasonDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Gurpreet on 22-09-2017.
 */

public interface TvShowSeasonsService {

    public interface TvShowSeasonResource {

        @GET("tv/{id}/season/{number}?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TvShowSeasonDetails> getTvShowSeasonDetails(@Path("id") int id, @Path("number") int seasonNumber);

        @GET("tv/{id}/season/{number}/videos?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TrailerResponse> getTrailers(@Path("id") int id, @Path("number") int seasonNumber);

    }


    /**
     * Get tv show details model
     */
    void getTvShowSeasonDetails(int id, int seasonNumber, Activity activity, GetTvShowSeasonDetailsCallback getTvShowSeasonDetailsCallback);

    interface GetTvShowSeasonDetailsCallback {
        void onSuccess(TvShowSeasonDetails tvShowSeasonDetails);

        void onFailure(Throwable throwable);
    }

    /**
     * Get movie trailers model
     */
    void getTrailers(int id, int seasonNumber, Activity activity, GetTrailersCallback getTrailersCallback);

    interface GetTrailersCallback {
        void onSuccess(TrailerResponse trailerResponse);

        void onFailure(Throwable throwable);
    }

}
