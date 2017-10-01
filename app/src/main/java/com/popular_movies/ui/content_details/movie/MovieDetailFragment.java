package com.popular_movies.ui.content_details.movie;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popular_movies.R;
import com.popular_movies.database.MovieProviderHelper;
import com.popular_movies.domain.common.Cast;
import com.popular_movies.domain.common.CreditsResponse;
import com.popular_movies.domain.common.Trailer;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.ui.content_details.DetailContentType;
import com.popular_movies.domain.movie.Movie;
import com.popular_movies.domain.movie.MovieCollection;
import com.popular_movies.domain.movie.MovieDetails;
import com.popular_movies.domain.movie.Review;
import com.popular_movies.domain.movie.ReviewResponse;
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.ui.FlowManager;
import com.popular_movies.ui.MovieItemClickListener;
import com.popular_movies.ui.content_details.BaseDetailFragment;
import com.popular_movies.ui.content_details.TrailerAdapter;
import com.popular_movies.util.AppUtils;
import com.popular_movies.util.DateConvert;
import com.popular_movies.util.TimeUtils;
import com.popular_movies.util.constants.IntentKeys;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailFragment extends BaseDetailFragment implements MovieDetailPresenter.View, ReviewsAdapter.NavigateReviewListener,
        TrailerAdapter.TrailerClickListener, MovieItemClickListener, CastAdapter.CastClickListener {

    private static final String TAG = MovieDetailFragment.class.getSimpleName();

    Movie movieData;
    private View view;
    private ReviewsAdapter reviewsAdapter;
    private LinearLayoutManager layoutManagerReview;
    private MovieDetailPresenterImpl movieDetailPresenterImpl;

    public static MovieDetailFragment getInstance(Parcelable movie) {
        MovieDetailFragment detailsFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(IntentKeys.KEY_DETAIL_CONTENT, movie);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            //       WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getActivity().getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));

        }
*/
        view = inflater.inflate(R.layout.fragment_detailed_view, container, false);
        ButterKnife.bind(this, view);

        diagonalLayout.setVisibility(View.VISIBLE);
        movieData = getArguments().getParcelable(getString(R.string.key_detail_content));
        layoutManagerReview = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvReviews.setLayoutManager(layoutManagerReview);

        if (!AppUtils.isTablet(getContext())) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
                // ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

        }

        if (movieData != null) {
            title.setText(movieData.getOriginal_title());

            //releaseDate.append(" " + DateConvert.convert(movieData.getRelease_date()));
            releaseDate.setText(DateConvert.convert(movieData.getRelease_date()));
            synopsis.setText(movieData.getOverview());
            if (movieData.getVote_average() == 0f) {
                userRatings.setText(getString(R.string.NA));
            } else {
                userRatings.setText(movieData.getVote_average().toString());
            }

//            ImageLoader.loadBackdropImage(getContext(), movieData.getBackdrop_path(), toolbarImage);
            ImageLoader.loadPosterImage(getContext(), movieData.getPoster_path(), toolbarImage, 4);

            ivFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (MovieProviderHelper.getInstance().doesMovieExist(movieData.getId())) {
                        ivFavorite.setImageResource(R.drawable.ic_favorite_border);
                        //  delete movie from database
                        MovieProviderHelper.getInstance().delete(movieData.getId());
                        Snackbar.make(view, getString(R.string.removed) + " " + movieData.getOriginal_title() +
                                " " + getString(R.string.from_favourite), Snackbar.LENGTH_LONG)
                                .show();
                    } else {
                        ivFavorite.setImageResource(R.drawable.ic_favorite_filled);
                        //  add movie to database
                        MovieProviderHelper.getInstance().insert(movieData);
                        Snackbar.make(view, getString(R.string.added) + " " + movieData.getOriginal_title() +
                                " " + getString(R.string.to_favourite), Snackbar.LENGTH_LONG)
                                .show();
                    }
                }
            });

            movieDetailPresenterImpl = new MovieDetailPresenterImpl(this, getActivity());
            movieDetailPresenterImpl.fetchTrailers(movieData.getId());
            movieDetailPresenterImpl.fetchReviews(movieData.getId());
            movieDetailPresenterImpl.fetchMovieDetails(movieData.getId());
            movieDetailPresenterImpl.fetchMovieCredits(movieData.getId());

        }
        if (MovieProviderHelper.getInstance().doesMovieExist(movieData.getId())) {
            ivFavorite.setImageResource(R.drawable.ic_favorite_filled);
        }
        return view;
    }

    @Override
    public void onReviewsRetreivalSuccess(ReviewResponse reviewResponse) {
        pbReviews.setVisibility(View.GONE);
        if (reviewResponse.getResults().size() > 0) {
            List<Review> reviewList = reviewResponse.getResults();
            if (reviewList != null) {
                SnapHelper snapHelper = new LinearSnapHelper();
                snapHelper.attachToRecyclerView(rvReviews);
                reviewsAdapter = new ReviewsAdapter(getContext(), reviewList, this);
                rvReviews.setAdapter(reviewsAdapter);
            }
        }
        //  if no reviews available
        else {
            tvNoReviews.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onReviewsRetreivalFailure(Throwable throwable) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), getString(R.string.connection_error), Snackbar.LENGTH_LONG)
                .show();
        pbReviews.setVisibility(View.GONE);
    }

    @Override
    public void onTrailersRetreivalSuccess(TrailerResponse trailerResponse) {
        pbTrailers.setVisibility(View.GONE);
        if (trailerResponse.getResults().size() > 0) {
            List<Trailer> listTrailers = new ArrayList<>();
            if (getContext() != null) {
                for (Trailer trailer : trailerResponse.getResults()) {
                    if (trailer.getSite().equalsIgnoreCase(getContext().getString(R.string.youtube))) {
                        listTrailers.add(trailer);
                    }
                }
                dsvTrailers.setAdapter(new TrailerAdapter(listTrailers, this));
                dsvTrailers.setItemTransformer(new ScaleTransformer.Builder()
                        .setMinScale(0.8f)
                        .build());
            }
        }
        //  if no trailers available
        else {
            tvNoTrailers.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTrailersRetreivalFailure(Throwable throwable) {
        pbTrailers.setVisibility(View.GONE);
        Snackbar.make(view, getString(R.string.connection_error), Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onMovieDetailsRetreivalSuccess(MovieDetails movieDetails) {
        //  display movie genre and duration
        if (movieDetails.getGenres() != null) {
            StringBuilder sbGenre = new StringBuilder();
            for (int i = 0; i < movieDetails.getGenres().length; i++) {
                if (i != 0)
                    sbGenre.append(" | ");
                sbGenre.append(movieDetails.getGenres()[i].getName());
            }
            tvGenre.setText(sbGenre);
        }
        if (movieDetails.getRuntime() == null || movieDetails.getRuntime() == 0) {
            tvDuration.setText(getString(R.string.NA));
        } else {
            tvDuration.setText(TimeUtils.formatDuration(movieDetails.getRuntime()));
        }

        if (movieDetails.getBelongs_to_collection() != null) {
            movieDetailPresenterImpl.fetchMovieCollection(movieDetails.getBelongs_to_collection().getId());
        }
        //  if no collections available
        else {
            pbCollection.setVisibility(View.GONE);
            tvNoCollection.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onMovieDetailsRetreivalFailure(Throwable throwable) {
    }

    @Override
    public void onMovieCollectionRetreivalSuccess(MovieCollection movieCollection) {
        pbCollection.setVisibility(View.GONE);
        if (movieCollection.getParts().length > 0) {
            List<Movie> listMovie = new ArrayList<>();
            if (getContext() != null) {
                for (Movie movie : movieCollection.getParts()) {
                    if(movie.getId() != movieData.getId())
                        listMovie.add(movie);
                }
                dsvCollection.setAdapter(new MovieCollectionAdapter(listMovie, this));
                dsvCollection.setItemTransformer(new ScaleTransformer.Builder()
                        .setMinScale(0.8f)
                        .build());
            }
        }
        //  if no trailers available
        else {
            tvNoCollection.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onMovieCollectionRetreivalFailure(Throwable throwable) {
        pbCollection.setVisibility(View.GONE);
        tvNoCollection.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreditsRetreivalSuccess(CreditsResponse creditsResponse) {
        pbCast.setVisibility(View.GONE);
        if (creditsResponse != null && creditsResponse.getCast().length > 0) {
            List<Cast> listCast = new ArrayList<>();
            if (getContext() != null) {
                Collections.addAll(listCast, creditsResponse.getCast());
                dsvCast.setAdapter(new CastAdapter(listCast, this));
                dsvCast.setItemTransformer(new ScaleTransformer.Builder()
                        .setMinScale(0.8f)
                        .build());
            }
        }
        //  if no credits available
        else {
            tvNoCast.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreditsRetreivalFailure(Throwable throwable) {
        pbCast.setVisibility(View.GONE);
        tvNoCast.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNextClick(int pos) {
        rvReviews.smoothScrollToPosition(pos + 1);
    }

    @Override
    public void onPreviousClick(int pos) {
        if (pos > 0) {
            rvReviews.smoothScrollToPosition(pos - 1);
        }
    }

    @Override
    public void onTrailerClick(String key) {
        viewTrailerInYoutube(key);
    }

    @OnClick(R.id.ivBack)
    public void goBack() {
        getActivity().finish();
    }

    @Override
    public void itemClicked(View view, int position, Movie movieData) {
        FlowManager.moveToDetailsActivity(getContext(), DetailContentType.MOVIE, movieData);
    }

    @Override
    public void itemClicked(View view, int position, Cast cast) {
        //  TODO: Navigate to people's profile
    }
}
