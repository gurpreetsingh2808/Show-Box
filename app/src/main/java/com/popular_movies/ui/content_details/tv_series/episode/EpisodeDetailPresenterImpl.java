package com.popular_movies.ui.content_details.tv_series.episode;

import android.app.Activity;

import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.seasons.TvShowSeasonDetails;
import com.popular_movies.service.tv_show.seasons.TvShowSeasonsService;
import com.popular_movies.service.tv_show.seasons.TvShowSeasonsServiceImpl;


/**
 * Created by Gurpreet on 21-01-2017.
 */


public class EpisodeDetailPresenterImpl implements EpisodeDetailPresenter.Presenter {

    private final EpisodeDetailPresenter.View view;
    private final Activity activity;
    private TvShowSeasonsService tvShowSeasonsService;

    public EpisodeDetailPresenterImpl(EpisodeDetailPresenter.View view, Activity activity) {
        this.view = view;
        this.activity = activity;
        this.tvShowSeasonsService = new TvShowSeasonsServiceImpl();
    }
//
//    @Override
//    public void fetchTvShowSeasonDetails(int id, int seasonNumber) {
//        tvShowSeasonsService.getTvShowSeasonDetails(id, seasonNumber, activity, new TvShowSeasonsService.GetTvShowSeasonDetailsCallback() {
//            @Override
//            public void onSuccess(TvShowSeasonDetails tvShowSeasonDetails) {
//                view.onTvShowSeasonDetailsRetreivalSuccess(tvShowSeasonDetails);
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                view.onTvShowSeasonDetailsRetreivalFailure(throwable);
//            }
//        });
//    }

    @Override
    public void fetchTrailers(int tvShowId, int seasonNumber) {
        tvShowSeasonsService.getTrailers(tvShowId, seasonNumber, activity, new TvShowSeasonsService.GetTrailersCallback() {
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

//    @Override
//    public void fetchTvShowSeasonCredits(int tvShowId) {
//
//    }
}

