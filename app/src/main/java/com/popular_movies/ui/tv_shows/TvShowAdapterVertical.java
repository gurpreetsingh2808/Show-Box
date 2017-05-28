package com.popular_movies.ui.tv_shows;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.popular_movies.R;
import com.popular_movies.domain.TvShow;
import com.popular_movies.framework.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gurpreet on 1/17/2016.
 */
public class TvShowAdapterVertical extends RecyclerView.Adapter<TvShowAdapterVertical.ViewHolder> {

    private static String TAG = TvShowAdapterVertical.class.getSimpleName();
    private List<TvShow> tvShowItemArrayList;

    private LayoutInflater inflater;
    private Context context;
    private TvShowItemClickListener clickListener;

    public TvShowAdapterVertical(Context context, List<TvShow> movieDataList) {
        if (context != null) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.tvShowItemArrayList = movieDataList;
        } else {
            Log.e(TAG, "TvShowAdapterHorizontal: context is null");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie_item_vertical, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final TvShow movieData = tvShowItemArrayList.get(position);
        holder.setData(movieData);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_title)
        TextView title;
        @BindView(R.id.movie_thumbnail)
        ImageView thumbnail;
        @BindView(R.id.movie_synopsis)
        TextView synopsis;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition(), tvShowItemArrayList.get(getAdapterPosition()));
            }
        }

        private void setData(final TvShow movieData) {
            title.setText(movieData.getOriginal_name());
            ImageLoader.loadPosterImage(context, movieData.getPoster_path(), thumbnail);
            synopsis.setText(movieData.getOverview());
        }
    }

    public void setClickListener(TvShowItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return tvShowItemArrayList.size();
    }

}