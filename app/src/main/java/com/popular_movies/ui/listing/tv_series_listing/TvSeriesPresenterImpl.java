package com.popular_movies.ui.listing.tv_series_listing;

import android.app.Activity;

import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.tv.TvShowResponse;
import com.popular_movies.service.tv_show.TvShowsService;
import com.popular_movies.service.tv_show.TvShowsServiceImpl;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class TvSeriesPresenterImpl implements TvSeriesPresenter.Presenter {

    private final TvSeriesPresenter.View view;
    private final Activity activity;
    private TvShowsService tvShowsService;

    public TvSeriesPresenterImpl(TvSeriesPresenter.View view, Activity activity) {
        this.view = view;
        this.activity = activity;
        this.tvShowsService = new TvShowsServiceImpl();
    }

    @Override
    public void fetchTvSeries(String seriesType, String pageNumber) {
        tvShowsService.getTvSeries(seriesType, pageNumber, activity, new TvShowsService.GetTvSeriesCallback() {
            @Override
            public void onSuccess(TvShowResponse tvSeriesResponse) {
                view.onTvSeriesRetreivalSuccess(tvSeriesResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onTvSeriesRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void fetchGenres() {
        tvShowsService.getGenre(activity, new TvShowsService.FetchGenresCallback() {
            @Override
            public void onSuccess(GenreResponse genreResponse) {
                view.onGenresRetreivalSuccess(genreResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onGenresRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void getSearchResults(String query) {
        tvShowsService.getSearchResults(query, activity, new TvShowsService.GetTvSeriesCallback() {

            @Override
            public void onSuccess(TvShowResponse tvSeriesResponse) {
                view.onTvSeriesRetreivalSuccess(tvSeriesResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onTvSeriesRetreivalFailure(throwable);
            }
        });
    }
}
