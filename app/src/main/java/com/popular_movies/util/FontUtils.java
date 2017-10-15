package com.popular_movies.util;

import android.content.Context;
import android.graphics.Typeface;


/**
 * Created by Gurpreet on 10/19/2016.
 */

public class FontUtils {

    public static Typeface getHeadingTypeFace(Context context) {
            return Typeface.createFromAsset(context.getAssets(), ApplicationConstants.HEADING_FONT);
    }

    public static Typeface getBodyTypeFace(Context context) {
        return Typeface.createFromAsset(context.getAssets(), ApplicationConstants.BODY_FONT);
    }

}
