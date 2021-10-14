package com.alcohol.finalalcohol.TodaysAlcohol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alcohol.finalalcohol.R;

import java.util.ArrayList;

public class todaysalcoholAdapter extends RecyclerView.Adapter<todaysalcoholAdapter.ViewHolder> {
        ArrayList images;
        Context context;

public todaysalcoholAdapter(Context context, ArrayList images) {
        this.context = context;
        this.images = images;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todays_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
        }

// Binding data to the into specified position
@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int res = (int) images.get(position);
        holder.images.setImageResource(res);
        }

@Override
public int getItemCount() {
        return images.size();
        }

// Initializing the Views
public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView images;

    public ViewHolder(View view) {
        super(view);
        images = (ImageView) view.findViewById(R.id.imageView);
    }
}
}
