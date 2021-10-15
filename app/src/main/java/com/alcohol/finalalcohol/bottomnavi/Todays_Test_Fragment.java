package com.alcohol.finalalcohol.Bottomnavi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alcohol.finalalcohol.R;
import com.alcohol.finalalcohol.TodaysTest.Todays_Test_Fragment_Content;

public class Todays_Test_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_todays_test, container, false);

        Button btnStartTest = viewGroup.findViewById(R.id.btnStartTest);
        btnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Todays_Test_Fragment_Content();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.contain, fragment);
                fragmentTransaction.commit();
            }
        });

        return viewGroup;

    }
}