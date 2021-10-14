package com.alcohol.finalalcohol.MySubsDetail;

import static com.alcohol.finalalcohol.Common.CommonMethod.my_subsDetail_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alcohol.finalalcohol.ATask.My_SubsDetail_ATask;
import com.alcohol.finalalcohol.DTO.My_SubsDetail_DTO;
import com.alcohol.finalalcohol.R;

import java.util.ArrayList;

public class mySubsDetail extends AppCompatActivity {

    RecyclerView recyclerView;
    mySubsDetailAdapter adapter;
    ArrayList<My_SubsDetail_DTO> mySubsList;
    TextView tvTitle;
    Bundle stateBundle;

    //Manifest에 액티비티 등록이 안되있을 경우 등록해야함!

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_my_subsdetail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        stateBundle = new Bundle();

        //recyclerview 초기화////////////////////////////
        mySubsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        ////////////////////////////////////////////////

        //adapter 객체 생성
        adapter = new mySubsDetailAdapter(mySubsList, mySubsDetail.this);


        //adapter 에 담을 dto 의 list 를 서버에서 가져옴
        stateBundle.putString("page","Detail");
        My_SubsDetail_ATask aTask = new My_SubsDetail_ATask(stateBundle);
        try {
            aTask.execute().get();
        }catch (Exception e){
            e.getMessage();
        }

        if(my_subsDetail_list != null){
            Log.d("main", "mySubsDetail onCreate: " + my_subsDetail_list.get(0).getMonth() + ",size: " + my_subsDetail_list.size());
            for (int i = 0; i < my_subsDetail_list.size(); i++) {
                adapter.addDto(new My_SubsDetail_DTO(my_subsDetail_list.get(i).getMonth(),
                        my_subsDetail_list.get(i).getAl_List(),my_subsDetail_list.get(i).getPrice_state()));
            }
        }else if(my_subsDetail_list == null){
            adapter.addDto(new My_SubsDetail_DTO("","",""));
        }


        //adapter.addDto(new My_SubsDetail_DTO("21/08","술 목록이 표시됩니다","30000"));
        //adapter.addDto(new My_SubsDetail_DTO("21/09","술 목록이 표시됩니다","30000"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new mySubsDetailListener() {
            @Override
            public void onItemClick(mySubsDetailAdapter.ViewHolder holder, View view, int position) {
                My_SubsDetail_DTO dto = adapter.getItem(position);
            }
        });

        //인텐트 종료
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
