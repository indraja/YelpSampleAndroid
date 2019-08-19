package com.sample.yelp.fair.network;

import com.sample.yelp.fair.entity.Business;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @GET("/v3/businesses/{businessId}")
    Call<Business> getBusiness(@Path("businessId") String businessId);
}
