package com.popular_movies.ui.content_details.tv_series;

import com.popular_movies.domain.common.CreditsResponse;
import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.TvShowDetails;
import com.popular_movies.domain.tv.TvShowExternalIds;
import com.popular_movies.domain.tv.seasons.CommentsResponse;

import java.util.List;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class TvShowDetailPresenter {

    public interface View {
        void onTrailersRetreivalSuccess(TrailerResponse trailerResponse);
        void onTrailersRetreivalFailure(Throwable throwable);

        void onTvShowDetailsRetreivalSuccess(TvShowDetails movieDetails);
        void onTvShowDetailsRetreivalFailure(Throwable throwable);

        void onCreditsRetreivalSuccess(CreditsResponse creditsResponse);
        void onCreditsRetreivalFailure(Throwable throwable);

        void onTvShowExternalIdsRetreivalSuccess(TvShowExternalIds tvShowExternalIds);
        void onTvShowExternalIdsRetreivalFailure(Throwable throwable);

        void onCommentsRetreivalSuccess(List<CommentsResponse> commentsResponse);
        void onCommentsRetreivalFailure(Throwable throwable);

    }

    interface Presenter {
        void fetchTvShowDetails(int id);
        void fetchTrailers(int id);
        void fetchTvShowCredits(int movieId);
        void fetchTvShowExternalIds(int movieId);
        void fetchComments(String name);
    }
}
