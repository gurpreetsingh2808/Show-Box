package com.popular_movies.service.tv_show.seasons;

import android.app.Activity;

import com.popular_movies.domain.common.CreditsResponse;
import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.seasons.TvShowSeasonDetails;
import com.popular_movies.service.ResourceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gurpreet on 22-09-2017.
 */

public class TvShowSeasonsServiceImpl implements TvShowSeasonsService {

    @Override
    public void getTvShowSeasonDetails(int id, int seasonNumber, Activity activity, final GetTvShowSeasonDetailsCallback getTvShowSeasonDetailsCallback) {
        TvShowSeasonResource tvShowSeasonResourceResource = ResourceBuilder.buildResource(TvShowSeasonResource.class, activity);
        Call<TvShowSeasonDetails> call = tvShowSeasonResourceResource.getTvShowSeasonDetails(id, seasonNumber);
        call.enqueue(new Callback<TvShowSeasonDetails>() {
            @Override
            public void onResponse(Call<TvShowSeasonDetails> call, Response<TvShowSeasonDetails> response) {
                if (response.body() != null && response.isSuccessful())
                    getTvShowSeasonDetailsCallback.onSuccess(response.body());
                else
                    getTvShowSeasonDetailsCallback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<TvShowSeasonDetails> call, Throwable t) {
                if (!call.isCanceled()) {
                    getTvShowSeasonDetailsCallback.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getTrailers(int id, int seasonNumber, Activity activity, final GetTrailersCallback getTrailersCallback) {
        TvShowSeasonResource tvShowSeasonResourceResource = ResourceBuilder.buildResource(TvShowSeasonResource.class, activity);
        Call<TrailerResponse> call = tvShowSeasonResourceResource.getTrailers(id, seasonNumber);
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
}
