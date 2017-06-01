package com.popular_movies.ui.content_details.movie;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.popular_movies.R;
import com.popular_movies.domain.Movie;
import com.popular_movies.domain.Trailer;
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.ui.MovieItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gurpreet on 08-04-2017.
 */

public class MovieCollectionAdapter extends RecyclerView.Adapter<MovieCollectionAdapter.ViewHolder> {

    private List<Movie> data;
    private MovieItemClickListener clickListener;


    public MovieCollectionAdapter(List<Movie> data, MovieItemClickListener clickListener) {
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
                        clickListener.itemClicked(view, getPosition(), data.get(getAdapterPosition()));
                    }
                }
            });
        }

        private void setData(Movie movie) {
            ImageLoader.loadPosterImage(itemView.getContext(), movie.getPoster_path(), ivCollectionImage, 3);
            tvCollectionTitle.setText(movie.getOriginal_title());
        }
    }

}
