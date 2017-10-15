package com.popular_movies.ui.listing.search_listing;

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
import com.popular_movies.domain.search.Search;
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.util.DateConvert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gurpreet on 1/17/2016.
 */
public class SearchListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static String TAG = SearchListingAdapter.class.getSimpleName();
    private List<Search> searchItemArrayList;
    private LayoutInflater inflater;
    private Context context;
    private SearchItemClickListener clickListener;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private Map<Integer, String> mapGenre = new HashMap<>();

    public SearchListingAdapter(Context context, List<Search> searchItemArrayList) {
        if (context != null) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.searchItemArrayList = searchItemArrayList;
            /*if (AppUtils.isTablet(context)) {
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail, MovieDetailFragment.getInstance(searchItemArrayList.get(0)))
                        .commit();
            }*/
        } else {
            Log.e(TAG, "SearchAdapterHorizontal: context is null");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return searchItemArrayList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = inflater.inflate(R.layout.item_movie_listing, parent, false);
            return new SearchViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = inflater.inflate(R.layout.item_pagination_loader, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchViewHolder) {
            final Search searchData = searchItemArrayList.get(position);
            SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
            searchViewHolder.setData(searchData);

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            //loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return searchItemArrayList == null ? 0 : searchItemArrayList.size();
    }

    public void setGenre(Map<Integer, String> mapGenre) {
        this.mapGenre = mapGenre;
    }

    public void addAll(List<Search> results) {
        searchItemArrayList.addAll(results);
        //  TODO: try to use Notify item range changed
        notifyDataSetChanged();
    }

    public void add(Search movie) {
        searchItemArrayList.add(movie);
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                notifyItemInserted(searchItemArrayList.size() - 1);
            }
        };

        handler.post(r);
    }

    public void remove() {
        searchItemArrayList.remove(searchItemArrayList.size() - 1);
        notifyItemRemoved(searchItemArrayList.size());
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        //public SearchAdapterHorizontal.ClickListener clickListener;

        SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getAdapterPosition(), searchItemArrayList.get(getAdapterPosition()));
            }
        }

        private void setData(final Search searchData) {
            switch (searchData.getMedia_type()) {
                case SearchResultContentType.MOVIE:
                    setSearchData(searchData.getTitle(), searchData.getRelease_date(), searchData.getVote_average(),
                            searchData.getGenre_ids());

                    break;
                case SearchResultContentType.TV_SERIES:
                    setSearchData(searchData.getOriginal_name(), searchData.getFirst_air_date(), searchData.getVote_average(),
                            searchData.getGenre_ids());
                    break;
            }
            //  set title
            //  set poster/movie image
            ImageLoader.loadBackdropImage(context, searchData.getBackdrop_path(), thumbnail, 3);

        }

        private void setSearchData(String name, String release_date, Float vote_average, List<Integer> genre_ids) {
            title.setText(name);
            tvReleaseYear.setText(DateConvert.convert(release_date));
            tvRating.setText(vote_average.toString());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < genre_ids.size(); i++) {
                for (Map.Entry<Integer, String> genre : mapGenre.entrySet()) {
                    Log.d(TAG, "genre " + genre.getValue());
                    if (genre_ids.get(i).intValue() == genre.getKey()) {
                        Log.d(TAG, "setData: genre matched");
                        sb.append(genre.getValue());
                        break;
                    }
                }
                if (i != genre_ids.size() - 1) {
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

    public void setClickListener(SearchItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

}