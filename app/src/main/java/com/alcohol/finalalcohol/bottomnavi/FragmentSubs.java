package com.alcohol.finalalcohol.Bottomnavi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alcohol.finalalcohol.R;
import com.alcohol.finalalcohol.Subscribe.Subs_input;

public class FragmentSubs extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_subs, container, false);

        rootView.findViewById(R.id.btn_subs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Subs_input.class);
                startActivity(intent);
            }
        });
        
        return rootView;
    }
}
