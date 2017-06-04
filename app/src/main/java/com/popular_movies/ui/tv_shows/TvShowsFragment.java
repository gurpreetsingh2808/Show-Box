package com.popular_movies.ui.tv_shows;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.popular_movies.R;
import com.popular_movies.domain.tv.TvShow;
import com.popular_movies.domain.tv.TvShowResponse;
import com.popular_movies.domain.dictionary.DetailContentType;
import com.popular_movies.ui.content_details.MovieDetailActivity;
import com.popular_movies.ui.content_details.tv_series.TvShowDetailFragment;
import com.popular_movies.ui.movies_listing.MoviesListingActivity;
import com.popular_movies.util.AppUtils;
import com.popular_movies.util.constants.IntentKeys;
import com.popular_movies.util.constants.TitleKeyValues;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gurpreet on 26-05-2017.
 */

public class TvShowsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        TvShowsPresenter.View, View.OnClickListener, TvShowItemClickListener {

    //  recycler view
    @BindView(R.id.rvTopRated)
    public RecyclerView rvTopRated;
    @BindView(R.id.rvPopular)
    public RecyclerView rvPopular;
    @BindView(R.id.rvAiringToday)
    public RecyclerView rvAiringToday;
    @BindView(R.id.rvOnTheAir)
    public RecyclerView rvOnTheAir;

    //  progress bar
    @BindView(R.id.pbPopular)
    ProgressBar pbPopular;
    @BindView(R.id.pbTopRated)
    ProgressBar pbTopRated;
    @BindView(R.id.pbAiringToday)
    ProgressBar pbAiringToday;
    @BindView(R.id.pbOnTheAir)
    ProgressBar pbOnTheAir;

    @BindView(R.id.tvAiringTodayViewAll)
    TextView tvAiringTodayViewAll;
    @BindView(R.id.tvOnTheAirViewAll)
    TextView tvOnTheAirViewAll;
    @BindView(R.id.tvTopRatedViewAll)
    TextView tvTopRatedViewAll;
    @BindView(R.id.tvPopularViewAll)
    TextView tvPopularViewAll;

    public ArrayList<TvShow> tvShowDataList = new ArrayList<>();
    public TvShowAdapterHorizontal adapterHorizontal;
    private TvShowAdapterVertical adapterVertical;
    // public SwipeRefreshLayout refreshLayout;
    private static final String KEY_TITLE = "KEY_TITLE";
    static String seriesType;
    private View view;
    private TvShowsPresenterImpl tvShowsPresenterImpl;

    public TvShowsFragment() {

    }

