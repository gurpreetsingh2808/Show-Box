package com.popular_movies.ui.content_details.movie;

import com.popular_movies.domain.common.CreditsResponse;
import com.popular_movies.domain.movie.MovieCollection;
import com.popular_movies.domain.movie.MovieDetails;
import com.popular_movies.domain.movie.ReviewResponse;
import com.popular_movies.domain.common.TrailerResponse;

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

        void onCreditsRetreivalSuccess(CreditsResponse creditsResponse);
        void onCreditsRetreivalFailure(Throwable throwable);
    }

    interface Presenter {
        void fetchMovieDetails(int movieId);
        void fetchReviews(int movieId);
        void fetchTrailers(int movieId);
        void fetchMovieCollection(int movieId);
        void fetchMovieCredits(int movieId);
    }
}
