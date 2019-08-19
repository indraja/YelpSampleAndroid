package com.sample.yelp.fair;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.sample.yelp.fair.databinding.BusinessCenterDetailsScreenBinding;
import com.sample.yelp.fair.entity.OpenHours;
import com.sample.yelp.fair.network.RestClientManager;
import com.sample.yelp.fair.network.RetrofitInterface;
import com.sample.yelp.fair.ui.HoursAdapter;
import com.sample.yelp.fair.ui.MainViewModel;
import com.sample.yelp.fair.ui.PhotosAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    BusinessCenterDetailsScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RetrofitInterface apiClient = RestClientManager.getInstance().getApiClient();

        MainViewModel viewModel = new MainViewModel(this, apiClient);

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.setContentView(this, R.layout.business_center_details_screen);
        binding.setVm(viewModel);

        binding.hoursList.setHasFixedSize(true);
        binding.hoursList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        binding.picturesList.setHasFixedSize(true);
        binding.picturesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        viewModel.fetchBusinessess();
    }

    public void setBusinessHours(List<OpenHours.OpenHour> openHours) {
        HoursAdapter hoursAdapter = new HoursAdapter(openHours);
        binding.hoursList.setAdapter(hoursAdapter);
    }

    public void setBusinessCenterPhotos(List<String> photos) {
        PhotosAdapter photosAdapter = new PhotosAdapter(this, photos);
        binding.picturesList.setAdapter(photosAdapter);
    }

}
