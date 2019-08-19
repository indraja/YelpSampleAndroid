package com.sample.yelp.fair.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sample.yelp.fair.R;
import com.sample.yelp.fair.entity.Day;
import com.sample.yelp.fair.entity.OpenHours;

import java.util.List;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.HoursViewHolder> {

    private List<OpenHours.OpenHour> openHours;

    public HoursAdapter(List<OpenHours.OpenHour> openHours) {
        this.openHours = openHours;
    }

    @NonNull
    @Override
    public HoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hours, parent, false);
        return new HoursViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder holder, int position) {
        OpenHours.OpenHour openHour = openHours.get(position);
        holder.tvDay.setText(Day.valueOf(openHour.getDay()).toString());
        holder.tvHours.setText(openHour.getStart().substring(0, 2) + ":" + openHour.getStart().substring(2)
                + " to " + openHour.getEnd().substring(0, 2) + ":" + openHour.getEnd().substring(2));
    }

    @Override
    public int getItemCount() {
        return openHours.size();
    }

    public class HoursViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDay;
        private TextView tvHours;

        public HoursViewHolder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tv_day);
            tvHours = itemView.findViewById(R.id.tv_hours);
        }

    }

}