    public static TvShowsFragment getInstance(String title) {
        TvShowsFragment fragment = new TvShowsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        //bundle.putString(fragment.getActivity().getString(R.string.key_title), title);
        fragment.setArguments(bundle);
        seriesType = title;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_tv_shows, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        rvPopular.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOnTheAir.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTopRated.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvAiringToday.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        rvPopular.setHasFixedSize(true);
        rvPopular.setNestedScrollingEnabled(false);
        rvTopRated.setHasFixedSize(true);
        rvTopRated.setNestedScrollingEnabled(false);
        rvAiringToday.setHasFixedSize(true);
        rvAiringToday.setNestedScrollingEnabled(false);
        rvOnTheAir.setHasFixedSize(true);
        rvOnTheAir.setNestedScrollingEnabled(false);
        /*
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.progress_colors));
    */
        tvShowsPresenterImpl = new TvShowsPresenterImpl(this, getActivity());

        pbTopRated.setVisibility(View.VISIBLE);
        pbPopular.setVisibility(View.VISIBLE);
        pbAiringToday.setVisibility(View.VISIBLE);
        pbOnTheAir.setVisibility(View.VISIBLE);

        tvAiringTodayViewAll.setOnClickListener(this);
        tvTopRatedViewAll.setOnClickListener(this);
        tvOnTheAirViewAll.setOnClickListener(this);
        tvPopularViewAll.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList(getString(R.string.key_data)) != null) {
            tvShowDataList = savedInstanceState.getParcelableArrayList(getString(R.string.key_data));
            pbTopRated.setVisibility(View.GONE);
            setHorizontalAdapter(tvShowDataList, rvTopRated);
        } else {
            tvShowsPresenterImpl.fetchShowsAiringToday();
            tvShowsPresenterImpl.fetchShowsOnTheAir();
            tvShowsPresenterImpl.fetchTopRatedShows();
            tvShowsPresenterImpl.fetchPopularShows();
            //tvShowsPresenterImpl.fetchLatestMovies();
        }
    }

    private void setVerticalAdapter(List<TvShow> tvShows, RecyclerView recyclerView) {
        List<TvShow> tvShowDataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tvShowDataList.add(tvShows.get(i));
        }
        adapterVertical = new TvShowAdapterVertical(getContext(), tvShowDataList);
        adapterVertical.setClickListener(this);
        if (recyclerView.getAdapter() != null) {
            recyclerView.swapAdapter(adapterVertical, false);
        } else {
            recyclerView.setAdapter(adapterVertical);
        }
    }

    private void setHorizontalAdapter(List<TvShow> tvShows, RecyclerView recyclerView) {
        List<TvShow> tvShowDataList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tvShowDataList.add(tvShows.get(i));
        }
        adapterHorizontal = new TvShowAdapterHorizontal(getContext(), tvShowDataList);
        adapterHorizontal.setClickListener(this);
        if (recyclerView.getAdapter() != null) {
            recyclerView.swapAdapter(adapterHorizontal, false);
        } else {
            recyclerView.setAdapter(adapterHorizontal);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (AppUtils.isTablet(getContext()) && newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvPopular.setLayoutManager(new GridLayoutManager(getContext(), 2));
            rvOnTheAir.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            rvPopular.setLayoutManager(new LinearLayoutManager(getContext()));
            rvOnTheAir.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (tvShowDataList != null) {
            outState.putParcelableArrayList(getString(R.string.key_data), tvShowDataList);
        }
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onTopRatedShowsRetreivalSuccess(TvShowResponse tvShowResponse) {
        pbTopRated.setVisibility(View.GONE);
        setHorizontalAdapter(tvShowResponse.getResults(), rvTopRated);
    }

    @Override
    public void onTopRatedShowsRetreivalFailure(Throwable throwable) {
        pbTopRated.setVisibility(View.GONE);
        Snackbar.make(view, getString(R.string.connection_error), Snackbar.LENGTH_LONG)
                .show();
        //progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPopularShowsRetreivalSuccess(TvShowResponse tvShowResponse) {
        setVerticalAdapter(tvShowResponse.getResults(), rvPopular);
        ////////// refreshLayout.setRefreshing(false);
        pbPopular.setVisibility(View.GONE);
    }

    @Override
    public void onPopularShowsRetreivalFailure(Throwable throwable) {
        Snackbar.make(view, getString(R.string.connection_error), Snackbar.LENGTH_LONG)
                .show();
        //////////// refreshLayout.setRefreshing(false);
        pbPopular.setVisibility(View.GONE);
    }

    @Override
    public void onTheAirShowsRetreivalSuccess(TvShowResponse tvShowResponse) {
        setVerticalAdapter(tvShowResponse.getResults(), rvOnTheAir);
        pbOnTheAir.setVisibility(View.GONE);
    }

    @Override
    public void onTheAirShowsRetreivalFailure(Throwable throwable) {
        pbOnTheAir.setVisibility(View.GONE);
    }

    @Override
    public void onShowsAiringTodayRetreivalSuccess(TvShowResponse tvShowResponse) {
        pbAiringToday.setVisibility(View.GONE);
        setHorizontalAdapter(tvShowResponse.getResults(), rvAiringToday);
    }

    @Override
    public void onShowsAiringTodayRetreivalFailure(Throwable throwable) {
        pbAiringToday.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvAiringTodayViewAll:
                openTvShowsListing(IntentKeys.KEY_NOW_PLAYING, TitleKeyValues.now_playing);
                break;
            case R.id.tvOnTheAirViewAll:
                openTvShowsListing(IntentKeys.KEY_UPCOMING, TitleKeyValues.upcoming);
                break;
            case R.id.tvTopRatedViewAll:
                openTvShowsListing(IntentKeys.KEY_TOP_RATED, TitleKeyValues.top_rated);
                break;
            case R.id.tvPopularViewAll:
                openTvShowsListing(IntentKeys.KEY_POPULAR, TitleKeyValues.popular);
                break;
        }
    }

    private void openTvShowsListing(String seriesType, String title) {
        Intent intent = new Intent(getActivity(), MoviesListingActivity.class);
        intent.putExtra(getString(R.string.key_movie_type), seriesType);
        intent.putExtra(getString(R.string.key_title), title);
        startActivity(intent);
    }

    @Override
    public void itemClicked(View view, int position, TvShow tvShow) {
        if (AppUtils.isTablet(getContext()) && AppUtils.isLandscape(getContext())) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
                    .replace(R.id.movie_detail, TvShowDetailFragment.getInstance(tvShow))
                    .commit();
        } else {
            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra(getString(R.string.key_detail_content), tvShow);
            intent.putExtra(getString(R.string.key_detail_content_type), DetailContentType.TV_SERIES);
                        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            AppBarLayout barLayout = (AppBarLayout) ((AppCompatActivity) context).findViewById(R.id.actionbar);
                            ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation((AppCompatActivity) context,
                                    Pair.create((View) thumbnail, context.getString(R.string.transition_name)),
                                    Pair.create((View) barLayout, context.getString(R.string.transition_name_action_bar)));
                            context.startActivity(intent, compat.toBundle());
                        } else*/
            startActivity(intent);
        }
    }
}
