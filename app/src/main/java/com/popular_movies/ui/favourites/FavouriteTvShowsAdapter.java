package com.popular_movies.ui.favourites;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.popular_movies.R;
import com.popular_movies.domain.movie.Movie;
import com.popular_movies.domain.tv.TvShow;
import com.popular_movies.domain.tv.TvShowsTable;
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.ui.FlowManager;
import com.popular_movies.ui.content_details.DetailContentType;
import com.popular_movies.ui.content_details.movie.MovieDetailFragment;
import com.popular_movies.util.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gurpreet on 1/17/2016.
 */
public class FavouriteTvShowsAdapter extends RecyclerView.Adapter<FavouriteTvShowsAdapter.MyViewHolder> {

    private static String TAG = FavouriteTvShowsAdapter.class.getSimpleName();
    private List<TvShow> tvShowArrayList;
    private LayoutInflater inflater;
    private Context context;
    private Cursor dataCursor;
    public static ClickListener clickListener;


    public FavouriteTvShowsAdapter(Context context, Cursor cursor) {
        dataCursor = cursor;
        this.context = context;
        inflater = LayoutInflater.from(context);
       /* if (MainActivity.mIsDualPane) {
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail, DetailedViewFragment.getInstance(movieItemArrayList.get(0)))
                    .commit();
        }*/
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie_item_horizontal, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        dataCursor.moveToPosition(position);
        String title = dataCursor.getString(dataCursor.getColumnIndex(TvShowsTable.FIELD_COL_ORIGINAL_NAME));
        String description = dataCursor.getString(dataCursor.getColumnIndex(TvShowsTable.FIELD_COL_OVERVIEW));
        String posterPath = dataCursor.getString(dataCursor.getColumnIndex(TvShowsTable.FIELD_COL_POSTER_PATH));
        String backdrop = dataCursor.getString(dataCursor.getColumnIndex(TvShowsTable.FIELD_COL_BACKDROP));
        Float voteAverage = dataCursor.getFloat(dataCursor.getColumnIndex(TvShowsTable.FIELD_COL_VOTE_AVERAGE));
        String releaseDate = dataCursor.getString(dataCursor.getColumnIndex(TvShowsTable.FIELD_COL_FIRST_AIR_DATE));
        Log.d(TAG, "onBindViewHolder: release date " + releaseDate);
        String id = dataCursor.getString(dataCursor.getColumnIndex(TvShowsTable.FIELD_COL_ID));

        holder.title.setText(title);
        ImageLoader.loadPosterImage(context, posterPath, holder.thumbnail);

        final TvShow tvShow = new TvShow(title, description, posterPath, backdrop, voteAverage, releaseDate, Integer.valueOf(id));

        if (AppUtils.isTablet(context) && AppUtils.isLandscape(context)) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
                            .replace(R.id.movie_detail, MovieDetailFragment.getInstance(tvShow))
                            .commit();
                }
            });
        } else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FlowManager.moveToDetailsActivity(context, DetailContentType.TV_SERIES, tvShow);

                }
            });
        }
    }

    public Cursor swapCursor(Cursor cursor) {
        if (dataCursor == cursor) {
            return null;
        }
        Cursor oldCursor = dataCursor;
        this.dataCursor = cursor;
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return oldCursor;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_title)
        TextView title;
        @BindView(R.id.movie_thumbnail)
        ImageView thumbnail;
        //public MovieAdapterHorizontal.ClickListener clickListener;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }
}
