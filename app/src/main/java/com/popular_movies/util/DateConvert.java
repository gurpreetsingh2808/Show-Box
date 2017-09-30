package com.popular_movies.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateConvert {
    public static String convert(String stringDate) {
        Date date= null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);
        return monthToName(cal.get(Calendar.MONTH)) + " " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR);
    }

    public static String getYear(Date date) {
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(Calendar.YEAR));
    }

    private static String monthToName(int id) {
        switch (id) {
            case 0:
                return "Jan";
            case 1:
                return "Feb";
            case 2:
                return "Mar";
            case 3:
                return "Apr";
            case 4:
                return "May";
            case 5:
                return "Jun";
            case 6:
                return "Jul";
            case 7:
                return "Aug";
            case 8:
                return "Sep";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dec";
            default:
                return null;

        }
    }
}
