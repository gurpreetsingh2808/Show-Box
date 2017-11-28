package com.popular_movies.ui.filter;

import com.popular_movies.domain.common.Genre;

/**
 * Created by Gurpreet on 30-04-2017.
 */

public interface GenreItemCheckedChangeListener {
    void onCheckedChanged(Boolean isChecked, Genre genre);
}
