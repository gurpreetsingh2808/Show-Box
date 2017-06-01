package com.popular_movies.service.movie;

import android.app.Activity;

import com.popular_movies.BuildConfig;
import com.popular_movies.domain.Genre;
import com.popular_movies.domain.GenreResponse;
import com.popular_movies.domain.MovieCollection;
import com.popular_movies.domain.MovieDetails;
import com.popular_movies.domain.MovieResponse;
import com.popular_movies.domain.ReviewResponse;
import com.popular_movies.domain.TrailerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Gurpreet on 20-01-2017.
 */

public interface MovieService {

    public interface MovieResource {
        @GET("movie/{movieType}?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<MovieResponse> getMovies(@Path("movieType") String movieType, @Query("page") String page);

        @GET("movie/popular?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<MovieResponse> getPopularMovies();

        @GET("movie/top_rated?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<MovieResponse> getTopRatedMovies();

        @GET("movie/upcoming?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<MovieResponse> getUpcomingMovies();

        @GET("movie/latest?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<MovieResponse> getLatestMovies();

        @GET("movie/now_playing?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<MovieResponse> getNowPlayingMovies();

        @GET("movie/{id}?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<MovieDetails> getMovieDetails(@Path("id") int id);

        @GET("movie/{id}/videos?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<TrailerResponse> getTrailers(@Path("id") int id);

        @GET("movie/{id}/reviews?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<ReviewResponse> getReview(@Path("id") int id);

        @GET("collection/{id}?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<MovieCollection> getCollection(@Path("id") int id);

        @GET("search/movie?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<MovieResponse> getSearchResults(@Query("query") String searchQuery);

        @GET("genre/movie/list?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<GenreResponse> getGenres();
    }

    /**
     * Get movies model
     */
    void getMovies(String movieType, String pageNumber, Activity activity, GetMoviesCallback getMoviesCalback);

    interface GetMoviesCallback {
        void onSuccess(MovieResponse movieResponse);

        void onFailure(Throwable throwable);
    }

    void getPopularMovies(Activity activity, GetMoviesCallback getMoviesCalback);

    void getTopRatedMovies(Activity activity, GetMoviesCallback getMoviesCalback);

    void getUpcomingMovies(Activity activity, GetMoviesCallback getUpcomingMoviesCalback);

    void getNowPlayingMovies(Activity activity, GetMoviesCallback getNowPlayingMoviesCalback);

    void getLatestMovies(Activity activity, GetMoviesCallback getNowPlayingMoviesCalback);

    /**
     * Get movie details model
     */
    void getMovieDetails(int id, Activity activity, GetMovieDetailsCallback getMovieDetailsCallback);

    interface GetMovieDetailsCallback {
        void onSuccess(MovieDetails movieDetails);

        void onFailure(Throwable throwable);
    }

    /**
     * Get movie trailers model
     */
    void getTrailers(int id, Activity activity, GetTrailersCallback getTrailersCallback);

    interface GetTrailersCallback {
        void onSuccess(TrailerResponse trailerResponse);

        void onFailure(Throwable throwable);
    }

    /**
     * Get movie reviews model
     */
    void getReviews(int id, Activity activity, GetReviewsCallback getReviewsCallback);

    interface GetReviewsCallback {
        void onSuccess(ReviewResponse reviewResponse);
        void onFailure(Throwable throwable);
    }

    /**
     * Get movie details model
     */
    void getMovieCollection(int id, Activity activity, GetMovieCollectionCallback getMovieCollectionCallback);

    interface GetMovieCollectionCallback {
        void onSuccess(MovieCollection movieCollection);

        void onFailure(Throwable throwable);
    }

    /**
     * search model
     */
    void getSearchResults(String query, Activity activity, GetMoviesCallback getMoviesCalback);

    /**
     * genre model
     */
    void getGenre(Activity activity, FetchGenresCallback fetchGenresCallback);

    interface FetchGenresCallback {
        void onSuccess(GenreResponse genreResponse);
        void onFailure(Throwable throwable);
    }

}
