package com.popular_movies.ui.listing.tv_series_listing.episodes_listing;

import android.app.Activity;

import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.tv.TvShowResponse;
import com.popular_movies.domain.tv.seasons.TvShowSeasonDetails;
import com.popular_movies.service.tv_show.TvShowsService;
import com.popular_movies.service.tv_show.TvShowsServiceImpl;
import com.popular_movies.service.tv_show.seasons.TvShowSeasonsService;
import com.popular_movies.service.tv_show.seasons.TvShowSeasonsServiceImpl;
import com.popular_movies.ui.listing.tv_series_listing.TvSeriesPresenter;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class EpisodePresenterImpl implements EpisodePresenter.Presenter {

    private final EpisodePresenter.View view;
    private final Activity activity;
    private TvShowSeasonsService tvShowSeasonsService;

    public EpisodePresenterImpl(EpisodePresenter.View view, Activity activity) {
        this.view = view;
        this.activity = activity;
        this.tvShowSeasonsService = new TvShowSeasonsServiceImpl();
    }


    @Override
    public void fetchEpisodes(int tvId, int seasonNumber) {
        tvShowSeasonsService.getTvShowSeasonDetails(tvId, seasonNumber, activity, new TvShowSeasonsService.GetTvShowSeasonDetailsCallback() {
            @Override
            public void onSuccess(TvShowSeasonDetails tvShowSeasonDetails) {
                view.onEpisodesRetreivalSuccess(tvShowSeasonDetails);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onEpisodesRetreivalFailure(throwable);
            }
        });
    }
}
