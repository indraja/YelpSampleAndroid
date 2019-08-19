package com.sample.yelp.fair.network;

import com.sample.yelp.fair.utils.ApiConstants;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class NetworkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder()
                .addHeader(ApiConstants.AUTHORIZATION, "Bearer "+ApiConstants.API_KEY);

        return chain.proceed(builder.build());
    }

}
