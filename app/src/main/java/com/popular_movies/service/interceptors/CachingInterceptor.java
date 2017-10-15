package com.popular_movies.service.interceptors;

import android.app.Activity;

import com.popular_movies.util.ConnectivityUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gurpreet on 12/13/2016.
 */

public class CachingInterceptor implements Interceptor {

    Activity activity;

    public CachingInterceptor(Activity activity) {
        this.activity = activity;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        if (!ConnectivityUtil.isNetworkConnected(activity.getApplicationContext())) {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(1, TimeUnit.HOURS)
                    .build();

            request = request.newBuilder()
                    .removeHeader("Pragma")
                    .cacheControl(cacheControl)
                    .build();
//        }

        return chain.proceed(request);
    }

}
