package com.popular_movies.ui.listing.movies_listing;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.popular_movies.R;
import com.popular_movies.domain.common.Genre;
import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.ui.content_details.DetailContentType;
import com.popular_movies.domain.movie.Movie;
import com.popular_movies.domain.movie.MovieResponse;
import com.popular_movies.ui.FlowManager;
import com.popular_movies.ui.MovieItemClickListener;
import com.popular_movies.ui.listing.movies_listing.filter.MyFabFragment;
import com.popular_movies.util.DateConvert;
import com.popular_movies.util.pagination.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        MoviesPresenter.View, MovieItemClickListener
/*, MovieAdapterHorizontal.ClickListener*/ {

    //  recycler view
    @BindView(R.id.rvMoviesList)
    public RecyclerView rvMoviesList;

    //  progress bar
    @BindView(R.id.pbMoviesList)
    ProgressBar pbMoviesList;

    private String TAG = MovieListFragment.class.getSimpleName();
    public ArrayList<Movie> movieDataList = new ArrayList<>();
    public MovieListingAdapter adapterHorizontal;
    // public SwipeRefreshLayout refreshLayout;
    private static final String KEY_TITLE = "KEY_TITLE";
    static String movieType;
    private View view;
    private MoviesPresenterImpl moviesPresenterImpl;
    private Integer pageNumber = 1;
    private LinearLayoutManager layoutManager;
    private Boolean loadingInProgress = false;
    private Map<Integer, String> mapGenre = new HashMap<>();
    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();


    public MovieListFragment() {

    }

    public static MovieListFragment getInstance(String title) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        //bundle.putString(fragment.getActivity().getString(R.string.key_title), title);
        fragment.setArguments(bundle);
        movieType = title;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movies_list, container, false);
        ButterKnife.bind(this, view);
        moviesPresenterImpl = new MoviesPresenterImpl(this, getActivity());
        pbMoviesList.setVisibility(View.VISIBLE);

        layoutManager = new LinearLayoutManager(getContext());
        rvMoviesList.setLayoutManager(layoutManager);
        rvMoviesList.setHasFixedSize(true);
        rvMoviesList.setNestedScrollingEnabled(false);

        final MyFabFragment dialogFrag1 = MyFabFragment.newInstance();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        dialogFrag1.setParentFab(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFrag1.show(getChildFragmentManager(), dialogFrag1.getTag());
            }
        });

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
        /*if (savedInstanceState != null && savedInstanceState.getParcelableArrayList(getString(R.string.key_data)) != null) {
            movieDataList = savedInstanceState.getParcelableArrayList(getString(R.string.key_data));
            pbMoviesList.setVisibility(View.GONE);
            setHorizontalAdapter(movieDataList, rvMoviesList);
        } else {*/
//            moviesPresenterImpl.fetchMovies(movieType, String.valueOf(pageNumber));
        // }
        moviesPresenterImpl.fetchGenres();
        rvMoviesList.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                pageNumber++;
                adapterHorizontal.add(null);
                loadingInProgress = true;
                moviesPresenterImpl.fetchMovies(movieType, String.valueOf(pageNumber));
            }

            @Override
            public Boolean isLoading() {
                // Indicate whether new page loading is in progress or not
                return loadingInProgress;
            }
        });
    }

    private void setHorizontalAdapter(List<Movie> listMovies, RecyclerView recyclerView) {
        adapterHorizontal = new MovieListingAdapter(getContext(), listMovies);
        adapterHorizontal.setClickListener(this);
        if (recyclerView.getAdapter() != null) {
            recyclerView.swapAdapter(adapterHorizontal, false);
        } else {
            recyclerView.setAdapter(adapterHorizontal);
        }
        adapterHorizontal.setGenre(mapGenre);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvMoviesList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvMoviesList.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (movieDataList != null) {
            outState.putParcelableArrayList(getString(R.string.key_data), movieDataList);
        }
    }

    @Override
    public void onRefresh() {
        pageNumber = 1;
        moviesPresenterImpl.fetchMovies(movieType, String.valueOf(pageNumber));
    }

    @Override
    public void onMoviesRetreivalSuccess(MovieResponse movieResponse) {
        pbMoviesList.setVisibility(View.GONE);
        if (adapterHorizontal == null) {
            setHorizontalAdapter(movieResponse.getResults(), rvMoviesList);
        } else {
            loadingInProgress = false;
            adapterHorizontal.remove();
            adapterHorizontal.addAll(movieResponse.getResults());
        }
    }

    @Override
    public void onMoviesRetreivalFailure(Throwable throwable) {
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
    public void onGenresRetreivalSuccess(GenreResponse genreResponse) {
        for (Genre genre : genreResponse.getGenres()) {
            mapGenre.put(genre.getId(), genre.getName());
            Log.e(TAG, "genre: id " + genre.getId() + ", name " + genre.getName());
        }
        moviesPresenterImpl.fetchMovies(movieType, String.valueOf(pageNumber));
    }

    @Override
    public void onGenresRetreivalFailure(Throwable throwable) {
        Log.e(TAG, "onGenresRetreivalFailure: ");
        moviesPresenterImpl.fetchMovies(movieType, String.valueOf(pageNumber));
    }

    @Override
    public void itemClicked(View view, int position, Movie movieData) {
        FlowManager.moveToDetailsActivity(getContext(), DetailContentType.MOVIE, movieData);
        //if (!AppUtils.isTablet(getContext())) {
//        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
//        intent.putExtra(getString(R.string.key_detail_content), movieData);
                        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            AppBarLayout barLayout = (AppBarLayout) ((AppCompatActivity) context).findViewById(R.id.actionbar);
                            ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation((AppCompatActivity) context,
                                    Pair.create((View) thumbnail, context.getString(R.string.transition_name)),
                                    Pair.create((View) barLayout, context.getString(R.string.transition_name_action_bar)));
                            context.startActivity(intent, compat.toBundle());
                        } else*/
//        startActivity(intent);
       /*  }
         else {
             getActivity().getSupportFragmentManager().beginTransaction()
                     .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
                     .replace(R.id.movie_detail, MovieDetailFragment.getInstance(movieData))
                     .commit();
         }*/

    }

    /**
     * To get the applied filters for the filter fragment
     * @return
     */
    public ArrayMap<String, List<String>> getApplied_filters() {
        return applied_filters;
    }


    /**
     * To get the list of movies for filter fragment
     * @return
     */
    public List<Movie> getMovieData() {
        return movieDataList;
    }

