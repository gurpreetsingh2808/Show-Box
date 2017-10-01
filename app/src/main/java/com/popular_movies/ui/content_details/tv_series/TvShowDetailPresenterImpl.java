package com.popular_movies.ui.content_details.tv_series;

import android.app.Activity;
import android.util.Log;

import com.popular_movies.domain.common.CreditsResponse;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.TvShowDetails;
import com.popular_movies.domain.tv.ExternalIds;
import com.popular_movies.domain.tv.seasons.CommentsResponse;
import com.popular_movies.service.tv_show.TvShowsService;
import com.popular_movies.service.tv_show.TvShowsServiceImpl;

import java.util.List;


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
                Log.e("TAG", tvShowDetails.getEpisode_run_time()[0].toString());
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

    @Override
    public void fetchTvShowCredits(int movieId) {
        tvShowsService.getCredits(movieId, activity, new TvShowsService.GetCreditsCallback() {
            @Override
            public void onSuccess(CreditsResponse creditResponse) {
                view.onCreditsRetreivalSuccess(creditResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onCreditsRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void fetchTvShowExternalIds(int movieId) {
        tvShowsService.getTvShowExternalIds(movieId, activity, new TvShowsService.GetTvShowExternalIdsCallback() {
            @Override
            public void onSuccess(ExternalIds tvShowExternalIds) {
                view.onTvShowExternalIdsRetreivalSuccess(tvShowExternalIds);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onTvShowExternalIdsRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void fetchComments(String name) {
        tvShowsService.getComments(name, activity, new TvShowsService.GetCommentsCallback() {
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

