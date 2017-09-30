package com.popular_movies.service.tv_show.seasons.episodes;

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

public interface EpisodeService {

    public interface EpisodeResource {

        @GET("tv/{id}/season/{number}?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TvShowSeasonDetails> getTvShowSeasonDetails(@Path("id") int id);

        @GET("tv/{id}/videos?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TrailerResponse> getTrailers(@Path("id") int id);

        @GET("tv/{id}/credits?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<CreditsResponse> getCredits(@Path("id") int id);

//        @GET("shows/{name}/seasons/{season_number}/episodes/{episode_number}/comments")
//        Call<Response> getComments(@Path("name") String name, @Path("season_number") int season_number, @Path("episode_number") int episode_number);
    }


    /**
     * Get tv show details model
     */
    void getTvShowSeasonDetails(int id, Activity activity, GetTvShowSeasonDetailsCallback getTvShowSeasonDetailsCallback);

    interface GetTvShowSeasonDetailsCallback {
        void onSuccess(TvShowSeasonDetails tvShowSeasonDetails);

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
     * Get episode reviews model
     */
//    void getComments(String name, int seasonNumber, int episodeNumber, Activity activity, GetReviewsCallback getReviewsCallback);
//
//    interface GetReviewsCallback {
//        void onSuccess(Response reviewResponse);
//        void onFailure(Throwable throwable);
//    }

    /**
     * Get movie credits model
     */
    void getCredits(int id, Activity activity, GetCreditsCallback getCreditsCallback);

    interface GetCreditsCallback {
        void onSuccess(CreditsResponse creditResponse);

        void onFailure(Throwable throwable);
    }

}