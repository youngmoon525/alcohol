package com.alcohol.finalalcohol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class starthomeFragment extends Fragment {
    private static final String TAG = "main : homeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment, container, false);
        /*
        일단 Fragment에서 this 라는 객체는 사용이 불가능하다. 하지만 우리가 흔히 아는 Intent를 사용할 때에는 해당 Context를 가리키는 this가 필요하다.
        Fragment에서는 this 대신 getActivity()를 사용하면 현재 Context를 받아올 수가 있다.
         */
        Intent intent = new Intent(getActivity(), LoginActivity.class);//로그인액티비티로 전환


        //Intent intent = new Intent(getActivity(), MainActivity.class);//메인액티비티로 전환
        startActivity(intent);//로그인액티비티로 전환

        return rootView;
    }

}
