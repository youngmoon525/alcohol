package com.alcohol.finalalcohol.TodaysTest;


import static com.alcohol.finalalcohol.Common.CommonMethod.al_info_DTO;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alcohol.finalalcohol.ATask.Todays_Test_ATask_SendData;
import com.alcohol.finalalcohol.MainActivity;
import com.alcohol.finalalcohol.R;
import com.alcohol.finalalcohol.Bottomnavi.Todays_Test_Fragment;

public class Todays_Test_Fragment_Result extends Fragment {
    MainActivity mainActivity;
    Bundle selectBundle;
    TextView tvResult;
    Button btnback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_todays_test_result, container, false);
        mainActivity = (MainActivity) getActivity();
        selectBundle = getArguments();
        Log.d("main:", "F_Result: " + selectBundle.get(String.valueOf(1)));

        Todays_Test_ATask_SendData todays_test_aTask_sendData =  new Todays_Test_ATask_SendData(selectBundle);
        try {
            todays_test_aTask_sendData.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvResult = viewGroup.findViewById(R.id.tvResult);
        btnback = viewGroup.findViewById(R.id.btnback);

        try {
            if(al_info_DTO.getAl_name().isEmpty()){
                tvResult.setText("결과값 없음");
            }else {
                tvResult.setText(al_info_DTO.getAl_name());
            }
        }catch (Exception e){
            tvResult.setText("결과값 없음");
        }

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Todays_Test_Fragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.contain, fragment);
                fragmentTransaction.commit();
            }
        });


        return viewGroup;
    }

}
