package com.popular_movies.ui.content_details.tv_series;

import android.app.Activity;

import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.TvShowDetails;
import com.popular_movies.service.tv_show.TvShowsService;
import com.popular_movies.service.tv_show.TvShowsServiceImpl;


/**
 * Created by Gurpreet on 21-01-2017.
 */


public class TvShowDetailPresenterImpl implements TvShowDetailPresenter.Presenter {

    private final TvShowDetailPresenter.View view;
    private final Activity activity;
    private TvShowsService tvShowsService;

    public TvShowDetailPresenterImpl(TvShowDetailPresenter.View view, Activity activity) {
        this.view = view;
        this.activity = activity;
        this.tvShowsService = new TvShowsServiceImpl();
    }

    @Override
    public void fetchTvShowDetails(int id) {
        tvShowsService.getTvShowDetails(id, activity, new TvShowsService.GetTvShowDetailsCallback() {
            @Override
            public void onSuccess(TvShowDetails tvShowDetails) {
                view.onTvShowDetailsRetreivalSuccess(tvShowDetails);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onTvShowDetailsRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void fetchTrailers(int movieId) {
        tvShowsService.getTrailers(movieId, activity, new TvShowsService.GetTrailersCallback() {
            @Override
            public void onSuccess(TrailerResponse trailerResponse) {
                view.onTrailersRetreivalSuccess(trailerResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onTrailersRetreivalFailure(throwable);
            }
        });
    }
}

