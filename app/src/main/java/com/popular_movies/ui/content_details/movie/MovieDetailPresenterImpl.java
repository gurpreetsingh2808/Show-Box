package com.popular_movies.ui.content_details.movie;

import android.app.Activity;

import com.popular_movies.domain.common.CreditsResponse;
import com.popular_movies.domain.movie.MovieCollection;
import com.popular_movies.domain.movie.MovieDetails;
import com.popular_movies.domain.movie.ReviewResponse;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.service.movie.MovieService;
import com.popular_movies.service.movie.MovieServiceImpl;


/**
 * Created by Gurpreet on 21-01-2017.
 */


public class MovieDetailPresenterImpl implements MovieDetailPresenter.Presenter {

    private final MovieDetailPresenter.View view;
    private final Activity activity;
    private MovieService movieService;

    public MovieDetailPresenterImpl(MovieDetailPresenter.View view, Activity activity) {
        this.view = view;
        this.activity = activity;
        this.movieService = new MovieServiceImpl();
    }

    @Override
    public void fetchMovieDetails(int movieId) {
        movieService.getMovieDetails(movieId, activity, new MovieService.GetMovieDetailsCallback() {
            @Override
            public void onSuccess(MovieDetails movieDetails) {
                view.onMovieDetailsRetreivalSuccess(movieDetails);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onMovieDetailsRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void fetchReviews(int movieId) {
        movieService.getReviews(movieId, activity, new MovieService.GetReviewsCallback() {
            @Override
            public void onSuccess(ReviewResponse reviewResponse) {
                view.onReviewsRetreivalSuccess(reviewResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onReviewsRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void fetchTrailers(int movieId) {
        movieService.getTrailers(movieId, activity, new MovieService.GetTrailersCallback() {
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
    public void fetchMovieCollection(int movieId) {
        movieService.getMovieCollection(movieId, activity, new MovieService.GetMovieCollectionCallback() {
            @Override
            public void onSuccess(MovieCollection movieCollection) {
                view.onMovieCollectionRetreivalSuccess(movieCollection);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onMovieCollectionRetreivalFailure(throwable);
            }
        });
    }

    @Override
    public void fetchMovieCredits(int movieId) {
        movieService.getCredits(movieId, activity, new MovieService.GetCreditsCallback() {
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
}

