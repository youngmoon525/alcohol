package com.alcohol.finalalcohol.TodaysTest;

import static com.alcohol.finalalcohol.Common.CommonMethod.todays_Test_DTO_List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alcohol.finalalcohol.ATask.Todays_Test_ATask;
import com.alcohol.finalalcohol.DTO.Todays_Test_DTO;
import com.alcohol.finalalcohol.MainActivity;
import com.alcohol.finalalcohol.R;

public class Todays_Test_Fragment_Content extends Fragment {

    MainActivity mainActivity;
    ProgressBar progressBar;
    TextView question, progress_per, selectedNum;
    Button btnSelect1_1, btnSelect1_2; //btnNext;
    int selectNum = 0;
    int progress_count = 0;
    Todays_Test_DTO testDTO;
    //ArrayList<String> selectList;
    Bundle selectBundle;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_todays_test_content, container, false);

        //메인프레임, 프로그레스 바, 리스트, 번들 등
        mainActivity = (MainActivity) getActivity();
        progressBar = viewGroup.findViewById(R.id.progressBar);
            //selectList = new ArrayList<>();
        selectBundle = new Bundle();

        //텍스트뷰
        question = viewGroup.findViewById(R.id.question);
        progress_per = viewGroup.findViewById(R.id.progress_per);
        selectedNum = viewGroup.findViewById(R.id.selectedNum);

        //버튼
        //btnNext = viewGroup.findViewById(R.id.btnNext);
        btnSelect1_1 = viewGroup.findViewById(R.id.btnSelect1_1);
        btnSelect1_2 = viewGroup.findViewById(R.id.btnSelect1_2);

        // 프로그레스바 설정
        progressBar.setIndeterminate(false); // 값을 변경시키겠다
        progressBar.setMax(14);
        progressBar.setProgress(1);

        Todays_Test_ATask todays_Test_ATask = new Todays_Test_ATask();
        try {
            todays_Test_ATask.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //1번 선택 누름
        btnSelect1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePage(1);
                nextPage();
            }
        });

        //2번 선택 누름
        btnSelect1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePage(2);
                nextPage();

            }
        });

        //프래그먼트 호출과 동시에 click 이벤트 실행
        btnSelect1_1.performClick();

        return viewGroup;


    }

    private void nextPage(){
        if(progress_count < 14) {
            btnSelect1_1.setTextColor(getResources().getColor(R.color.colorwhite));
            btnSelect1_1.setText(todays_Test_DTO_List.get(progress_count).getTodays_a());
            btnSelect1_2.setTextColor(getResources().getColor(R.color.colorwhite));
            btnSelect1_2.setText(todays_Test_DTO_List.get(progress_count).getTodays_b());

            progress_per.setText(String.valueOf(progress_count + 1) + "/14");
            progressBar.setProgress(progress_count + 1);

            question.setText(todays_Test_DTO_List.get(progress_count).getTodays_q());

            progress_count++;
            selectNum = 0;
        }
    }

    private void choosePage(int choice){
        if(progress_count >= 14){
            selectBundle.putString(String.valueOf(progress_count), String.valueOf(choice));
            selectBundle.putString("testId"+progress_count,todays_Test_DTO_List.get(progress_count-1).getTodays_id());

            Fragment replaceFrag = new Todays_Test_Fragment_Loading();
            replaceFrag(replaceFrag);
        }else {
            if (progress_count > 0) {

                selectBundle.putString(String.valueOf(progress_count), String.valueOf(choice));
                selectBundle.putString("testId" + progress_count, todays_Test_DTO_List.get(progress_count - 1).getTodays_id());

                try {
                    selectedNum.append(String.valueOf(selectBundle.get(String.valueOf(progress_count))));
                } catch (Exception e) {
                    selectedNum.append("N");
                }

            }

        }
    }

    private void replaceFrag(Fragment replaceFrag) {
        Fragment fragment = replaceFrag;
        fragment.setArguments(selectBundle);
        Log.d("main:", "F_Content: " + selectBundle.get("1"));
        Log.d("main:", "F_Content: " + selectBundle.get("testId1"));
        FragmentManager manager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.contain, fragment);
        fragmentTransaction.commit();
    }



}