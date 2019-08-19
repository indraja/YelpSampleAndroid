package com.sample.yelp.fair.ui;

import android.databinding.*;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;
import com.sample.yelp.fair.MainActivity;
import com.sample.yelp.fair.R;
import com.sample.yelp.fair.entity.Business;
import com.sample.yelp.fair.entity.OpenHours;
import com.sample.yelp.fair.network.RetrofitInterface;
import com.sample.yelp.fair.utils.ApiConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Calendar;
import java.util.List;

public class MainViewModel extends BaseObservable {
    public ObservableBoolean showProgess = new ObservableBoolean();
    public ObservableField name = new ObservableField();
    public ObservableFloat rating = new ObservableFloat();
    public ObservableInt totalReviews = new ObservableInt();
    public ObservableField addressLine1 = new ObservableField();
    public ObservableField addressLine2 = new ObservableField();
    public ObservableField phone = new ObservableField();
    public ObservableField website = new ObservableField();
    public ObservableField todayTiming = new ObservableField();

    private RetrofitInterface apiClient;

    private MainActivity mainActivity;

    public MainViewModel(MainActivity mainActivity, RetrofitInterface apiClient) {
        this.apiClient = apiClient;
        this.mainActivity = mainActivity;
    }

    public void fetchBusinessess() {
        showProgess.set(true);

        apiClient.getBusiness(ApiConstants.BUSINESS_ID_GETTY_CENTER).enqueue(new Callback<Business>() {
            @Override
            public void onResponse(Call<Business> call, Response<Business> response) {
                showProgess.set(false);

                if (response != null && response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        populateGettyCenterDetails(response.body());
                    }
                } else {
                    onFailure(call, null);
                }
            }

            @Override
            public void onFailure(Call<Business> call, Throwable t) {
                Toast.makeText(mainActivity, mainActivity.getString(R.string.api_error), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateGettyCenterDetails(@NonNull Business business) {
        name.set(business.getName());
        rating.set(business.getRating());
        totalReviews.set(business.getReviewCount());
        addressLine1.set(business.getLocation().getDisplayAddress().get(0));
        addressLine2.set(business.getLocation().getDisplayAddress().get(1));
        phone.set(business.getDisplayPhone());
        website.set("No Website found");

        setDate(business.getHours());

        mainActivity.setBusinessCenterPhotos(business.getPhotos());
    }

    private void setDate(List<OpenHours> hours) {
        if (hours != null && !hours.isEmpty()) {
            Calendar c = Calendar.getInstance();
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            List<OpenHours.OpenHour> openHours = hours.get(0).getOpenHour();
            if (openHours != null && !openHours.isEmpty()) {
                mainActivity.setBusinessHours(openHours);

                for (OpenHours.OpenHour openHour : openHours) {
                    if (openHour.getDay() == dayOfWeek) {
                        todayTiming.set(openHour.getStart().substring(0, 2) + ":" + openHour.getStart().substring(2)
                                + " to " + openHour.getEnd().substring(0, 2) + ":" + openHour.getEnd().substring(2));
                    }
                }
            }
        }
    }

}


