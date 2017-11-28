package com.popular_movies.ui.filter;

import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.movie.MovieResponse;

import java.util.List;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class FilterPresenter {

    public interface View {
        void onMoviesRetreivalSuccess(MovieResponse movieResponse);
        void onMoviesRetreivalFailure(Throwable throwable);
        void onGenresRetreivalSuccess(GenreResponse genreResponse);
        void onGenresRetreivalFailure(Throwable throwable);
    }

    interface Presenter {
        void fetchGenres();
        void getMovies(String voteAverage, List<String> genreIds, String startYear, String endYear, String sortBy, String pageNumber);
    }
}
