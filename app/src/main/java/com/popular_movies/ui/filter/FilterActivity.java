package com.popular_movies.ui.filter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.popular_movies.R;
import com.popular_movies.domain.common.Genre;
import com.popular_movies.domain.common.GenreResponse;
import com.popular_movies.domain.filter.Filter;
import com.popular_movies.domain.movie.MovieResponse;
import com.popular_movies.util.AppUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FilterActivity extends AppCompatActivity implements FilterPresenter.View, GenreItemCheckedChangeListener {

    private FilterPresenterImpl filterPresenterImpl;
    private GenreAdapter genreAdapter;
    private ArrayList<String> selectedGenreIds;

    @BindView(R.id.rvGenre)
    RecyclerView rvGenre;
    @BindView(R.id.seekbarVoteAverage)
    SeekBar seekbarVoteAverage;
    @BindView(R.id.radioGroupSort)
    RadioGroup radioGroupSort;

    @BindView(R.id.rbSortPopularity)
    RadioButton rbSortPopularity;

    private String sortString;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.initializeCalligraphy();
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.WHITE);
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
//            getWindow().setStatusBarColor(getResources().getColor(R.color.lightGrey));
            // Set the status bar to dark-semi-transparentish
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        filterPresenterImpl = new FilterPresenterImpl(this, this);
        rvGenre.setLayoutManager(new GridLayoutManager(this, 2));
        radioGroupSort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                sortString = findViewById(checkedId).getTag().toString();
                Log.e("Filter", "onCheckedChanged: " +sortString);
            }
        });
        filterPresenterImpl.fetchGenres();
    }


    @OnClick(R.id.cvFilterApply)
    void applyFilter() {
        String voteAverage = String.valueOf(seekbarVoteAverage.getProgress());
        filterPresenterImpl.getMovies(voteAverage, null, null, null, sortString, "1");
    }


    @Override
    public void onMoviesRetreivalSuccess(MovieResponse movieResponse) {

    }

    @Override
    public void onMoviesRetreivalFailure(Throwable throwable) {

    }

    @Override
    public void onGenresRetreivalSuccess(GenreResponse genreResponse) {
        genreAdapter = new GenreAdapter(this, genreResponse.getGenres());
        genreAdapter.setCheckedChangeListener(this);
        rvGenre.setAdapter(genreAdapter);
    }

    @Override
    public void onGenresRetreivalFailure(Throwable throwable) {
        Log.d("Filter", "" + throwable.fillInStackTrace());
    }

    @Override
    public void onCheckedChanged(Boolean isChecked, Genre genre) {
        if (isChecked) {
            selectedGenreIds.add(String.valueOf(genre.getId()));
        } else {
            selectedGenreIds.remove(genre.getId());
        }
    }
}
