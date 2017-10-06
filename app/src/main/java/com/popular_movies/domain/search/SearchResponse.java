package com.popular_movies.domain.search;

import com.popular_movies.domain.movie.Dates;
import com.popular_movies.domain.movie.Movie;

import java.util.List;

/**
 * Created by Gurpreet on 04-10-2017.
 */

public class SearchResponse {

    private Integer page;
    private List<Search> results;
    private Integer total_results;
    private Integer total_pages;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public List<Search> getResults() {
        return results;
    }

    public void setResults(List<Search> results) {
        this.results = results;
    }
}
