package com.popular_movies.ui.tv_series_listing;

import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.movie.MovieResponse;
import com.popular_movies.domain.tv.TvShowResponse;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class TvSeriesPresenter {

    public interface View {
        void onTvSeriesRetreivalSuccess(TvShowResponse tvShowResponse);
        void onTvSeriesRetreivalFailure(Throwable throwable);
        void onGenresRetreivalSuccess(GenreResponse genreResponse);
        void onGenresRetreivalFailure(Throwable throwable);
    }

    interface Presenter {
        void fetchTvSeries(String seriesType, String pageNumber);
        void fetchGenres();
        void getSearchResults(String query);
    }
}
