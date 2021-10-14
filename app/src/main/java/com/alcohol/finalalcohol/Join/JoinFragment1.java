package com.alcohol.finalalcohol.Join;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alcohol.finalalcohol.ATask.JoinInsert;
import com.alcohol.finalalcohol.JoinActivity;
import com.alcohol.finalalcohol.LoginActivity;
import com.alcohol.finalalcohol.MainActivity;
import com.alcohol.finalalcohol.Join.JoinFragment2;
import com.alcohol.finalalcohol.R;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class JoinFragment1 extends Fragment {
    private static final String TAG = "main : JoinFragment1";
    public int reqPicCode = 1004;
    ImageView imageView;
    File imgFile = null;
    String imgFilePath = "";

    String state;

    EditText etEmail;
    EditText etNickname;
    EditText etPass;
    EditText etPassch;
    EditText etAddr;

    TextView tvEmail;
    TextView tvNickname;
    TextView tvPass;
    TextView tvPassch;

    RadioGroup genderGroup1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_join1, container, false);

        etEmail = rootView.findViewById(R.id.etEmail);
        tvEmail = rootView.findViewById(R.id.tvEmail);

        etNickname = rootView.findViewById(R.id.etNickname);
        tvNickname = rootView.findViewById(R.id.tvNickname);

        etPass = rootView.findViewById(R.id.etPass);
        tvPass = rootView.findViewById(R.id.tvPass);

        etPassch = rootView.findViewById(R.id.etPassch);
        tvPassch = rootView.findViewById(R.id.tvPassch);

        etAddr = rootView.findViewById(R.id.etAddr);

/*        btnMale = rootView.findViewById(R.id.btnMale);
        btnFemale = rootView.findViewById(R.id.btnFemale);*/

        Pattern emailPattern = Patterns.EMAIL_ADDRESS;

        genderGroup1 = (RadioGroup)rootView.findViewById(R.id.genderGroup1);
        // 체크박스와 달리 라디오 그룹은 그룹 전체를 등록한다
        // RadioButton 은 각각 선언하는 것이 아니라 RadioGroup 으로 선언해서 사용한다.

        // 이메일 포커스
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!emailPattern.matcher(etEmail.getText().toString()).matches()){
                    tvEmail.setText("이메일형식이 아닙니다.");
                    etEmail.setBackgroundResource(R.drawable.focusround);
                    return;
                }else {
                    tvEmail.setText("");
                    etEmail.setBackgroundResource(R.drawable.focusgreenround);
                }
            }
        });//etEmail

        //닉네임 포커스
        etNickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!Pattern.matches("^[a-z0-9가-힣]{8,20}$", etNickname.getText().toString())){
                    tvNickname.setText("닉네임을 확인해주세요.");
                    etNickname.setBackgroundResource(R.drawable.focusround);
                    return;
                }else {
                    tvNickname.setText("");
                    etNickname.setBackgroundResource(R.drawable.focusgreenround);

                }

            }
        });//etNickname

        //비번 포커스
        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(!Pattern.matches("^[a-z0-9ㄱ-ㅎㅏ-ㅣ~`!@#$%\\^&*()-]{8,20}$", etPass.getText().toString())) {
                    tvPass.setText("비밀번호형식이 아닙니다.");
                    etPass.setBackgroundResource(R.drawable.focusround);
                    return;

                } else {
                    tvPass.setText("");
                    etPass.setBackgroundResource(R.drawable.focusgreenround);
                }

                if(!etPass.getText().toString().equals(etPassch.getText().toString())){
                    tvPassch.setText("비밀번호가 일치하지않습니다.");
                    etPassch.setBackgroundResource(R.drawable.focusround);

                }else {
                    tvPassch.setText("");
                    etPassch.setBackgroundResource(R.drawable.focusgreenround);
                }

            }
        });//etNickname

        //비번체크
        etPassch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!etPass.getText().toString().equals(etPassch.getText().toString())){
                    tvPassch.setText("비밀번호가 일치하지않습니다.");
                    etPassch.setBackgroundResource(R.drawable.focusround);
                    return;

                }else {
                    tvPassch.setText("");
                    etPassch.setBackgroundResource(R.drawable.focusgreenround);
                }

            }
        });//etPassch

        //취소키
        rootView.findViewById(R.id.btnExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1. builder 선언
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                // 2. 알림창 제목 설정
                builder.setTitle("회원가입 취소");
                //3. 알림창 내용
                builder.setMessage("회원가입을 취소하시겠습니까?\n입력된내용이 초기화됩니다.");
                // 3. 알림창에 띄울 요소
                builder.setPositiveButton("회원가입취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });//회원가입취소

                builder.setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });//돌아가기
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });//btnExit

        //회원가입
        rootView.findViewById(R.id.btnJoin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pattern emailPattern = Patterns.EMAIL_ADDRESS;

                String email =etEmail.getText().toString();
                String nickname =etNickname.getText().toString();
                String pass =etPass.getText().toString();
                String passch =etPassch.getText().toString();
                String addr =etAddr.getText().toString();

                if(!emailPattern.matcher(email).matches()){
                    Toast.makeText(getContext(), "이메일을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etEmail.requestFocus();
                    return;
                }

                if( !Pattern.matches("^[a-z0-9가-힣]{4,20}$", nickname) ){
                    Toast.makeText(getContext(), "닉네임을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etNickname.requestFocus();
                    return;
                }

                if(!Pattern.matches("^[a-z0-9ㄱ-ㅎㅏ-ㅣ~`!@#$%\\^&*()-]{8,20}$", pass)){
                    Toast.makeText(getContext(), "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etPass.requestFocus();
                    return;
                }

                if(!pass.equals(passch)){
                    Toast.makeText(getContext(), "비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etPassch.requestFocus();
                    return;
                }

                // 1. builder 선언
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                // 2. 알림창 제목 설정
                builder.setTitle("회원가입");
                //3. 알림창 내용
                builder.setMessage("회원가입을 진행하시겠습니까?");
                // 3. 알림창에 띄울 요소
                builder.setPositiveButton("회원가입", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //다음에 넘겨야함
                        int genderid = genderGroup1.getCheckedRadioButtonId();
                        RadioButton rb = (RadioButton)rootView.findViewById(genderid);
                        // 성별 : rb.getText().toString()

                        JoinFragment2 fragment2 = new JoinFragment2();//프래그먼트2 선언

                        Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                        bundle.putString("mem_email",etEmail.getText().toString());//번들에 넘길 값 저장
                        bundle.putString("mem_nickname",etNickname.getText().toString());//번들에 넘길 값 저장
                        bundle.putString("mem_pw",etPass.getText().toString());//번들에 넘길 값 저장
                        bundle.putString("mem_address",etAddr.getText().toString());//번들에 넘길 값 저장
                        bundle.putString("mem_gender",rb.getText().toString());//번들에 넘길 값 저장

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                        transaction.replace(R.id.frame, fragment2);
                        transaction.commit();

                        /*Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);*/
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

                //Log.d(String.valueOf(this),  email + "," + email2 + "," + nickname + "," + pass + "," + passch + "," + addr );

                //JoinInsert joinInsert = new JoinInsert(email, nickname, pass, addr);
/*                try {
                    state = joinInsert.execute().get();
                } catch (ExecutionException e){
                    e.printStackTrace();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }*/
            }//onClick
        });//btnJoin

        return rootView;
    }
}