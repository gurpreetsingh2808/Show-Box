package com.popular_movies.ui.listing.tv_series_listing.episodes_listing;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.popular_movies.R;
import com.popular_movies.domain.tv.TvShow;
import com.popular_movies.domain.tv.seasons.Episode;
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.ui.TvShowItemClickListener;
import com.popular_movies.util.DateConvert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gurpreet on 24/09/2017.
 */
public class EpisodeListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static String TAG = EpisodeListingAdapter.class.getSimpleName();
    private List<Episode> tvEpisodesArrayList;
    private LayoutInflater inflater;
    private Context context;
    private EpisodeClickListener clickListener;

    public EpisodeListingAdapter(Context context, List<Episode> tvEpisodesArrayList) {
        if (context != null) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.tvEpisodesArrayList = tvEpisodesArrayList;
        } else {
            Log.e(TAG, "EpisodeAdapterHorizontal: context is null");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_movie_listing, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Episode tvEpisodesData = tvEpisodesArrayList.get(position);
        ((EpisodeViewHolder) holder).setData(tvEpisodesData);
    }

    @Override
    public int getItemCount() {
        return tvEpisodesArrayList == null ? 0 : tvEpisodesArrayList.size();
    }

    class EpisodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_title)
        TextView title;
        @BindView(R.id.tvReleaseYear)
        TextView tvReleaseYear;
        @BindView(R.id.movie_thumbnail)
        ImageView thumbnail;
        @BindView(R.id.tvRating)
        TextView tvRating;
        @BindView(R.id.tvGenre)
        TextView tvGenre;
        //public EpisodeAdapterHorizontal.ClickListener clickListener;

        EpisodeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getAdapterPosition(), tvEpisodesArrayList.get(getAdapterPosition()));
            }
        }

        private void setData(final Episode tvEpisodesData) {
            //  set title
            title.setText(tvEpisodesData.getName());
            //  set poster/movie image
            ImageLoader.loadPosterImage(context, tvEpisodesData.getStill_path(), thumbnail, 3);

            tvGenre.setVisibility(View.GONE);
            //  set rating
            if (tvEpisodesData.getVote_average() != null)
                tvRating.setText(tvEpisodesData.getVote_average().toString());
            //  set release date
            if (tvEpisodesData.getAir_date() != null)
                tvReleaseYear.setText(DateConvert.convert(tvEpisodesData.getAir_date()));

        }
    }

    public void setClickListener(EpisodeClickListener clickListener) {
        this.clickListener = clickListener;
    }

}