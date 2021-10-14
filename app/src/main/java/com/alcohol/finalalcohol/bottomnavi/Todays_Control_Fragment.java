package com.alcohol.finalalcohol.Bottomnavi;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.alcohol.finalalcohol.ATask.Todays_Cont_ATask;
import com.alcohol.finalalcohol.R;

public class Todays_Control_Fragment extends Fragment {

    ImageView ivRed,ivGreen,ivBlue,ivYellow,ivViolet,ivLightEmpty,ivLightFill;
    TextView tvLeft45, tvRight45, tvSpin180, tvSleep, tvFade, tvColorChange;
    SeekBar seekBar;
    Switch switchFridge, switchLight;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_todays_control, container, false);

        //컨트롤 요소들
        //이미지뷰
        ivRed = viewGroup.findViewById(R.id.ivRed);
        ivGreen = viewGroup.findViewById(R.id.ivGreen);
        ivBlue = viewGroup.findViewById(R.id.ivBlue);
        ivYellow = viewGroup.findViewById(R.id.ivYellow);
        ivViolet = viewGroup.findViewById(R.id.ivViolet);
        ivLightEmpty = viewGroup.findViewById(R.id.ivLightEmpty);
        ivLightFill = viewGroup.findViewById(R.id.ivLightFill);
        //텍스트뷰
        tvLeft45 = viewGroup.findViewById(R.id.tvLeft45);
        tvRight45 = viewGroup.findViewById(R.id.tvRight45);
        tvSpin180 = viewGroup.findViewById(R.id.tvSpin180);
        tvSleep = viewGroup.findViewById(R.id.tvSleep);
        tvFade = viewGroup.findViewById(R.id.tvFade);
        tvColorChange = viewGroup.findViewById(R.id.tvColorChange);
        //프로그레스, 스위치, 번들 등등
        seekBar = viewGroup.findViewById(R.id.seekBar);
        switchFridge = viewGroup.findViewById(R.id.switchFridge);
        switchLight = viewGroup.findViewById(R.id.switchLight);
        bundle = new Bundle();
        setColor();

        //seekBar.setProgress();


        //------색상 지정 부분-----------------------------------------------
        ivRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("color", "red");
                todaysContATask(bundle);
            }
        });
        ivGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("color", "green");
                todaysContATask(bundle);
            }
        });
        ivBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("color", "blue");
                todaysContATask(bundle);
            }
        });
        ivYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("color", "yellow");
                todaysContATask(bundle);
            }
        });
        ivViolet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("color", "violet");
                todaysContATask(bundle);
            }
        });
        //------색상 지정 부분-----------------------------------------------

        //------냉장고 컨트롤 부분-----------------------------------------------
        switchFridge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked){
                    bundle = new Bundle();
                    bundle.putString("switchFridge", "on");
                    todaysContATask(bundle);
                }else{
                    bundle = new Bundle();
                    bundle.putString("switchFridge", "off");
                    todaysContATask(bundle);
                }
            }
        });

        tvLeft45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("angle","left");
                todaysContATask(bundle);
            }
        });
        tvRight45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("angle","right");
                todaysContATask(bundle);
            }
        });
        tvSpin180.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("angle","half");
                todaysContATask(bundle);
            }
        });
        //------냉장고 컨트롤 부분-----------------------------------------------

        //------무드등 컨트롤 부분-----------------------------------------------
        switchLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked){
                    bundle = new Bundle();
                    bundle.putString("switchLight", "on");
                    todaysContATask(bundle);
                }else{
                    bundle = new Bundle();
                    bundle.putString("switchLight", "off");
                    todaysContATask(bundle);
                }
            }
        });

        tvSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("moodLight","sleep");
                todaysContATask(bundle);
            }
        });
        tvFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("moodLight","fade");
                todaysContATask(bundle);
            }
        });
        tvColorChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putString("moodLight","colorChange");
                todaysContATask(bundle);
            }
        });
        //------무드등 컨트롤 부분-----------------------------------------------

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //시크바 조정 중 작동
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b ) {
                bundle = new Bundle();
                bundle.putInt("progress", progress);
                Log.d("main:", "onProgressChanged: " + bundle.getInt("progress"));
            }
            //시크바 조정 시작시 작동
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            //시크바 조정 완료 후 작동
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                todaysContATask(bundle);
            }
        });

        return viewGroup;
    }

    //ATask 통신
    private void todaysContATask(Bundle bundle) {
        Todays_Cont_ATask todays_cont_aTask = new Todays_Cont_ATask(bundle);
        try {
            todays_cont_aTask.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setColor() {
        ivRed.setColorFilter(Color.parseColor("#ff4d4d"));
        ivGreen.setColorFilter(Color.parseColor("#4dff56"));
        ivBlue.setColorFilter(Color.parseColor("#4d6bff"));
        ivYellow.setColorFilter(Color.parseColor("#f3ff4a"));
        ivViolet.setColorFilter(Color.parseColor("#ea4aff"));
        ivLightEmpty.setColorFilter(Color.parseColor("#FFC107"));
        ivLightFill.setColorFilter(Color.parseColor("#FFC107"));
    }
}