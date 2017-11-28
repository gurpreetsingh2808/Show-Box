package com.popular_movies.ui.filter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.popular_movies.R;
import com.popular_movies.domain.common.Genre;
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.util.DateConvert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by Gurpreet on 1/17/2016.
 */
public class GenreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static String TAG = GenreAdapter.class.getSimpleName();
    private List<Genre> tvGenreArrayList;
    private LayoutInflater inflater;
    private Context context;
    private GenreItemCheckedChangeListener checkedChangeListener;
    private Map<Integer, String> mapGenre = new HashMap<>();

    public GenreAdapter(Context context, List<Genre> tvGenreArrayList) {
        if (context != null) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.tvGenreArrayList = tvGenreArrayList;
            
        } else {
            Log.e(TAG, "GenreAdapterHorizontal: context is null");
        }
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_filter_genre, parent, false);
            return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            final Genre tvShowData = tvGenreArrayList.get(position);
            GenreViewHolder movieViewHolder = (GenreViewHolder) holder;
            movieViewHolder.setData(tvShowData);
    }

    @Override
    public int getItemCount() {
        return tvGenreArrayList == null ? 0 : tvGenreArrayList.size();
    }

    public void setGenre(Map<Integer, String> mapGenre) {
        this.mapGenre = mapGenre;
    }
    

    class GenreViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.cbGenre)
        CheckBox checkBoxGenre;
        //public GenreAdapterHorizontal.ClickListener clickListener;

        GenreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            checkBoxGenre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (checkedChangeListener != null) {
                        checkedChangeListener.onCheckedChanged(isChecked, tvGenreArrayList.get(getAdapterPosition()));
                    }
                }
            });
        }

        private void setData(final Genre genre) {
            //  set title
            checkBoxGenre.setText(genre.getName());
        }
    }
    
    public void setCheckedChangeListener(GenreItemCheckedChangeListener clickListener) {
        this.checkedChangeListener = checkedChangeListener;
    }


}