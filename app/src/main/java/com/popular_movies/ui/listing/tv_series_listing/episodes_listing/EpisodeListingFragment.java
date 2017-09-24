package com.popular_movies.ui.listing.tv_series_listing.episodes_listing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.popular_movies.R;
import com.popular_movies.domain.tv.seasons.Episode;
import com.popular_movies.domain.tv.seasons.TvShowSeasonDetails;
import com.popular_movies.util.constants.IntentKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EpisodeListingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        EpisodePresenter.View, EpisodeClickListener {

    //  recycler view
    @BindView(R.id.rvMoviesList)
    public RecyclerView rvMoviesList;

    //  progress bar
    @BindView(R.id.pbMoviesList)
    ProgressBar pbMoviesList;

    private String TAG = EpisodeListingFragment.class.getSimpleName();
    public ArrayList<Episode> seasonsDataList = new ArrayList<>();
    public EpisodeListingAdapter adapterHorizontal;
    // public SwipeRefreshLayout refreshLayout;


    static String seriesType;
    private int tvId;
    private int seasonNumber;
    private View view;
    private EpisodePresenterImpl episodePresenterImpl;
    private LinearLayoutManager layoutManager;
    private Boolean loadingInProgress = false;
    private Map<Integer, String> mapGenre = new HashMap<>();

    public EpisodeListingFragment() {

    }

    public static EpisodeListingFragment getInstance(String title, int tvId, int seasonNumber) {
        EpisodeListingFragment fragment = new EpisodeListingFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IntentKeys.KEY_TITLE, title);
        bundle.putInt(IntentKeys.KEY_TV_ID, tvId);
        bundle.putInt(IntentKeys.KEY_SEASON_NUMBER, seasonNumber);

        //bundle.putString(fragment.getActivity().getString(R.string.key_title), title);
        fragment.setArguments(bundle);
        seriesType = title;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movies_list, container, false);
        ButterKnife.bind(this, view);

        tvId = getArguments().getInt(IntentKeys.KEY_TV_ID);
        seasonNumber = getArguments().getInt(IntentKeys.KEY_SEASON_NUMBER);

        episodePresenterImpl = new EpisodePresenterImpl(this, getActivity());
        pbMoviesList.setVisibility(View.VISIBLE);

        layoutManager = new LinearLayoutManager(getContext());
        rvMoviesList.setLayoutManager(layoutManager);
        rvMoviesList.setHasFixedSize(true);
        rvMoviesList.setNestedScrollingEnabled(false);

/*
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.progress_colors));
    */
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        episodePresenterImpl.fetchEpisodes(tvId, seasonNumber);
    }

    private void setEpisodeListingAdapter(List<Episode> listEpisodes, RecyclerView recyclerView) {
        adapterHorizontal = new EpisodeListingAdapter(getContext(), listEpisodes);
        adapterHorizontal.setClickListener(this);
        recyclerView.setAdapter(adapterHorizontal);
    }


    @Override
    public void onRefresh() {
        episodePresenterImpl.fetchEpisodes(tvId, seasonNumber);
    }


    @Override
    public void itemClicked(View view, int position, Episode episode) {
        //  TODO:
//        FlowManager.moveToDetailsActivity(getContext(), DetailContentType.EPISODE, episode);
    }

    @Override
    public void onEpisodesRetreivalSuccess(TvShowSeasonDetails tvShowSeasonDetails) {
        pbMoviesList.setVisibility(View.GONE);
        setEpisodeListingAdapter(tvShowSeasonDetails.getEpisodes(), rvMoviesList);
    }

    @Override
    public void onEpisodesRetreivalFailure(Throwable throwable) {
        pbMoviesList.setVisibility(View.GONE);
        Snackbar.make(view, getString(R.string.connection_error), Snackbar.LENGTH_LONG)
                .show();
    }
}

