package com.alcohol.finalalcohol.Bottomnavi;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alcohol.finalalcohol.TodaysAlcohol.todaysalcoholActivity;
import com.alcohol.finalalcohol.R;

public class Todays_Alcohol_Fragment extends Fragment {
    private static String TAG = "todays:Todays_Alcohol_Fragment";
    Button btnal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_todays_alcohol, container, false);

        btnal = rootView.findViewById(R.id.btnal);
        btnal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), todaysalcoholActivity.class);
                startActivity(intent);
                Log.d(TAG, "onClick: intent : 버튼 클릭");
            }
        });

        return rootView;
    }
}