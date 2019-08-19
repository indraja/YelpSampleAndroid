package com.sample.yelp.fair.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sample.yelp.fair.R;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {

    private Context context;
    private List<String> photos;

    public PhotosAdapter(Context context, List<String> openHours) {
        this.context = context;
        this.photos = openHours;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photos, parent, false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        String url = photos.get(position);

        RequestOptions myOptions = new RequestOptions()
                .override(200, 200).centerCrop();

        Glide.with(context)
                .asBitmap()
                .apply(myOptions)
                .load("http"+url.substring(5)) // some problem in loading images with glide
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class PhotosViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPhoto;

        public PhotosViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
        }

    }

}
