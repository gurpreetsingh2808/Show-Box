package com.popular_movies.ui.listing.tv_series_listing;

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
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.ui.TvShowItemClickListener;
import com.popular_movies.util.DateConvert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gurpreet on 1/17/2016.
 */
public class TvSeriesListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static String TAG = TvSeriesListingAdapter.class.getSimpleName();
    private List<TvShow> tvShowItemArrayList;
    private LayoutInflater inflater;
    private Context context;
    private TvShowItemClickListener clickListener;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private Map<Integer, String> mapGenre = new HashMap<>();

    public TvSeriesListingAdapter(Context context, List<TvShow> tvShowDataList) {
        if (context != null) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.tvShowItemArrayList = tvShowDataList;
            /*if (AppUtils.isTablet(context)) {
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail, MovieDetailFragment.getInstance(tvShowItemArrayList.get(0)))
                        .commit();
            }*/
        } else {
            Log.e(TAG, "TvShowAdapterHorizontal: context is null");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return tvShowItemArrayList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = inflater.inflate(R.layout.item_movie_listing, parent, false);
            return new TvShowViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = inflater.inflate(R.layout.item_pagination_loader, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TvShowViewHolder) {
            final TvShow tvShowData = tvShowItemArrayList.get(position);
            TvShowViewHolder movieViewHolder = (TvShowViewHolder) holder;
            movieViewHolder.setData(tvShowData);

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            //loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return tvShowItemArrayList == null ? 0 : tvShowItemArrayList.size();
    }

    public void setGenre(Map<Integer,String> mapGenre) {
        this.mapGenre = mapGenre;
    }

    public void addAll(List<TvShow> results) {
        tvShowItemArrayList.addAll(results);
        notifyDataSetChanged();
    }

    public void add(TvShow movie) {
        tvShowItemArrayList.add(movie);
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                notifyItemInserted(tvShowItemArrayList.size() - 1);
            }
        };

        handler.post(r);
    }

    public void remove() {
        tvShowItemArrayList.remove(tvShowItemArrayList.size() - 1);
        notifyItemRemoved(tvShowItemArrayList.size());
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        //public TvShowAdapterHorizontal.ClickListener clickListener;

        TvShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getAdapterPosition(), tvShowItemArrayList.get(getAdapterPosition()));
            }
        }

        private void setData(final TvShow tvShowData) {
            //  set title
            title.setText(tvShowData.getOriginal_name());
            //  set poster/movie image
//            ImageLoader.loadPosterImage(context, tvShowData.getPoster_path(), thumbnail);
            ImageLoader.loadBackdropImage(context, tvShowData.getBackdrop_path(), thumbnail, 3);

            //  set rating
            tvRating.setText(tvShowData.getVote_average());
            //  set release date
            tvReleaseYear.setText(DateConvert.convert(tvShowData.getFirst_air_date()));

            for (int i = 0; i < tvShowData.getGenre_ids().length; i++) {
                Log.d(TAG, "genre "+tvShowData.getGenre_ids()[i]);
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tvShowData.getGenre_ids().length; i++) {
                for (Map.Entry<Integer, String> genre : mapGenre.entrySet()) {
                    Log.d(TAG, "genre "+genre.getValue());
                    if (tvShowData.getGenre_ids()[i].intValue() == genre.getKey()) {
                        Log.d(TAG, "setData: genre matched");
                        sb.append(genre.getValue());
                        break;
                    }
                }
                if(i != tvShowData.getGenre_ids().length-1) {
                    sb.append(", ");
                }
            }
            tvGenre.setText(sb);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pbPagination)
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setClickListener(TvShowItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

}