package com.popular_movies.ui.content_details.tv_series.episode;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popular_movies.R;
import com.popular_movies.domain.common.Trailer;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.seasons.Crew;
import com.popular_movies.domain.tv.seasons.Episode;
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.ui.content_details.BaseDetailFragment;
import com.popular_movies.ui.content_details.TrailerAdapter;
import com.popular_movies.ui.content_details.movie.ReviewsAdapter;
import com.popular_movies.util.AppUtils;
import com.popular_movies.util.DateConvert;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EpisodeDetailFragment extends BaseDetailFragment implements EpisodeDetailPresenter.View,
        TrailerAdapter.TrailerClickListener, CrewAdapter.CastClickListener {

    private static final String TAG = EpisodeDetailFragment.class.getSimpleName();

    private static final String KEY_DETAIL_CONTENT = "KEY_DETAIL_CONTENT";
    Episode episode;
    private View view;
    private ReviewsAdapter reviewsAdapter;
    private LinearLayoutManager layoutManagerReview;
    private EpisodeDetailPresenterImpl episodeDetailPresenter;


    public static EpisodeDetailFragment getInstance(Parcelable series) {
        EpisodeDetailFragment detailsFragment = new EpisodeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DETAIL_CONTENT, series);
        //bundle.putParcelable(detailsFragment.getString(R.string.key_movie), movie);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detailed_view, container, false);
        ButterKnife.bind(this, view);

        setHeadings();
        diagonalLayout.setVisibility(View.VISIBLE);
        episode = getArguments().getParcelable(getString(R.string.key_detail_content));
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
        if (episode != null) {
            title.setText(episode.getName());
//            set text to first air date in place of in theatres

            synopsis.setText(episode.getOverview());
            userRatings.setText(episode.getVote_average().toString());
            setDetails(episode);

            ivFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (episodeProviderHelper.getInstance().doesepisodeExist(episode.getId())) {
//                        ivFavorite.setImageResource(R.drawable.ic_favorite_border);
//                        //  delete movie from database
//                        episodeProviderHelper.getInstance().delete(episode.getId());
//                        Snackbar.make(view, getString(R.string.removed) + " " + episode.getOriginal_title() +
//                                " " + getString(R.string.from_favourite), Snackbar.LENGTH_LONG)
//                                .show();
//                    } else {
//                        ivFavorite.setImageResource(R.drawable.ic_favorite_filled);
//                        //  add movie to database
//                        episodeProviderHelper.getInstance().insert(episode);
//                        Snackbar.make(view, getString(R.string.added) + " " + episode.getOriginal_title() +
//                                " " + getString(R.string.to_favourite), Snackbar.LENGTH_LONG)
//                                .show();
//                    }
                }
            });

            episodeDetailPresenter = new EpisodeDetailPresenterImpl(this, getActivity());
            episodeDetailPresenter.fetchTrailers(episode.getId(), episode.getSeason_number());


        }
//        if (episodeProviderHelper.getInstance().doesepisodeExist(episode.getId())) {
//            ivFavorite.setImageResource(R.drawable.ic_favorite_filled);
//        }
        return view;
    }


    @Override
    protected void setHeadings() {
        tvHeaderReleaseDate.setText("Air Date");
        tvHeadingCast.setText("Crew");
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


    private void setDetails(Episode episodeDetails) {
        Log.d(TAG, "success");


        tvHeaderReleaseDate.setText("Air date");
        releaseDate.setText(DateConvert.convert(episode.getAir_date().toString()));
        ImageLoader.loadPosterImage(getContext(), episode.getStill_path(), toolbarImage, 4);

        pbCast.setVisibility(View.GONE);
        if (episodeDetails.getCrew() != null && episodeDetails.getCrew().size() > 0) {
            if (getContext() != null) {
                dsvCast.setAdapter(new CrewAdapter(episodeDetails.getCrew(), this));
                dsvCast.setItemTransformer(new ScaleTransformer.Builder()
                        .setMinScale(0.8f)
                        .build());
            }
        } else {
            tvNoCast.setVisibility(View.VISIBLE);
        }

//        tvDuration.setText(TimeUtils.formatDuration(episodeDetails.get()[0]));


        //   TODO : INCOMPLETE
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
    public void itemClicked(View view, int position, Crew crew) {

    }
}
