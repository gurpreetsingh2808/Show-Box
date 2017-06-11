package com.popular_movies.service.tv_show;

import android.app.Activity;

import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.TvShowDetails;
import com.popular_movies.domain.tv.TvShowResponse;
import com.popular_movies.service.ResourceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class TvShowsServiceImpl implements TvShowsService {

    @Override
    public void getPopularTvSeries(Activity activity, final GetTvSeriesCallback getTvSeriesCalback) {
        TvSeriesResource tvSeriesResource = ResourceBuilder.buildResource(TvSeriesResource.class, activity);
        Call<TvShowResponse> call = tvSeriesResource.getPopularTvSeries();
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null && response.isSuccessful())
                    getTvSeriesCalback.onSuccess(response.body());
                else
                    getTvSeriesCalback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    //SnackBarManager.renderFailureSnackBar(activity, null);
                    getTvSeriesCalback.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getTopRatedTvSeries(Activity activity, final GetTvSeriesCallback getTvSeriesCalback) {
        TvSeriesResource tvSeriesResource = ResourceBuilder.buildResource(TvSeriesResource.class, activity);
        Call<TvShowResponse> call = tvSeriesResource.getTopRatedTvSeries();
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null && response.isSuccessful())
                    getTvSeriesCalback.onSuccess(response.body());
                else
                    getTvSeriesCalback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    //SnackBarManager.renderFailureSnackBar(activity, null);
                    getTvSeriesCalback.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getShowsAiringToday(Activity activity, final GetTvSeriesCallback getShowsAiringTodayCallback) {
        TvSeriesResource tvSeriesResource = ResourceBuilder.buildResource(TvSeriesResource.class, activity);
        Call<TvShowResponse> call = tvSeriesResource.getShowsAiringToday();
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null && response.isSuccessful())
                    getShowsAiringTodayCallback.onSuccess(response.body());
                else
                    getShowsAiringTodayCallback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    getShowsAiringTodayCallback.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getOnTheAirShows(Activity activity, final GetTvSeriesCallback getOnTheAirShowsCallback) {
        TvSeriesResource tvSeriesResource = ResourceBuilder.buildResource(TvSeriesResource.class, activity);
        Call<TvShowResponse> call = tvSeriesResource.getOnTheAirShows();
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null && response.isSuccessful())
                    getOnTheAirShowsCallback.onSuccess(response.body());
                else
                    getOnTheAirShowsCallback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    getOnTheAirShowsCallback.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getLatestTvSeries(Activity activity, final GetTvSeriesCallback getLatestTvSeriesCalback) {
        TvSeriesResource tvSeriesResource = ResourceBuilder.buildResource(TvSeriesResource.class, activity);
        Call<TvShowResponse> call = tvSeriesResource.getLatestTvSeries();
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null && response.isSuccessful())
                    getLatestTvSeriesCalback.onSuccess(response.body());
                else
                    getLatestTvSeriesCalback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    getLatestTvSeriesCalback.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getTvShowDetails(int id, Activity activity, final GetTvShowDetailsCallback getTvShowDetailsCallback) {
        TvSeriesResource tvSeriesResource = ResourceBuilder.buildResource(TvSeriesResource.class, activity);
        Call<TvShowDetails> call = tvSeriesResource.getTvShowDetails(id);
        call.enqueue(new Callback<TvShowDetails>() {
            @Override
            public void onResponse(Call<TvShowDetails> call, Response<TvShowDetails> response) {
                if (response.body() != null && response.isSuccessful())
                    getTvShowDetailsCallback.onSuccess(response.body());
                else
                    getTvShowDetailsCallback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<TvShowDetails> call, Throwable t) {
                if (!call.isCanceled()) {
                    getTvShowDetailsCallback.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getTvSeries(String type, String pageNumber, Activity activity, final GetTvSeriesCallback getTvSeriesCallback) {
        TvSeriesResource tvSeriesResource = ResourceBuilder.buildResource(TvSeriesResource.class, activity);
        Call<TvShowResponse> call = tvSeriesResource.getTvSeries(type, pageNumber);
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null && response.isSuccessful())
                    getTvSeriesCallback.onSuccess(response.body());
                else
                    getTvSeriesCallback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    //SnackBarManager.renderFailureSnackBar(activity, null);
                    getTvSeriesCallback.onFailure(t);
                }
            }
        });
    }


    @Override
    public void getTrailers(int id, Activity activity, final GetTrailersCallback getTrailersCallback) {

        TvSeriesResource tvSeriesResource = ResourceBuilder.buildResource(TvSeriesResource.class, activity);
        Call<TrailerResponse> call = tvSeriesResource.getTrailers(id);
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
    public void getSearchResults(String query, Activity activity, final GetTvSeriesCallback getTvSeriesCalback) {
        TvSeriesResource tvSeriesResource = ResourceBuilder.buildResource(TvSeriesResource.class, activity);
        Call<TvShowResponse> call = tvSeriesResource.getSearchResults(query);
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null && response.isSuccessful())
                    getTvSeriesCalback.onSuccess(response.body());
                else
                    getTvSeriesCalback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    getTvSeriesCalback.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getGenre(Activity activity, final FetchGenresCallback fetchGenresCallback) {
        TvSeriesResource tvSeriesResource = ResourceBuilder.buildResource(TvSeriesResource.class, activity);
        Call<GenreResponse> call = tvSeriesResource.getGenres();
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if (response.body() != null && response.isSuccessful())
                    fetchGenresCallback.onSuccess(response.body());
                else
                    fetchGenresCallback.onFailure(new Throwable("Error"));
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    fetchGenresCallback.onFailure(t);
                }
            }
        });
    }

}
