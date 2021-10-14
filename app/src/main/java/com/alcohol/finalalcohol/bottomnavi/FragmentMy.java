package com.alcohol.finalalcohol.Bottomnavi;

import static com.alcohol.finalalcohol.Common.CommonMethod.loginDTO;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alcohol.finalalcohol.ATask.MemberDelete;
import com.alcohol.finalalcohol.MyMemInfo.*;
import com.alcohol.finalalcohol.LoginActivity;
import com.alcohol.finalalcohol.MainActivity;
import com.alcohol.finalalcohol.R;
import com.alcohol.finalalcohol.MySubsDetail.mySubsDetail;
import com.alcohol.finalalcohol.MySubsDetail.mySubsRefund;
import com.alcohol.finalalcohol.MySubsDetail.mySubsResult;

import java.util.concurrent.ExecutionException;

public class FragmentMy extends Fragment {
    MainActivity mainActivity;

    TextView tv_name, tv_email;
    Button btn_1_1, btn_1_2, btn_1_3, btn_2_1, btn_2_2, btn_2_3, btn_2_4;
    ImageView iv_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my, container, false);
        mainActivity = (MainActivity) getActivity();

        tv_name = rootView.findViewById(R.id.tv_name);
        tv_email = rootView.findViewById(R.id.tv_email);

        btn_1_1 = rootView.findViewById(R.id.btn_1_1);
        btn_1_2 = rootView.findViewById(R.id.btn_1_2);
        btn_1_3 = rootView.findViewById(R.id.btn_1_3);
        btn_2_1 = rootView.findViewById(R.id.btn_2_1);
        btn_2_2 = rootView.findViewById(R.id.btn_2_2);
        btn_2_3 = rootView.findViewById(R.id.btn_2_3);
        btn_2_4 = rootView.findViewById(R.id.btn_2_4);

        //--상단 고객 정보 띄우기
        if(loginDTO.getMem_social_type().equals("kakao")) {
            tv_name.setText(loginDTO.getMem_kakao_nickname());
        } else {
            tv_name.setText(loginDTO.getMem_name());
        }

        tv_email.setText(loginDTO.getMem_email());

        //----내구독정보----
        //구독내역
        btn_1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), mySubsDetail.class);
                intent.putExtra("title","구독 내역");
                startActivity(intent);
            }
        });

        //구독 환불 신청
        btn_1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), mySubsRefund.class);
                intent.putExtra("title","구독 환불 신청");
                startActivity(intent);
            }
        });

        //취소,환불내역
        btn_1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), mySubsResult.class);
                intent.putExtra("title","취소 및 환불 내역");
                startActivity(intent);
            }
        });


        //----회원정보-----
        btn_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), UpdateActivity.class);
                startActivity(intent);
            }
        });


        //로그아웃
        btn_2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                loginDTO = null;
                Toast.makeText(getContext(), "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //회원탈퇴
        btn_2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. builder 선언
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                // 2. 알림창 제목 설정
                builder.setTitle("회원탈퇴");
                //3. 알림창 내용
                builder.setMessage("회원탈퇴 진행하시겠습니까?\n정보는 모두 삭제됩니다.");
                // 3. 알림창에 띄울 요소
                builder.setPositiveButton("회원탈퇴", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //다음에 넘겨야함
                        String email = loginDTO.getMem_email();

                        MemberDelete memberDelete = new MemberDelete(email);
                        loginDTO = null;

                        try {
                            memberDelete.execute().get();
                        }catch (ExecutionException e){
                            e.getMessage();
                        }catch (InterruptedException e){
                            e.getMessage();
                        }
                        Toast.makeText(getContext().getApplicationContext(), "회원탈퇴완료", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                        startActivity(intent);

                        //Intent intent = new Intent(getContext(), MainActivity.class);
                        //startActivity(intent);
                        //Toast.makeText(getContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });//회원가입이어서 진행

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });//돌아가기
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        return rootView;
    }
}