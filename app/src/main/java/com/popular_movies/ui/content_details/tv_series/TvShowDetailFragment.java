package com.popular_movies.ui.content_details.tv_series;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.florent37.diagonallayout.DiagonalLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.popular_movies.BuildConfig;
import com.popular_movies.R;
import com.popular_movies.domain.common.Cast;
import com.popular_movies.domain.common.CreditsResponse;
import com.popular_movies.domain.common.Trailer;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.dictionary.DetailContentType;
import com.popular_movies.domain.tv.Season;
import com.popular_movies.domain.tv.TvShow;
import com.popular_movies.domain.tv.TvShowDetails;
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.ui.FlowManager;
import com.popular_movies.ui.activity.ReviewActivity;
import com.popular_movies.ui.content_details.TrailerAdapter;
import com.popular_movies.ui.content_details.movie.CastAdapter;
import com.popular_movies.ui.content_details.movie.ReviewsAdapter;
import com.popular_movies.util.AppUtils;
import com.popular_movies.util.DateConvert;
import com.popular_movies.util.TimeUtils;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class TvShowDetailFragment extends Fragment implements TvShowDetailPresenter.View,
        TrailerAdapter.TrailerClickListener, CastAdapter.CastClickListener, TvShowSeasonClickListener {

    private static final String TAG = TvShowDetailFragment.class.getSimpleName();
    //  toolbar
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //  textview
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.releaseDate)
    TextView releaseDate;
    @BindView(R.id.tvHeaderReleaseDate)
    TextView tvHeaderReleaseDate;
    @BindView(R.id.synopsis)
    TextView synopsis;
    @BindView(R.id.userRatings)
    TextView userRatings;
    @BindView(R.id.tvNoTrailers)
    TextView tvNoTrailers;
    @BindView(R.id.tvNoReviews)
    TextView tvNoReviews;
    @BindView(R.id.tvGenre)
    TextView tvGenre;
    @BindView(R.id.tvDuration)
    TextView tvDuration;
    @BindView(R.id.tvNoCast)
    TextView tvNoCast;
    @BindView(R.id.tvNoCollection)
    TextView tvNoCollection;

    //  image view
    @BindView(R.id.toolbarImage)
    ImageView toolbarImage;
    @BindView(R.id.ivBack)
    AppCompatImageView acivBack;

    //  circular progress bar
    @BindView(R.id.indicator)
    CircleIndicator indicator;

    //  recycler view
    @BindView(R.id.rvReviews)
    RecyclerView rvReviews;

    //  progress bar
    @BindView(R.id.pbReviews)
    ProgressBar pbReviews;
    @BindView(R.id.pbTrailers)
    ProgressBar pbTrailers;
    @BindView(R.id.pbCollection)
    ProgressBar pbCollection;
    @BindView(R.id.pbCast)
    ProgressBar pbCast;

    //  favoite icon
    @BindView(R.id.ivFavorite)
    AppCompatImageView ivFavorite;
    //  button
    @BindView(R.id.buttonUserReviews)
    Button btnUserReview;

    @BindView(R.id.diagonalLayout)
    DiagonalLayout diagonalLayout;
    @BindView(R.id.dsvTrailers)
    DiscreteScrollView dsvTrailers;
    @BindView(R.id.dsvCollection)
    DiscreteScrollView dsvCollection;
    @BindView(R.id.dsvCast)
    DiscreteScrollView dsvCast;

    private static final String KEY_DETAIL_CONTENT = "KEY_DETAIL_CONTENT";
    TvShow tvShow;
    private InterstitialAd mInterstitialAd;
    private View view;
    private ReviewsAdapter reviewsAdapter;
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

        initializeAd();
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
            tvHeaderReleaseDate.setText("Release date");
            releaseDate.setText(DateConvert.convert(tvShow.getFirst_air_date()));
            synopsis.setText(tvShow.getOverview());
            userRatings.setText(tvShow.getVote_average());
            btnUserReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAd();
                }
            });

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
            tvShowDetailPresenter.fetchTvShowDetails(tvShow.getId());
            tvShowDetailPresenter.fetchTvShowCredits(tvShow.getId());


        }
//        if (TvShowProviderHelper.getInstance().doesTvShowExist(tvShow.getId())) {
//            ivFavorite.setImageResource(R.drawable.ic_favorite_filled);
//        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mInterstitialAd.isLoaded()) {
            requestNewInterstitial();
        }
    }

    private void initializeAd() {
        //  initialize interstitial ad
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                moveToReviewActivity();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d(TAG, "onAdLoaded: woohoo! add loaded successfully");
            }
        });
    }


    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("BC6C6B77CEF61830841859B30835E10C")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


    private void showAd() {
        //  check whether app is loaded or not
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            moveToReviewActivity();
        }
    }

    private void moveToReviewActivity() {
        Intent intent = new Intent(getActivity(), ReviewActivity.class);
        intent.putExtra(getString(R.string.key_movie_id), tvShow.getId());
        startActivity(intent);
    }

    private void viewTrailerInYoutube(String trailerKey) {
        if (trailerKey != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(BuildConfig.BASE_URL_TRAILER + trailerKey));
            startActivity(intent);
        } else {
            Snackbar.make(getActivity().findViewById(android.R.id.content), getString(R.string.trailer_error),
                    Snackbar.LENGTH_SHORT).show();
        }
    }


    public TextView getTitle() {
        return title;
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
            List<Season> listSeason = new ArrayList<>();
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
    public void onTrailerClick(String key) {
        viewTrailerInYoutube(key);
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
    public void seasonClicked(View view, int position, Season season) {
        // TODO :
    }
}
