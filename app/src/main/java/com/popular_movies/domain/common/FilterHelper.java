package com.popular_movies.domain.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Gurpreet on 09-11-2017.
 */

@Getter
@Setter
@AllArgsConstructor
public class FilterHelper {

    private String voteAverage;
    private List<String> listGenre;
    private String startingReleaseDate;
    private String endingReleaseDate;
    private String sortCriteria;

}
