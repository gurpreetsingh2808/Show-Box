package com.popular_movies.ui.filter;

import android.app.Activity;

import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.filter.Filter;
import com.popular_movies.domain.movie.MovieResponse;
import com.popular_movies.service.movie.MovieService;
import com.popular_movies.service.movie.MovieServiceImpl;
import com.popular_movies.ui.listing.movies_listing.MoviesPresenter;

import java.util.List;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class FilterPresenterImpl implements FilterPresenter.Presenter {

    private final FilterPresenter.View view;
    private final Activity activity;
    private MovieService movieService;

    public FilterPresenterImpl(FilterPresenter.View view, Activity activity) {
        this.view = view;
        this.activity = activity;
        this.movieService = new MovieServiceImpl();
    }

    @Override
    public void fetchGenres() {
        movieService.getGenre(activity, new MovieService.FetchGenresCallback() {
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
    public void getMovies(String voteAverage, List<String> genreIds, String startYear, String endYear, String sortBy, String pageNumber) {
        Filter filter = new Filter(genreIds, voteAverage, startYear, endYear, sortBy);
        movieService.getMovies(filter, pageNumber, activity, new MovieService.GetMoviesCallback() {
            @Override
            public void onSuccess(MovieResponse movieResponse) {
                view.onMoviesRetreivalSuccess(movieResponse);
            }

            public void onFailure(Throwable throwable) {
                view.onMoviesRetreivalFailure(throwable);
            }
        });
    }


}
