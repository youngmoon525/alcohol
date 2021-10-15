package com.alcohol.finalalcohol.TodaysAlcohol;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alcohol.finalalcohol.R;

import java.util.ArrayList;
import java.util.Arrays;

public class todaysalcoholActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    // Using ArrayList to store images data
    ArrayList images = new ArrayList<>(Arrays.asList(R.drawable.poster_1, R.drawable.poster_2, R.drawable.poster_3,
            R.drawable.poster_4, R.drawable.poster_5, R.drawable.poster_6, R.drawable.poster_7, R.drawable.poster_8,
            R.drawable.poster_9, R.drawable.poster_10));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol_todays);

        // Getting reference of recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Setting the layout as Staggered Grid for vertical orientation
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        // Sending reference and data to Adapter
        todaysalcoholAdapter startAdapter = new todaysalcoholAdapter(todaysalcoholActivity.this, images);

        // Setting Adapter to RecyclerView
        recyclerView.setAdapter(startAdapter);
    }
}