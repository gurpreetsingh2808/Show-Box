package com.popular_movies.ui.content_details.tv_series;

import com.popular_movies.domain.ReviewResponse;
import com.popular_movies.domain.TrailerResponse;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class TvShowDetailPresenter {

    public interface View {
        void onTrailersRetreivalSuccess(TrailerResponse trailerResponse);
        void onTrailersRetreivalFailure(Throwable throwable);

    }

    interface Presenter {
        void fetchTrailers(int movieId);
    }
}
