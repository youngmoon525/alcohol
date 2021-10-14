package com.alcohol.finalalcohol.Subscribe;

import static java.lang.String.valueOf;
import static com.alcohol.finalalcohol.Common.CommonMethod.loginDTO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alcohol.finalalcohol.ATask.Subs_InsertATask;
import com.alcohol.finalalcohol.R;
import com.alcohol.finalalcohol.WebViewActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Subs_input extends AppCompatActivity {

    //이름
    EditText et_name;

    //주소api
    public static final int REQUEST_CODE = 10000;
    EditText et_setAddr;
    EditText et_cstAddr;

    //배송일자
    Spinner spin_delivery;
    int deliverydt;
    private ArrayList<Integer> deliveryArray = new ArrayList<>();

    //구독개월
    Spinner spin_cal;
    int months;    //선택한 값
    private ArrayList<Integer> monthsarray = new ArrayList<>();

    //결제요금
    TextView tv_cost;
    int still_cost = 39000;
    int subs_price;      //최종 결과

    //카드번호
    EditText et_cardNum;

    //구독신청 버튼, 취소 버튼
    Button btn_submit;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsinput);

        //선언
        et_name = findViewById(R.id.et_name);
        et_setAddr = findViewById(R.id.et_setAddr);
        et_cstAddr = findViewById(R.id.et_cstAddr);
        spin_delivery = findViewById(R.id.spin_delivery);
        spin_cal = findViewById(R.id.spin_months);
        et_cardNum = findViewById(R.id.et_cardNum);
        tv_cost = findViewById(R.id.tv_cost);
        btn_submit = findViewById(R.id.btn_submit);
        iv_back = findViewById(R.id.iv_back);

        //최종 구독신청시 값 넘겨주기
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //보내줄 값 목록(7개)
                int mem_id = loginDTO.getMem_id();
                String subs_addr = (et_setAddr.getText().toString() + et_cstAddr.getText().toString());
                String mem_name = et_name.getText().toString();
                int day = deliverydt;
                String cardNum = et_cardNum.getText().toString();

                Subs_InsertATask task = new Subs_InsertATask(mem_id, mem_name, subs_addr, months, day, cardNum, subs_price);
                try {
                    task.execute().get();
                    Toast.makeText(getApplicationContext(), "구독신청이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(getApplicationContext(), SubsActivity.class);
                startActivity(intent);
            }
        });
        
        //취소 버튼 누르기
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //----배송일자 spinner----
        //스피너 리스트에 값 넣어줌
        for(int i = 1; i < 32; i++){ deliveryArray.add(i); }

        ArrayAdapter<Integer> deliveryAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, deliveryArray);
        deliveryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_delivery.setAdapter(deliveryAdapter);
        spin_delivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                deliverydt = (int) spin_delivery.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //----구독개월----
        //스피너 리스트에 값 넣어줌
        for(int i = 1; i < 13; i++){ monthsarray.add(i); }

        // 구독 개월 선택 스피너

        ArrayAdapter<Integer> monthsAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, monthsarray);
        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_cal.setAdapter(monthsAdapter);
        spin_cal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                months = (int) spin_cal.getItemAtPosition(i);

                //----결제요금----
                subs_price = months * still_cost;


                tv_cost.setText(valueOf(subs_price));
                //----결제요금----
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "희망하는 구독 개월수를 선택하세요!", Toast.LENGTH_SHORT).show();
            }
        });
        //----구독개월----

        //----카카오주소API----
        //기본 주소 입력 부분 터치 안되게 막기

        et_setAddr.setFocusable(false);
        
        //주소찾기 버튼 클릭
        findViewById(R.id.btn_addr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
                if(status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI) {
                    Log.d("주소설정페이지", "주소입력창 클릭");
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }else {
                    Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d("TAG", "onActivityResult: " + 1);
        switch (requestCode){
            case REQUEST_CODE:
            if(resultCode == RESULT_OK){
                String data = intent.getExtras().getString("data");
                if(data!= null){
                    Log.d("test", "data" + data);
                    et_setAddr.setText(data);
                }
            }
            break;
        }
    }
    //----카카오주소API----
}
