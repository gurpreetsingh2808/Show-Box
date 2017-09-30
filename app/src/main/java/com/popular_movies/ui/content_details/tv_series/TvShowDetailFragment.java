package com.popular_movies.ui.content_details.tv_series;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popular_movies.R;
import com.popular_movies.domain.common.Cast;
import com.popular_movies.domain.common.CreditsResponse;
import com.popular_movies.domain.common.Trailer;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.ui.listing.ListingContentType;
import com.popular_movies.domain.tv.Season;
import com.popular_movies.domain.tv.TvShow;
import com.popular_movies.domain.tv.TvShowDetails;
import com.popular_movies.domain.tv.TvShowExternalIds;
import com.popular_movies.domain.tv.seasons.CommentsResponse;
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.ui.FlowManager;
import com.popular_movies.ui.content_details.BaseDetailFragment;
import com.popular_movies.ui.content_details.TrailerAdapter;
import com.popular_movies.ui.content_details.movie.CastAdapter;
import com.popular_movies.util.AppUtils;
import com.popular_movies.util.DateConvert;
import com.popular_movies.util.TimeUtils;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TvShowDetailFragment extends BaseDetailFragment implements TvShowDetailPresenter.View,
        TrailerAdapter.TrailerClickListener, CastAdapter.CastClickListener, TvShowSeasonClickListener, CommentsAdapter.NavigateReviewListener {

    private static final String TAG = TvShowDetailFragment.class.getSimpleName();

    private static final String KEY_DETAIL_CONTENT = "KEY_DETAIL_CONTENT";
    private TvShow tvShow;
    private TvShowDetails tvShowDetails;
    private List<Season> listSeason = null;
    private View view;
    private CommentsAdapter commentsAdapter;
    private LinearLayoutManager layoutManagerReview;
    private TvShowDetailPresenterImpl tvShowDetailPresenter;


    public static TvShowDetailFragment getInstance(Parcelable series) {
        TvShowDetailFragment detailsFragment = new TvShowDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DETAIL_CONTENT, series);
        //bundle.putParcelable(detailsFragment.getString(R.string.key_movie), movie);
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

        setHeadings();
        diagonalLayout.setVisibility(View.VISIBLE);
        tvShow = getArguments().getParcelable(getString(R.string.key_detail_content));
        layoutManagerReview = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvReviews.setLayoutManager(layoutManagerReview);

        if (!AppUtils.isTablet(getContext())) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
                // ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

        }

        //  filling tv show details received from previous screen
        if (tvShow != null) {
            title.setText(tvShow.getOriginal_name());
//            set text to first air date in place of in theatres
            releaseDate.setText(DateConvert.convert(tvShow.getFirst_air_date()));
            synopsis.setText(tvShow.getOverview());
            userRatings.setText(tvShow.getVote_average().toString());

            ImageLoader.loadBackdropImage(getContext(), tvShow.getBackdrop_path(), toolbarImage);
            ////ImageLoader.loadPosterImage(getContext(), tvShow.getPoster_path(), toolbarImage, 4);

            ivFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (TvShowProviderHelper.getInstance().doesTvShowExist(tvShow.getId())) {
//                        ivFavorite.setImageResource(R.drawable.ic_favorite_border);
//                        //  delete movie from database
//                        TvShowProviderHelper.getInstance().delete(tvShow.getId());
//                        Snackbar.make(view, getString(R.string.removed) + " " + tvShow.getOriginal_title() +
//                                " " + getString(R.string.from_favourite), Snackbar.LENGTH_LONG)
//                                .show();
//                    } else {
//                        ivFavorite.setImageResource(R.drawable.ic_favorite_filled);
//                        //  add movie to database
//                        TvShowProviderHelper.getInstance().insert(tvShow);
//                        Snackbar.make(view, getString(R.string.added) + " " + tvShow.getOriginal_title() +
//                                " " + getString(R.string.to_favourite), Snackbar.LENGTH_LONG)
//                                .show();
//                    }
                }
            });

            tvShowDetailPresenter = new TvShowDetailPresenterImpl(this, getActivity());
            tvShowDetailPresenter.fetchTrailers(tvShow.getId());
            tvShowDetailPresenter.fetchTvShowExternalIds(tvShow.getId());
            tvShowDetailPresenter.fetchTvShowDetails(tvShow.getId());
            tvShowDetailPresenter.fetchTvShowCredits(tvShow.getId());


        }
