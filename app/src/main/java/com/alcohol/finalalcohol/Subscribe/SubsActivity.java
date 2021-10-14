package com.alcohol.finalalcohol.Subscribe;

import static com.alcohol.finalalcohol.Common.CommonMethod.alcoholList;
import static com.alcohol.finalalcohol.Common.CommonMethod.loginDTO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.alcohol.finalalcohol.ATask.SubsATask;
import com.alcohol.finalalcohol.DTO.Al_Info_DTO;
import com.alcohol.finalalcohol.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SubsActivity extends AppCompatActivity {

    //만들어준 요소들 정의하기
    RecyclerView recyclerView;
    SubsAdapter adapter;
    SubsATask subsATask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subs);

        //DB와 연동하기 위한 Atask 객체 선언
        String mem_id = String.valueOf(loginDTO.getMem_id());
        subsATask = new SubsATask(mem_id);

        //Atask 실행 후 값을 가져온다
        try {
            subsATask.execute().get();
        } catch (ExecutionException e) {
            e.getMessage();
        } catch (InterruptedException e) {
            e.getMessage();
        }

        recyclerView = findViewById(R.id.recyclerView);

        // *레이아웃 매니저 : 리사이클러뷰가 보일 기본적인 형태를 설정시 사용됨
        recyclerView.setLayoutManager(new LinearLayoutManager(SubsActivity.this));
        // 어댑터에 넣기 : 상위 2건의 자료만
        ArrayList<Al_Info_DTO> sub_alcoholList = new ArrayList<>();
        sub_alcoholList.add(alcoholList.get(0));
        sub_alcoholList.add(alcoholList.get(1));

        //상위 두건의 정보만 갖고있는 subs_alcoholList넣어줌
        adapter = new SubsAdapter(sub_alcoholList, SubsActivity.this);

        //ArrayList에 dto 추가해주기


        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subsATask.cancel(true);
        Log.d("TAG", "onDestroy: 어싱크 종료 ");
    }
}