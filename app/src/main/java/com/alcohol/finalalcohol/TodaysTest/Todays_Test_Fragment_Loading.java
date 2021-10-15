package com.alcohol.finalalcohol.TodaysTest;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alcohol.finalalcohol.MainActivity;
import com.alcohol.finalalcohol.R;

public class Todays_Test_Fragment_Loading extends Fragment {
    ImageView imgTestLoading;
    MainActivity mainActivity;
    private CountDownTimer countDownTimer;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_todays_test_loading, container, false);
        mainActivity = (MainActivity) getActivity();
        bundle = getArguments();
        //Log.d("main:", "F_Loading: " + bundle.getString("1"));

        imgTestLoading = viewGroup.findViewById(R.id.imgTestLoading);
        int time = 1000;
        countDown(time);

       // Fragment fragment = new Todays_Test_Fragment_Result();
       // replaceFrag(fragment);

        return viewGroup;
    }




    public void countDown(int time) {
        countDownTimer = new CountDownTimer(6000, 1000) {

            // 특정 시간마다 작동
            public void onTick(long millisUntilFinished) {

                if ((millisUntilFinished / time) % 2 == 0){
                    imgTestLoading.setColorFilter(getResources().getColor(R.color.coloryellow));
                }else if((millisUntilFinished / time) % 2 == 1){
                    imgTestLoading.setColorFilter(getResources().getColor(R.color.black));
                    //imgTestLoading.setImageAlpha(1);
                }
            }

            // 제한시간 종료시
            public void onFinish() {
                Fragment fragment = new Todays_Test_Fragment_Result();
                replaceFrag(fragment);
            }

        }.start();
    }


    private void replaceFrag(Fragment replaceFrag) {
        Fragment fragment = replaceFrag;
        fragment.setArguments(bundle);
        FragmentManager manager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.contain, fragment);
        fragmentTransaction.commit();
    }


}
