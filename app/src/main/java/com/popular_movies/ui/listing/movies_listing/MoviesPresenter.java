package com.popular_movies.ui.listing.movies_listing;

import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.movie.MovieResponse;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class MoviesPresenter {

    public interface View {
        void onMoviesRetreivalSuccess(MovieResponse movieResponse);
        void onMoviesRetreivalFailure(Throwable throwable);
        void onGenresRetreivalSuccess(GenreResponse genreResponse);
        void onGenresRetreivalFailure(Throwable throwable);
    }

    interface Presenter {
        void fetchMovies(String movieType, String pageNumber);
        void fetchGenres();
        void getSearchResults(String query);
    }
}
