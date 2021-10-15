package com.alcohol.finalalcohol.todaysAlcohol;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alcohol.finalalcohol.Common.CommonMethod;
import com.alcohol.finalalcohol.Dto.al_info_tbVO;
import com.alcohol.finalalcohol.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class todaysalcoholAdapter extends RecyclerView.Adapter<todaysalcoholAdapter.ViewHolder> {
        private static final String TAG = "todaysalcoholAdapter : ";

        //ArrayList images;
        Context context;
        List<al_info_tbVO> allist;

 public todaysalcoholAdapter(){}

public todaysalcoholAdapter(Context context, List<al_info_tbVO> allist) {
        this.context = context;
        //this.images = images;
        this.allist = allist;
    Log.d(TAG, "todaysalcoholAdapter: "+allist.get(0).getAl_id());
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