//    public List<Movie> getGenreFilteredMovies(List<String> genre, List<Movie> movieDataList) {
//        List<Movie> tempList = new ArrayList<>();
//        for (Movie movie : movieDataList) {
//            for (String g : genre) {
//                if (movie.getGenre().equalsIgnoreCase(g)) {
//                    tempList.add(movie);
//                }
//            }
//
//        }
//        return tempList;
//    }

    public List<Movie> getYearFilteredMovies(List<String> yearstr, List<Movie> movieDataList) {
        List<Movie> tempList = new ArrayList<>();
        for (Movie movie : movieDataList) {
            for (String y : yearstr) {
                if (DateConvert.getYear(movie.getRelease_date()).equalsIgnoreCase(y)) {
                    tempList.add(movie);
                }
            }
        }
        return tempList;
    }

//    public List<Movie> getQualityFilteredMovies(List<String> quality, List<Movie> movieDataList) {
//        List<Movie> tempList = new ArrayList<>();
//        for (Movie movie : movieDataList) {
//            for (String q : quality) {
//                if (movie.getQuality().equalsIgnoreCase(q)) {
//                    tempList.add(movie);
//                }
//            }
//        }
//        return tempList;
//    }

    public List<Movie> getRatingFilteredMovies(List<String> rating, List<Movie> movieDataList) {
        List<Movie> tempList = new ArrayList<>();
        for (Movie movie : movieDataList) {
            for (String r : rating) {
                if (movie.getVote_average() >= Float.parseFloat(r.replace(">",""))) {
                    tempList.add(movie);
                }
            }
        }
        return tempList;
    }

//    public List<String> getUniqueGenreKeys() {
//        List<String> genres = new ArrayList<>();
//        for (Movie movie : movieDataList) {
//            if (!genres.contains(movie.getGenre())) {
//                genres.add(movie.getGenre());
//            }
//        }
//        Collections.sort(genres);
//        return genres;
//    }

    public List<String> getUniqueYearKeys() {
        List<String> years = new ArrayList<>();
        for (Movie movie : movieDataList) {
            if (!years.contains(DateConvert.getYear(movie.getRelease_date()) + "")) {
                years.add(DateConvert.getYear(movie.getRelease_date()) + "");
            }
        }
        Collections.sort(years);
        return years;
    }

//    public List<String> getUniqueQualityKeys() {
//        List<String> qualities = new ArrayList<>();
//        for (Movie movie : movieDataList) {
//            if (!qualities.contains(movie.getQuality())) {
//                qualities.add(movie.getQuality());
//            }
//        }
//        Collections.sort(qualities);
//        return qualities;
//    }

    public List<String> getUniqueRatingKeys() {
        List<String> ratings = new ArrayList<>();
        for (Movie movie : movieDataList) {
            int rating = (int) Math.floor(movie.getVote_average());
            String rate = "> " + rating;
            if (!ratings.contains(rate)) {
                ratings.add(rate);
            }
        }
        Collections.sort(ratings);
        return ratings;
    }

}

