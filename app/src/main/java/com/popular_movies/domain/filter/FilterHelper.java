package com.popular_movies.domain.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * <code>FilterHelper</code> provides various utility methods on filter management
 *
 * @author Gurpreet Singh
 * @since 28-Nov-2017
 */
public class FilterHelper {

    public static Map<String, String> getFilterAsMap(Filter filter) {

        Map<String, String> filterAsMap = new HashMap<>();

        if (filter != null) {
            if (filter.getGenreIds() != null && !filter.getGenreIds().isEmpty()) {
                filterAsMap.put("with_genres", android.text.TextUtils.join(",", filter.getGenreIds()));
            }
            if (filter.getVoteCount() != null) {
                filterAsMap.put("vote_average.gte", filter.getVoteCount());
            }
            if (filter.getStartYear() != null) {
                filterAsMap.put("primary_release_date.gte", filter.getStartYear());
            }
            if (filter.getEndYear() != null) {
                filterAsMap.put("primary_release_date.lte", filter.getEndYear());
            }
            if (filter.getSortCriteria() != null) {
                filterAsMap.put("sort_by", filter.getSortCriteria());
            }
        }

        return filterAsMap;
    }
}
