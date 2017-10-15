package com.popular_movies.ui.favourites;

import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popular_movies.R;
import com.popular_movies.domain.movie.MovieTable;
import com.popular_movies.domain.tv.TvShowsTable;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoritesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private final Integer MOVIES_LOADER_ID = 0;
    private final Integer TV_SHOWS_LOADER_ID = 1;

    //  recycler view
    @BindView(R.id.rvFavoriteMovies)
    RecyclerView rvFavoriteMovies;
    @BindView(R.id.rvFavoriteTvShows)
    RecyclerView rvFavoriteTvShows;

    private FavouriteMoviesAdapter favouriteMoviesAdapter;
    private FavouriteTvShowsAdapter favouriteTvShowsAdapter;
    private final String TAG = FavoritesFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.favorites_list, container, false);
        ButterKnife.bind(this, layout);

        if (getResources().getBoolean(R.bool.isTablet)) {
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int width = (int) (metrics.widthPixels / metrics.density);
            //For Tabs
            ///////////////////////////boolean isTablet = getResources().getBoolean(R.bool.isTablet);
            ///////////////////////////width = isTablet ? (width / 2) : width;
            rvFavoriteMovies.setLayoutManager(new GridLayoutManager(getContext(), width / 140));
        } else {
            rvFavoriteMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            rvFavoriteTvShows.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
        favouriteMoviesAdapter = new FavouriteMoviesAdapter(getContext(), null);
        favouriteTvShowsAdapter = new FavouriteTvShowsAdapter(getContext(), null);
        rvFavoriteMovies.setAdapter(favouriteMoviesAdapter);
        rvFavoriteTvShows.setAdapter(favouriteTvShowsAdapter);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Prepare the loader.  Either re-connect with an existing one, or start a new one.
        getLoaderManager().initLoader(MOVIES_LOADER_ID, null, this);
        getLoaderManager().initLoader(TV_SHOWS_LOADER_ID, null, this);

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.e(TAG, "onCreateLoader: ID "+id);
        Log.e(TAG, "onCreateLoader: ARGS "+args);
        Uri CONTENT_URI = null;
        if(id == MOVIES_LOADER_ID) {
            CONTENT_URI = MovieTable.CONTENT_URI;
        }
        else if(id == TV_SHOWS_LOADER_ID) {
            CONTENT_URI = TvShowsTable.CONTENT_URI;
        }
        return new CursorLoader(getContext(), CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.e(TAG, "onLoadFinished: LOADER ID "+loader.getId());
        if(loader.getId() == MOVIES_LOADER_ID) {
            favouriteMoviesAdapter.swapCursor(cursor);
        }
        else if(loader.getId() == TV_SHOWS_LOADER_ID) {
            favouriteTvShowsAdapter.swapCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        if(loader.getId() == MOVIES_LOADER_ID) {
            favouriteMoviesAdapter.swapCursor(null);
        }
        else if(loader.getId() == TV_SHOWS_LOADER_ID) {
            favouriteTvShowsAdapter.swapCursor(null);
        }
    }

}
