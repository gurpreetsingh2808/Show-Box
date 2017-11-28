package com.popular_movies.service.movie;

import android.app.Activity;

import com.popular_movies.BuildConfig;
import com.popular_movies.domain.common.CreditsResponse;
import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.filter.Filter;
import com.popular_movies.domain.movie.MovieCollection;
import com.popular_movies.domain.movie.MovieDetails;
import com.popular_movies.domain.movie.MovieResponse;
import com.popular_movies.domain.movie.ReviewResponse;
import com.popular_movies.domain.common.TrailerResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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

        @GET("movie/{id}/credits?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<CreditsResponse> getCredits(@Path("id") int id);

        @GET("collection/{id}?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<MovieCollection> getCollection(@Path("id") int id);

        @GET("search/movie?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<MovieResponse> getSearchResults(@Query("query") String searchQuery);

        @GET("genre/movie/list?api_key=" + BuildConfig.TMDB_API_KEY)
        Call<GenreResponse> getGenres();

        @GET("discover/movie?api_key=" + BuildConfig.TMDB_API_KEY + "")
        Call<MovieResponse> getMoviesWithFilter(@QueryMap Map<String, String> filter, @Query("page") String page);

    }

    /**
     * Get movies model
     */
    void getMovies(String movieType, String pageNumber, Activity activity, GetMoviesCallback getMoviesCallback);

    interface GetMoviesCallback {
        void onSuccess(MovieResponse movieResponse);

        void onFailure(Throwable throwable);
    }

    void getPopularMovies(Activity activity, GetMoviesCallback getMoviesCallback);

    void getTopRatedMovies(Activity activity, GetMoviesCallback getMoviesCallback);

    void getUpcomingMovies(Activity activity, GetMoviesCallback getUpcomingMoviesCallback);

    void getNowPlayingMovies(Activity activity, GetMoviesCallback getNowPlayingMoviesCallback);

    void getLatestMovies(Activity activity, GetMoviesCallback getLatestMoviesCallback);

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
     * Get movie credits model
     */
    void getCredits(int id, Activity activity, GetCreditsCallback getCreditsCallback);

    interface GetCreditsCallback {
        void onSuccess(CreditsResponse creditResponse);
        void onFailure(Throwable throwable);
    }

    /**
     * search model
     */
    void getSearchResults(String query, Activity activity, GetMoviesCallback getMoviesCallback);

    /**
     * genre model
     */
    void getGenre(Activity activity, FetchGenresCallback fetchGenresCallback);

    interface FetchGenresCallback {
        void onSuccess(GenreResponse genreResponse);
        void onFailure(Throwable throwable);
    }

    /**
     * Get filtered movies
     *
     * @param filter
     * @param pageNumber
     * @param activity
     * @param getMoviesCallback
     */
    void getMovies(Filter filter, String pageNumber, Activity activity, GetMoviesCallback getMoviesCallback);

}
