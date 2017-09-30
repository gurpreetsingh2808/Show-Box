package com.popular_movies.ui.content_details.tv_series.episode;

import com.popular_movies.domain.common.TrailerResponse;

/**
 * Created by Gurpreet on 25-09-2017.
 */

public class EpisodeDetailPresenter {

    public interface View {
        void onTrailersRetreivalSuccess(TrailerResponse trailerResponse);
        void onTrailersRetreivalFailure(Throwable throwable);

//        void onEpisodeDetailsRetreivalSuccess(TvShowSeasonDetails movieDetails);
//        void onEpisodeDetailsRetreivalFailure(Throwable throwable);

//        void onCreditsRetreivalSuccess(CreditsResponse creditsResponse);
//        void onCreditsRetreivalFailure(Throwable throwable);

    }


    interface Presenter {
        //        void fetchTvShowSeasonDetails(int id, int seasonNumber);
        void fetchTrailers(int id, int seasonNumber);
//        void fetchTvShowSeasonCredits(int movieId);
    }
}
