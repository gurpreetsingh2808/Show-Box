package com.popular_movies.util;

import java.util.Locale;

/**
 * <code>ApplicationConstants</code> this class contains the base constants which will be used across the application.
 *
 * @author Gurpreet Singh
 * @since 09 Oct, 2017
 */
public final class ApplicationConstants {

    // Private constructor so that this class cannot be initiated as this is a singleton
    private ApplicationConstants() { }

    // The default encoding of the app
    public static final String DEFAULT_ENCODING = "UTF-8";

    // The default Locale of the app
    public static final String DEFAULT_LOCALE_KEY = Locale.getDefault().toString();

    // The name of the application platform
    public static final String APP_PLATFORM_NAME = "android";

    // The type of device
    public static  final String DEVICE_TYPE = "mobile" ;

    // The heading font
    public static final String HEADING_FONT = "fonts/Brandon_Bold.otf";

    // The heading font (lighter version)
//    public static final String HEADING_FONT_LIGHT = "fonts/Moon-Light.otf";

    // The body font
    public static final String BODY_FONT ="fonts/Brandon-Regular.otf" ;
}
