package com.popular_movies.domain.filter;


import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <code>Filter</code> represents the filter object for narrowing down and bringing more relevant
 * results to the user on the app
 */

@Getter
@Setter
@AllArgsConstructor
public class Filter {

    private List<String> genreIds = new ArrayList<>();
    private String voteCount = "0";
    private String startYear = "1900";
    private String endYear = "-1";
    private String sortCriteria;

}
