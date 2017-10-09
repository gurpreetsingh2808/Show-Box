package com.popular_movies.ui.listing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.popular_movies.R;
import com.popular_movies.ui.listing.movies_listing.MovieListFragment;
import com.popular_movies.ui.listing.search_listing.SearchListingFragment;
import com.popular_movies.ui.listing.tv_series_listing.TvSeriesListingFragment;
import com.popular_movies.ui.listing.tv_series_listing.episodes_listing.EpisodeListingFragment;
import com.popular_movies.ui.movie.MainActivity;
import com.popular_movies.util.AppUtils;
import com.popular_movies.util.constants.IntentKeys;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ListingActivity extends AppCompatActivity implements AAH_FabulousFragment.Callbacks, AAH_FabulousFragment.AnimationListener {

    //  toolbar
    @BindView(R.id.MovieListing_ToolBar)
    Toolbar toolbar;

    @BindView(R.id.tvToolbarTitleMoviesListing)
    TextView tvToolbarTitle;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.initializeCalligraphy();
        setContentView(R.layout.activity_movies_listing);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        if (savedInstanceState == null) {
            //  setting title
            if (getIntent() != null && getIntent().hasExtra(getString(R.string.key_title))) {
                tvToolbarTitle.setText(getIntent().getStringExtra(getString(R.string.key_title)));

                String type = getIntent().getExtras().getString(getString(R.string.key_listing_content_type));
                if (type != null) {
                    Log.e(ListingActivity.class.getSimpleName(), "onCreate: type"+type );
                    switch (type) {
                        case ListingContentType.MOVIE:
                            Fragment fragment = MovieListFragment.getInstance(getIntent()
                                    .getStringExtra(getString(R.string.key_listing_sub_content_type)));
                            setFragment(fragment);
                            break;
                        case ListingContentType.TV_SERIES:
                            fragment = TvSeriesListingFragment.getInstance(getIntent()
                                    .getStringExtra(getString(R.string.key_listing_sub_content_type)));
                            setFragment(fragment);
                            break;
                        case ListingContentType.EPISODES:
                            fragment = EpisodeListingFragment.getInstance(getIntent()
                                            .getStringExtra(getString(R.string.key_listing_sub_content_type)),
                                            getIntent().getExtras().getInt(IntentKeys.KEY_TV_ID, -1),
                                            getIntent().getIntExtra(IntentKeys.KEY_SEASON_NUMBER, -1));
                            setFragment(fragment);
                            break;
                        case ListingContentType.SEARCH:
                            fragment = SearchListingFragment.getInstance(getIntent()
                                    .getStringExtra(getString(R.string.key_listing_sub_content_type)));
                            setFragment(fragment);
                            break;
                    }
                }
            }

        }
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movies_listing, fragment, getString(R.string.tag))
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (this.isTaskRoot()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.ivBack)
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void onOpenAnimationStart() {

    }

    @Override
    public void onOpenAnimationEnd() {

    }

    @Override
    public void onCloseAnimationStart() {

    }

    @Override
    public void onCloseAnimationEnd() {

    }

    @Override
    public void onResult(Object result) {

    }
}
