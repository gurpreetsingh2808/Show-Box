package com.popular_movies.ui.listing.search_listing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.popular_movies.R;
import com.popular_movies.domain.search.Search;
import com.popular_movies.domain.search.SearchResponse;
import com.popular_movies.domain.tv.TvShow;
import com.popular_movies.domain.tv.seasons.Episode;
import com.popular_movies.domain.tv.seasons.TvShowSeasonDetails;
import com.popular_movies.ui.FlowManager;
import com.popular_movies.ui.content_details.DetailContentType;
import com.popular_movies.ui.listing.tv_series_listing.TvSeriesListingAdapter;
import com.popular_movies.ui.listing.tv_series_listing.episodes_listing.EpisodeClickListener;
import com.popular_movies.ui.listing.tv_series_listing.episodes_listing.EpisodeListingAdapter;
import com.popular_movies.ui.listing.tv_series_listing.episodes_listing.EpisodePresenter;
import com.popular_movies.ui.listing.tv_series_listing.episodes_listing.EpisodePresenterImpl;
import com.popular_movies.util.constants.IntentKeys;
import com.popular_movies.util.pagination.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchListingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        SearchPresenter.View, SearchItemClickListener {

    //  recycler view
    @BindView(R.id.rvMoviesList)
    public RecyclerView rvMoviesList;

    //  progress bar
    @BindView(R.id.pbMoviesList)
    ProgressBar pbMoviesList;

    private String TAG = SearchListingFragment.class.getSimpleName();
    //    public ArrayList<Search> searchResultsList = new ArrayList<>();
    public SearchListingAdapter adapterHorizontal;
    // public SwipeRefreshLayout refreshLayout;


    static String seriesType;
    private String searchTerm;
    private View view;
    private SearchPresenterImpl searchPresenterImpl;
    private Boolean loadingInProgress = false;
    private Integer pageNumber = 1;
    private LinearLayoutManager layoutManager;
    private Map<Integer, String> mapGenre = new HashMap<>();

    public SearchListingFragment() {

    }

    public static SearchListingFragment getInstance(String searchTerm) {
        SearchListingFragment fragment = new SearchListingFragment();
        Bundle bundle = new Bundle();
        Log.e(SearchListingFragment.class.getSimpleName(), "getInstance: searchTerm " + searchTerm);
        bundle.putString(IntentKeys.KEY_SEARCH, searchTerm);

        fragment.setArguments(bundle);
//        seriesType = title;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movies_list, container, false);
        ButterKnife.bind(this, view);

        searchTerm = getArguments().getString(IntentKeys.KEY_SEARCH);
        Log.e(TAG, "onCreateView: search term " + searchTerm);

        searchPresenterImpl = new SearchPresenterImpl(this, getActivity());
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
        searchPresenterImpl.searchAll(searchTerm, pageNumber);
        rvMoviesList.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                pageNumber++;
                adapterHorizontal.add(null);
                loadingInProgress = true;
                searchPresenterImpl.searchAll(searchTerm, pageNumber);
            }

            @Override
            public Boolean isLoading() {
                // Indicate whether new page loading is in progress or not
                return loadingInProgress;
            }
        });

    }


    @Override
    public void onRefresh() {
        pageNumber = 1;
        searchPresenterImpl.searchAll(searchTerm, pageNumber);
    }

    private void setHorizontalAdapter(List<Search> listSearchItems, RecyclerView recyclerView) {
        adapterHorizontal = new SearchListingAdapter(getContext(), listSearchItems);
        adapterHorizontal.setClickListener(this);
        if (recyclerView.getAdapter() != null) {
            recyclerView.swapAdapter(adapterHorizontal, false);
        } else {
            recyclerView.setAdapter(adapterHorizontal);
        }
    }


    @Override
    public void onSearchAllRetreivalSuccess(SearchResponse searchResponse) {
        pbMoviesList.setVisibility(View.GONE);
        if (adapterHorizontal == null) {
            setHorizontalAdapter(searchResponse.getResults(), rvMoviesList);
        } else {
            loadingInProgress = false;
            adapterHorizontal.remove();
            adapterHorizontal.addAll(searchResponse.getResults());
        }
    }

    @Override
    public void onSearchAllRetreivalFailure(Throwable throwable) {
        if (adapterHorizontal == null) {
            pbMoviesList.setVisibility(View.GONE);
        } else {
            loadingInProgress = false;
            adapterHorizontal.remove();
        }
        Snackbar.make(view, getString(R.string.connection_error), Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void itemClicked(View view, int position, Search search) {
        FlowManager.moveToDetailsActivity(getContext(), DetailContentType.SEARCH, search);
    }
}

