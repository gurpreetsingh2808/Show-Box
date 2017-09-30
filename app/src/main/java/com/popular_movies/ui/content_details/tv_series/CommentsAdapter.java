package com.popular_movies.ui.content_details.tv_series;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.popular_movies.R;
import com.popular_movies.domain.movie.Review;
import com.popular_movies.domain.tv.seasons.CommentsResponse;
import com.popular_movies.framework.util.animation.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gurpreet on 4/9/2016.
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {

    private List<CommentsResponse> commentDataArrayList = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private static final String TAG = CommentsAdapter.class.getSimpleName();
    private NavigateReviewListener mListener;
    private ExpandableTextView expandableTextView = new ExpandableTextView();

    //Todo : Remove reviews adapter once review activity is eliminated
    //  remove this once
    public CommentsAdapter(Context context, List<CommentsResponse> reviewList) {
        if(context != null) {
            this.context = context;
            this.commentDataArrayList = reviewList;
            inflater = LayoutInflater.from(context);
        }
    }

    public CommentsAdapter(Context context, List<CommentsResponse> reviewList, NavigateReviewListener mListener) {
        if(context != null) {
            this.context = context;
            this.commentDataArrayList = reviewList;
            inflater = LayoutInflater.from(context);
            this.mListener = mListener;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_review, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CommentsResponse current = commentDataArrayList.get(position);
//        holder.user.setText("-"+current.getUser().getName());
        holder.review.setText(current.getComment());
//        expandableTextView.collapseTextView(holder.review, 10);
    }

    @Override
    public int getItemCount() {
        return commentDataArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvUsername)
        TextView user;
        @BindView(R.id.tvReview)
        TextView review;
        @BindView(R.id.ivNext)
        ImageView ivNext;
        @BindView(R.id.ivPrevious)
        ImageView ivPrevious;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ivNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onNextClick(getAdapterPosition());
                    }
                }
            });
            ivPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onPreviousClick(getAdapterPosition());
                    }
                }
            });
            review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    expandableTextView.cycleTextViewExpansion(review);
                }
            });
        }

    }

    /**
     * interface to navigate reviews on click
     */
    public interface NavigateReviewListener {
        void onNextClick(int pos);
        void onPreviousClick(int pos);
    }
}
