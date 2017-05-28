package com.popular_movies.ui.tv_shows;

import com.popular_movies.domain.TvShowResponse;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class TvShowsPresenter {

    public interface View {
        void onShowsAiringTodayRetreivalSuccess(TvShowResponse tvSeriesResponse);

        void onShowsAiringTodayRetreivalFailure(Throwable throwable);

        void onTheAirShowsRetreivalSuccess(TvShowResponse tvSeriesResponse);

        void onTheAirShowsRetreivalFailure(Throwable throwable);

        void onPopularShowsRetreivalSuccess(TvShowResponse tvSeriesResponse);

        void onPopularShowsRetreivalFailure(Throwable throwable);

        void onTopRatedShowsRetreivalSuccess(TvShowResponse tvSeriesResponse);

        void onTopRatedShowsRetreivalFailure(Throwable throwable);
    }

    interface Presenter {
        void fetchShowsAiringToday();

        void fetchShowsOnTheAir();

        void fetchTopRatedShows();

        void fetchPopularShows();
    }
}
