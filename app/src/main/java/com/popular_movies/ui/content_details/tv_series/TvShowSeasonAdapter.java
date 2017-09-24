package com.popular_movies.ui.content_details.tv_series;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.popular_movies.R;
import com.popular_movies.domain.movie.Movie;
import com.popular_movies.domain.tv.Season;
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.ui.MovieItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gurpreet on 08-04-2017.
 */

public class TvShowSeasonAdapter extends RecyclerView.Adapter<TvShowSeasonAdapter.ViewHolder> {

    private List<Season> data;
    private TvShowSeasonClickListener clickListener;


    public TvShowSeasonAdapter(List<Season> data, TvShowSeasonClickListener clickListener) {
        this.data = data;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_collection, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivCollectionImage)
        ImageView ivCollectionImage;
        @BindView(R.id.tvCollectionTitle)
        TextView tvCollectionTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ivCollectionImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        String title = data.get(getAdapterPosition()).getName() + " : " +data.get(getAdapterPosition()).getSeason_number();
                        clickListener.seasonClicked(view, getPosition(),  title);
                    }
                }
            });
        }

        private void setData(Season season) {
            ImageLoader.loadPosterImage(itemView.getContext(), season.getPoster_path(), ivCollectionImage, 3);
            tvCollectionTitle.setText("Season " +season.getSeason_number());
        }
    }

}
