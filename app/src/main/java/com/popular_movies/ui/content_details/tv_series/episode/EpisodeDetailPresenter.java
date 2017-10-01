package com.popular_movies.ui.content_details.tv_series.episode;

import com.popular_movies.domain.common.TrailerResponse;
import com.popular_movies.domain.tv.ExternalIds;
import com.popular_movies.domain.tv.seasons.CommentsResponse;

import java.util.List;

/**
 * Created by Gurpreet on 25-09-2017.
 */

public class EpisodeDetailPresenter {

    public interface View {
        void onTrailersRetreivalSuccess(TrailerResponse trailerResponse);
        void onTrailersRetreivalFailure(Throwable throwable);

//        void onEpisodeDetailsRetreivalSuccess(Episode episode);
//        void onEpisodeDetailsRetreivalFailure(Throwable throwable);

        void onEpisodeExternalIdsRetreivalSuccess(ExternalIds tvShowExternalIds);
        void onEpisodeExternalIdsRetreivalFailure(Throwable throwable);


        void onCommentsRetreivalSuccess(List<CommentsResponse> commentsResponse);
        void onCommentsRetreivalFailure(Throwable throwable);

    }


    interface Presenter {
        //        void fetchEpisodeDetails(int id, int seasonNumber);
        void fetchTrailers(int id, int seasonNumber, int episodeNumber);
        void fetchEpisodeExternalIds(int movieId, int seasonNumber, int episodeNumber);
        void fetchComments(String name, int seasonNumber, int episodeNumber);
    }
}
