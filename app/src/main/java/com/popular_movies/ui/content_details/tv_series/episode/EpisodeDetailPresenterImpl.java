package com.popular_movies.ui.content_details.tv_series.episode;

import android.app.Activity;

import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.ExternalIds;
import com.popular_movies.domain.tv.seasons.CommentsResponse;
import com.popular_movies.domain.tv.seasons.TvShowSeasonDetails;
import com.popular_movies.service.tv_show.seasons.TvShowSeasonsService;
import com.popular_movies.service.tv_show.seasons.TvShowSeasonsServiceImpl;
import com.popular_movies.service.tv_show.seasons.episodes.EpisodeService;
import com.popular_movies.service.tv_show.seasons.episodes.EpisodeServiceImpl;

import java.util.List;


/**
 * Created by Gurpreet on 21-01-2017.
 */


public class EpisodeDetailPresenterImpl implements EpisodeDetailPresenter.Presenter {

    private final EpisodeDetailPresenter.View view;
    private final Activity activity;
    private EpisodeService episodeService;

    public EpisodeDetailPresenterImpl(EpisodeDetailPresenter.View view, Activity activity) {
        this.view = view;
        this.activity = activity;
        this.episodeService = new EpisodeServiceImpl();
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
    public void fetchTrailers(int tvShowId, int seasonNumber, int episodeNumber) {
        episodeService.getTrailers(tvShowId, seasonNumber, episodeNumber, activity, new EpisodeService.GetTrailersCallback() {
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

    @Override
    public void fetchEpisodeExternalIds(int id, int seasonNumber, int episodeNumber) {
        episodeService.getEpisodeExternalIds(id, seasonNumber, episodeNumber, activity, new EpisodeService.GetEpisodeExternalIdsCallback() {
            @Override
            public void onSuccess(ExternalIds tvShowExternalIds) {
                view.onEpisodeExternalIdsRetreivalSuccess(tvShowExternalIds);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onEpisodeExternalIdsRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void fetchComments(String name, int seasonNumber, int episodeNumber) {
        episodeService.getComments(name, seasonNumber, episodeNumber, activity, new EpisodeService.GetCommentsCallback() {
            @Override
            public void onSuccess(List<CommentsResponse> comments) {
                view.onCommentsRetreivalSuccess(comments);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onCommentsRetreivalFailure(throwable);
            }
        });
    }

}

