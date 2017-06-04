package com.popular_movies.ui.content_details.movie;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.popular_movies.R;
import com.popular_movies.domain.common.Cast;
import com.popular_movies.domain.movie.Movie;
import com.popular_movies.framework.ImageLoader;
import com.popular_movies.ui.MovieItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gurpreet on 08-04-2017.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private List<Cast> data;
    private CastClickListener castClickListener;


    public CastAdapter(List<Cast> data, CastClickListener castClickListener) {
        this.data = data;
        this.castClickListener = castClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_cast, parent, false);
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

        @BindView(R.id.ivCastImage)
        ImageView ivCastImage;
        @BindView(R.id.tvCastName)
        TextView tvCastName;
        @BindView(R.id.tvCharacterName)
        TextView tvCharacterName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ivCastImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (castClickListener != null) {
                        castClickListener.itemClicked(view, getPosition(), data.get(getAdapterPosition()));
                    }
                }
            });
        }

        private void setData(Cast cast) {
            ImageLoader.loadPosterImage(itemView.getContext(), cast.getProfile_path(), ivCastImage, 3);
//            tvCastName.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
            tvCastName.setText(cast.getName());
            if(!TextUtils.isEmpty(cast.getCharacter())) {
                tvCharacterName.setText(TextUtils.concat("as " +cast.getCharacter()));
            }
        }
    }

    public interface CastClickListener {
        void itemClicked(View view, int position, Cast cast);
    }
}
