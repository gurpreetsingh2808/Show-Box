package com.popular_movies.util;

/**
 * Created by Gurpreet on 31-05-2017.
 */

public class TimeUtils {

    public static String formatDuration(int duration) {
        int hours = duration / 60; //since both are ints, you get an int
        int minutes = duration % 60;
        if(hours < 1) {
            return minutes + " mins ";
        }
        else if(hours == 1) {
            return hours + " hr " + minutes + " mins ";
        }
        else {
            return hours + " hrs " + minutes + " mins ";
        }
    }
}
