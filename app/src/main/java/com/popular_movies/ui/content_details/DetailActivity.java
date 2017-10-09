package com.popular_movies.ui.content_details;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;

import com.popular_movies.R;
import com.popular_movies.ui.content_details.movie.MovieDetailFragment;
import com.popular_movies.ui.content_details.tv_series.TvShowDetailFragment;
import com.popular_movies.ui.content_details.tv_series.episode.EpisodeDetailFragment;
import com.popular_movies.ui.movie.MainActivity;
import com.popular_movies.util.AppUtils;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity {
    public static final String TAG = DetailActivity.class.getSimpleName();


    public void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            //getWindow().setEnterTransition(new AutoTransition());
            //getWindow().setReturnTransition(new Explode());

            getWindow().setEnterTransition(new Slide());
            getWindow().setExitTransition(new Slide());
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupWindowAnimations();
        super.onCreate(savedInstanceState);
        AppUtils.initializeCalligraphy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            //       WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.pureBlack));

        }

        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            Log.d("moviedetail", "action bar not null");
//            getSupportActionBar().setTitle(MovieDetailFragment.getInstance(getIntent().
//                    getParcelableExtra(getString(R.string.key_detail_content))).getTitle().toString());
        }

        if (savedInstanceState == null) {
            String type = getIntent().getExtras().getString(getString(R.string.key_detail_content_type));
            if (type != null) {
                switch (type) {
                    case DetailContentType.MOVIE:
                        Fragment fragment = MovieDetailFragment.getInstance(getIntent()
                                .getParcelableExtra(getString(R.string.key_detail_content)));
                        setFragment(fragment);
                        break;
                    case DetailContentType.TV_SERIES:
                        fragment = TvShowDetailFragment.getInstance(getIntent()
                                .getParcelableExtra(getString(R.string.key_detail_content)));
                        setFragment(fragment);
                        break;
                    case DetailContentType.EPISODE:
                        fragment = EpisodeDetailFragment.getInstance(getIntent()
                                .getParcelableExtra(getString(R.string.key_detail_content)));
                        setFragment(fragment);
                        break;

                }
            }

        }
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detailFragment, fragment, getString(R.string.tag))
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
