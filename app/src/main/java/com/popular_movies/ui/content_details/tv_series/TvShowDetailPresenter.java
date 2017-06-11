package com.popular_movies.ui.content_details.tv_series;

import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.TvShowDetails;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class TvShowDetailPresenter {

    public interface View {
        void onTrailersRetreivalSuccess(TrailerResponse trailerResponse);
        void onTrailersRetreivalFailure(Throwable throwable);

        void onTvShowDetailsRetreivalSuccess(TvShowDetails movieDetails);
        void onTvShowDetailsRetreivalFailure(Throwable throwable);

    }

    interface Presenter {
        void fetchTvShowDetails(int id);
        void fetchTrailers(int id);
    }
}
