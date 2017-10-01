package com.popular_movies.service.tv_show.seasons.episodes;

import android.app.Activity;

import com.popular_movies.BuildConfig;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.ExternalIds;
import com.popular_movies.domain.tv.seasons.CommentsResponse;
import com.popular_movies.domain.tv.seasons.Episode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Gurpreet on 22-09-2017.
 */

public interface EpisodeService {

    public interface EpisodeResource {

        @GET("tv/{id}/season/{season_number}/episode/{episode_number}?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<Episode> getEpisodeDetails(@Path("id") int id, @Path("season_number") int season_number, @Path("episode_number") int episode_number);

        @GET("tv/{id}/season/{season_number}/episode/{episode_number}/videos?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TrailerResponse> getTrailers(@Path("id") int id, @Path("season_number") int season_number, @Path("episode_number") int episode_number);

        @GET("tv/{id}/season/{season_number}/episode/{episode_number}/external_ids?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<ExternalIds> getExternalIds(@Path("id") int id, @Path("season_number") int season_number, @Path("episode_number") int episode_number);

        @GET("shows/{id}/seasons/{season_number}/episodes/{episode_number}/comments")
        Call<List<CommentsResponse>> getComments(@Path("id") String id, @Path("season_number") int season_number, @Path("episode_number") int episode_number);
    }


    /**
     * Get episode details model
     */
    void getEpisodeDetails(int id, int seasonNumber, int episodeNumber, Activity activity, GetEpisodeDetailsCallback getEpisodeDetailsCallback);

    interface GetEpisodeDetailsCallback {
        void onSuccess(Episode episode);

        void onFailure(Throwable throwable);
    }

    /**
     * Get episode trailers model
     */
    void getTrailers(int showId, int seasonNumber, int episodeNumber, Activity activity, GetTrailersCallback getTrailersCallback);

    interface GetTrailersCallback {
        void onSuccess(TrailerResponse trailerResponse);

        void onFailure(Throwable throwable);
    }

    /**
     * Get episode external ids model
     */
    void getEpisodeExternalIds(int id, int seasonNumber, int episodeNumber, Activity activity, GetEpisodeExternalIdsCallback getTvShowExternalIdsCallback);

    interface GetEpisodeExternalIdsCallback {
        void onSuccess(ExternalIds tvShowExternalIds);

        void onFailure(Throwable throwable);
    }
    /**
     * Get episode comments model
     */
    void getComments(String name, int seasonNumber, int episodeNumber, Activity activity, GetCommentsCallback getCommentsCallback);

    interface GetCommentsCallback {
        void onSuccess(List<CommentsResponse> comments);

        void onFailure(Throwable throwable);
    }

}
