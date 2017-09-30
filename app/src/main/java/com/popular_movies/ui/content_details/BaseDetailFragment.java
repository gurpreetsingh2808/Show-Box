package com.popular_movies.ui.content_details;

import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.florent37.diagonallayout.DiagonalLayout;
import com.popular_movies.R;
import com.popular_movies.ui.FlowManager;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Gurpreet on 30-09-2017.
 */

public class BaseDetailFragment extends Fragment {

    //  toolbar
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    //  textview
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.releaseDate)
    public TextView releaseDate;
    @BindView(R.id.tvHeaderReleaseDate)
    public TextView tvHeaderReleaseDate;
    @BindView(R.id.synopsis)
    public TextView synopsis;
    @BindView(R.id.userRatings)
    public TextView userRatings;
    @BindView(R.id.tvNoTrailers)
    public TextView tvNoTrailers;
    @BindView(R.id.tvNoReviews)
    public TextView tvNoReviews;
    @BindView(R.id.tvGenre)
    public TextView tvGenre;
    @BindView(R.id.tvDuration)
    public TextView tvDuration;
    @BindView(R.id.tvNoCast)
    public TextView tvNoCast;
    @BindView(R.id.tvNoCollection)
    public TextView tvNoCollection;

    @BindView(R.id.tvHeadingUserReviews)
    public TextView tvHeadingUserReviews;
    @BindView(R.id.tvHeadingCast)
    public TextView tvHeadingCast;

    //  image view
    @BindView(R.id.toolbarImage)
    public ImageView toolbarImage;
    @BindView(R.id.ivBack)
    public AppCompatImageView acivBack;

    //  circular progress bar
    @BindView(R.id.indicator)
    public CircleIndicator indicator;

    //  recycler view
    @BindView(R.id.rvReviews)
    public RecyclerView rvReviews;

    //  progress bar
    @BindView(R.id.pbReviews)
    public ProgressBar pbReviews;
    @BindView(R.id.pbTrailers)
    public ProgressBar pbTrailers;
    @BindView(R.id.pbCollection)
    public ProgressBar pbCollection;
    @BindView(R.id.pbCast)
    public ProgressBar pbCast;

    //  favoite icon
    @BindView(R.id.ivFavorite)
    public AppCompatImageView ivFavorite;

    @BindView(R.id.diagonalLayout)
    public DiagonalLayout diagonalLayout;
    @BindView(R.id.dsvTrailers)
    public DiscreteScrollView dsvTrailers;
    @BindView(R.id.dsvCollection)
    public DiscreteScrollView dsvCollection;
    @BindView(R.id.dsvCast)
    public DiscreteScrollView dsvCast;


    protected void setHeadings() {
    }

    protected void viewTrailerInYoutube(String trailerKey) {
        FlowManager.viewTrailerInYoutube(getContext(), trailerKey, getActivity().findViewById(android.R.id.content));
    }

    public TextView getTitle() {
        return title;
    }

}
