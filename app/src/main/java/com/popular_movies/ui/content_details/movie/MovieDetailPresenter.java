package com.popular_movies.ui.content_details.movie;

import com.popular_movies.domain.MovieCollection;
import com.popular_movies.domain.MovieDetails;
import com.popular_movies.domain.ReviewResponse;
import com.popular_movies.domain.TrailerResponse;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class MovieDetailPresenter {

    public interface View {
        void onReviewsRetreivalSuccess(ReviewResponse reviewResponse);
        void onReviewsRetreivalFailure(Throwable throwable);

        void onTrailersRetreivalSuccess(TrailerResponse trailerResponse);
        void onTrailersRetreivalFailure(Throwable throwable);

        void onMovieDetailsRetreivalSuccess(MovieDetails movieDetails);
        void onMovieDetailsRetreivalFailure(Throwable throwable);

        void onMovieCollectionRetreivalSuccess(MovieCollection movieCollection);
        void onMovieCollectionRetreivalFailure(Throwable throwable);
    }

    interface Presenter {
        void fetchMovieDetails(int movieId);
        void fetchReviews(int movieId);
        void fetchTrailers(int movieId);
        void fetchMovieCollection(int movieId);
    }
}
