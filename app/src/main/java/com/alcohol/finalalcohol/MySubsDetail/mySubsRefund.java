package com.alcohol.finalalcohol.MySubsDetail;

import static com.alcohol.finalalcohol.Common.CommonMethod.my_subsDetail_list;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.alcohol.finalalcohol.DTO.Mem_Info_DTO;
import com.alcohol.finalalcohol.DTO.My_SubsDetail_DTO;
import com.alcohol.finalalcohol.R;

import java.util.ArrayList;

public class mySubsRefund extends AppCompatActivity {

    Mem_Info_DTO mem_info_dto;


    RecyclerView recyclerView;
    mySubsDetailAdapter adapter;
    ArrayList<My_SubsDetail_DTO> mySubsList;
    TextView tvTitle;
    Bundle stateBundle;
    String updateCheck = "2";   //0: 업데이트 체크에 실패함, 1: 업데이트 체크에 성공함, 2: 업데이트 체크를 하지 않음(기본값)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_my_subsrefund);

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
        adapter = new mySubsDetailAdapter(mySubsList, mySubsRefund.this);


        //adapter 에 담을 dto 의 list 를 서버에서 가져옴
        stateBundle.putString("page","Refund");
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

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new mySubsDetailListener() {
            @Override
            public void onItemClick(mySubsDetailAdapter.ViewHolder holder, View view, int position) {
                My_SubsDetail_DTO dto = adapter.getItem(position);
            }
        });

        //환불 신청 버튼 클릭
        findViewById(R.id.btnRefund).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> selected = new ArrayList<>();
                String[] items = new String[my_subsDetail_list.size()];
                for (int i = 0; i < items.length; i++) {
                    items[i] = my_subsDetail_list.get(i).getMonth();
                    selected.add(i, "");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(mySubsRefund.this);
                builder.setTitle("환불을 실행할 구독 월을 선택하세요.");
                //다이얼로그 알림창에 선택할 목록 추가
                builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked == true){
                            selected.set(position,items[position]);
                            Log.d("main", "onClick: "+ position + "," + items[position]+","+ selected.size());
                        }else{
                            selected.set(position,"");
                        }
                    }
                });

                //확인 버튼 클릭
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        updateCheck = "1";
                        stateBundle.putString("page","RefundOn");
                        stateBundle.putString("refundSize", String.valueOf(selected.size()));
                        if(selected.size() > 0 ){
                            for (int i = 0; i < selected.size(); i++) {
                                stateBundle.putString("refund"+i, selected.get(i));
                            }
                        }
                        My_SubsDetail_ATask aTask = new My_SubsDetail_ATask(stateBundle);
                        try {
                            aTask.execute().get();
                        }catch (Exception e){
                            e.getMessage();
                        }

                        for (My_SubsDetail_DTO dto :my_subsDetail_list) {
                            if(dto.getPrice_state().equals("0")){
                                updateCheck = "0";
                            }
                        }
                        dialogInterface.dismiss();

                    }
                });

                //취소 버튼 클릭
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        showConfirm(updateCheck);
                    }
                });


            }

            private void showConfirm(String updateCheck) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mySubsRefund.this);
                if(updateCheck == "1"){
                    builder.setTitle("알림").setMessage("구독 환불이 신청되었습니다.");
                }else if(updateCheck == "0"){
                    builder.setTitle("알림").setMessage("오류가 발생했습니다. \n네트워크 상태를 확인 후 다시 시도해주세요.");
                }else{
                    builder.setTitle("알림").setMessage("구독 환불 신청을 취소했습니다.");
                }

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        finish();
                    }
                });
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