//        if (TvShowProviderHelper.getInstance().doesTvShowExist(tvShow.getId())) {
//            ivFavorite.setImageResource(R.drawable.ic_favorite_filled);
//        }
        return view;
    }

    @Override
    protected void setHeadings() {
        tvHeaderReleaseDate.setText("Air Date");
        tvHeadingUserReviews.setText("Comments");
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
    public void onTvShowDetailsRetreivalSuccess(TvShowDetails tvShowDetails) {
        Log.d(TAG, "success");

        this.tvShowDetails = tvShowDetails;
//  display movie genre and duration
        if (tvShowDetails.getGenres() != null) {
            StringBuilder sbGenre = new StringBuilder();
            Log.d(TAG, "genre length" + tvShowDetails.getGenres().length);

            for (int i = 0; i < tvShowDetails.getGenres().length; i++) {
                if (i != 0)
                    sbGenre.append(" | ");
                sbGenre.append(tvShowDetails.getGenres()[i].getName());
            }
            tvGenre.setText(sbGenre);
            Log.d(TAG, sbGenre.toString());

        }
        tvDuration.setText(TimeUtils.formatDuration(tvShowDetails.getEpisode_run_time()[0]));

        //  display tv show seasons
        pbCollection.setVisibility(View.GONE);
        if (tvShowDetails.getNumber_of_seasons() > 1 && tvShowDetails.getSeasons() != null &&
                tvShowDetails.getSeasons().length > 0) {
            listSeason = new ArrayList<>();
            if (getContext() != null) {
                Collections.addAll(listSeason, tvShowDetails.getSeasons());
                dsvCollection.setAdapter(new TvShowSeasonAdapter(listSeason, this));
                dsvCollection.setItemTransformer(new ScaleTransformer.Builder()
                        .setMinScale(0.8f)
                        .build());
            }

        }
        //  if only one season available
        else {
            tvNoCollection.setVisibility(View.VISIBLE);
        }

        //   TODO : INCOMPLETE
    }

    @Override
    public void onTvShowDetailsRetreivalFailure(Throwable throwable) {

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
    public void onTvShowExternalIdsRetreivalSuccess(TvShowExternalIds tvShowExternalIds) {
        tvShowDetailPresenter.fetchComments(String.valueOf(tvShowExternalIds.getId()));
    }

    @Override
    public void onTvShowExternalIdsRetreivalFailure(Throwable throwable) {
        pbReviews.setVisibility(View.GONE);
        tvNoReviews.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCommentsRetreivalSuccess(List<CommentsResponse> commentsResponse) {
        pbReviews.setVisibility(View.GONE);
        if (commentsResponse.size() > 0) {
            SnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(rvReviews);
            commentsAdapter = new CommentsAdapter(getContext(), commentsResponse, this);
            rvReviews.setAdapter(commentsAdapter);
        }
        //  if no reviews available
        else {
            tvNoReviews.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCommentsRetreivalFailure(Throwable throwable) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), getString(R.string.connection_error), Snackbar.LENGTH_LONG)
                .show();
        pbReviews.setVisibility(View.GONE);
        tvNoReviews.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTrailerClick(String key) {
        FlowManager.viewTrailerInYoutube(getContext(), key, getActivity().findViewById(android.R.id.content));
    }

    @OnClick(R.id.ivBack)
    public void goBack() {
        getActivity().finish();
    }

    @Override
    public void itemClicked(View view, int position, Cast cast) {
        // TODO :
    }

    @Override
    public void seasonClicked(View view, int position, String title) {
        FlowManager.moveToListingActivity(getContext(), ListingContentType.EPISODES, tvShowDetails.getName() + " : Season "
                + listSeason.get(position).getSeason_number(), tvShow.getId(), listSeason.get(position).getSeason_number());
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
}
