package com.popular_movies.ui.listing.tv_series_listing.episodes_listing;

import com.popular_movies.domain.tv.seasons.TvShowSeasonDetails;

/**
 * Created by Gurpreet on 21-01-2017.
 */

public class EpisodePresenter {

    public interface View {
        void onEpisodesRetreivalSuccess(TvShowSeasonDetails tvShowSeasonDetails);

        void onEpisodesRetreivalFailure(Throwable throwable);
    }

    interface Presenter {
        void fetchEpisodes(int tvId, int seasonNumber);
    }
}
