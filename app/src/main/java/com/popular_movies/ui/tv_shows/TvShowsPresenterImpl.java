package com.popular_movies.ui.tv_shows;

import android.app.Activity;
import android.util.Log;

import com.popular_movies.domain.tv.TvShowResponse;
import com.popular_movies.service.tv_show.TvShowsService;
import com.popular_movies.service.tv_show.TvShowsServiceImpl;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class TvShowsPresenterImpl implements TvShowsPresenter.Presenter {

    private final TvShowsPresenter.View view;
    private final Activity activity;
    private TvShowsService seriesService;

    public TvShowsPresenterImpl(TvShowsPresenter.View view, Activity activity) {
        this.view = view;
        this.activity = activity;
        this.seriesService = new TvShowsServiceImpl();
    }

    @Override
    public void fetchShowsAiringToday() {
        seriesService.getShowsAiringToday(activity, new TvShowsService.GetTvSeriesCallback() {
            @Override
            public void onSuccess(TvShowResponse tvSeriesResponse) {
                view.onShowsAiringTodayRetreivalSuccess(tvSeriesResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onShowsAiringTodayRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void fetchShowsOnTheAir() {
        seriesService.getOnTheAirShows(activity, new TvShowsService.GetTvSeriesCallback() {
            @Override
            public void onSuccess(TvShowResponse tvSeriesResponse) {
                view.onTheAirShowsRetreivalSuccess(tvSeriesResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onTheAirShowsRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void fetchPopularShows() {
        seriesService.getPopularTvSeries(activity, new TvShowsService.GetTvSeriesCallback() {
            @Override
            public void onSuccess(TvShowResponse tvSeriesResponse) {
                view.onPopularShowsRetreivalSuccess(tvSeriesResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onPopularShowsRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void fetchTopRatedShows() {
        seriesService.getTopRatedTvSeries(activity, new TvShowsService.GetTvSeriesCallback() {
            @Override
            public void onSuccess(TvShowResponse tvSeriesResponse) {
                view.onTopRatedShowsRetreivalSuccess(tvSeriesResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onTopRatedShowsRetreivalFailure(throwable);
            }
        });
    }

}
