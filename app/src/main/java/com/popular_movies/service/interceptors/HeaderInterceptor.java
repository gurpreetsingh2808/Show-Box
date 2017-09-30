package com.popular_movies.service.interceptors;


import com.popular_movies.util.HeaderUtil;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * <code>HeaderInterceptor</code> adds the various headers to the outgoing request
 * to allow for the backend to be able to identify the client
 */
public class HeaderInterceptor implements okhttp3.Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        HeaderUtil headerUtil = new HeaderUtil();

        Request.Builder rBuilder = original.newBuilder()
                .header(HeaderUtil.TRAKT_API_VERSION, headerUtil.getTraktApiVersion())
                .header(HeaderUtil.TRAKT_API_KEY, headerUtil.getTraktApiKey());

        rBuilder.method(original.method(), original.body());
        Request request = rBuilder.build();

        return chain.proceed(request);
    }
}
