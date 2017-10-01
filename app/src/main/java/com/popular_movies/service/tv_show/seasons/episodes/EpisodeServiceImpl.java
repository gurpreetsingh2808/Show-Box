package com.popular_movies.service.tv_show.seasons.episodes;

import android.app.Activity;

import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.ExternalIds;
import com.popular_movies.domain.tv.seasons.CommentsResponse;
import com.popular_movies.domain.tv.seasons.Episode;
import com.popular_movies.domain.tv.seasons.TvShowSeasonDetails;
import com.popular_movies.service.ResourceBuilder;
import com.popular_movies.service.tv_show.seasons.TvShowSeasonsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gurpreet on 22-09-2017.
 */

public class EpisodeServiceImpl implements EpisodeService {

    @Override
    public void getEpisodeDetails(int id, int seasonNumber, int episodeNumber, Activity activity, final GetEpisodeDetailsCallback getEpisodeDetailsCallback) {
        EpisodeResource episodeResource = ResourceBuilder.buildResource(EpisodeResource.class, activity);
        Call<Episode> call = episodeResource.getEpisodeDetails(id, seasonNumber, episodeNumber);
        call.enqueue(new Callback<Episode>() {
            @Override
            public void onResponse(Call<Episode> call, Response<Episode> response) {
                if (response.body() != null && response.isSuccessful())
                    getEpisodeDetailsCallback.onSuccess(response.body());
                else
                    getEpisodeDetailsCallback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<Episode> call, Throwable t) {
                if (!call.isCanceled()) {
                    getEpisodeDetailsCallback.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getTrailers(int showId, int seasonNumber, int episodeNumber, Activity activity, final GetTrailersCallback getTrailersCallback) {
        EpisodeResource episodeResource = ResourceBuilder.buildResource(EpisodeResource.class, activity);
        Call<TrailerResponse> call = episodeResource.getTrailers(showId, seasonNumber, episodeNumber);
        call.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                if (response.body() != null && response.isSuccessful())
                    getTrailersCallback.onSuccess(response.body());
                else
                    getTrailersCallback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    getTrailersCallback.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getEpisodeExternalIds(int id, int seasonNumber, int episodeNumber, Activity activity, final GetEpisodeExternalIdsCallback getTvShowExternalIdsCallback) {
        EpisodeResource episodeResource = ResourceBuilder.buildResource(EpisodeResource.class, activity);
        Call<ExternalIds> call = episodeResource.getExternalIds(id, seasonNumber, episodeNumber);
        call.enqueue(new Callback<ExternalIds>() {
            @Override
            public void onResponse(Call<ExternalIds> call, Response<ExternalIds> response) {
                if (response.body() != null && response.isSuccessful())
                    getTvShowExternalIdsCallback.onSuccess(response.body());
                else
                    getTvShowExternalIdsCallback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<ExternalIds> call, Throwable t) {
                if (!call.isCanceled()) {
                    getTvShowExternalIdsCallback.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getComments(String name, int seasonNumber, int episodeNumber, Activity activity, final GetCommentsCallback getCommentsCallback) {
        EpisodeResource episodeResource = ResourceBuilder.buildTraktResource(EpisodeResource.class, activity);
        Call<List<CommentsResponse>> call = episodeResource.getComments(name, seasonNumber, episodeNumber);
        call.enqueue(new Callback<List<CommentsResponse>>() {
            @Override
            public void onResponse(Call<List<CommentsResponse>> call, Response<List<CommentsResponse>> response) {
                if (response.body() != null && response.isSuccessful())
                    getCommentsCallback.onSuccess(response.body());
                else
                    getCommentsCallback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<List<CommentsResponse>> call, Throwable t) {
                if (!call.isCanceled()) {
                    getCommentsCallback.onFailure(t);
                }
            }
        });
    }
}
