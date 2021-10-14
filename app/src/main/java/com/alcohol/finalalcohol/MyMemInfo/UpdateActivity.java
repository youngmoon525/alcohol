package com.alcohol.finalalcohol.MyMemInfo;

import static com.alcohol.finalalcohol.Common.CommonMethod.loginDTO;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alcohol.finalalcohol.ATask.MemberUpdate;
import com.alcohol.finalalcohol.ATask.NickCheck;
import com.alcohol.finalalcohol.LoginActivity;
import com.alcohol.finalalcohol.MainActivity;
import com.alcohol.finalalcohol.R;
import com.alcohol.finalalcohol.WebViewActivity;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class UpdateActivity extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    public int reqPicCode = 1004;
    ImageView imageView;
    File imgFile = null;
    String imgFilePath = "";

    String state;

    EditText etEmail;
    EditText etNickname;

    EditText etPass;
    EditText etPassch;
    TextView tvAddr;
    EditText etAddr2;
    RadioGroup radioGroup;

    TextView tvEmail;
    TextView tvNickname;
    TextView tvPass;
    TextView tvPassch;

    Button nickch, btnAddr;
    int nickInt = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etEmail = findViewById(R.id.etEmail);
        tvEmail = findViewById(R.id.tvEmail);
        etEmail.setText(loginDTO.getMem_email());
        etEmail.setEnabled(false);

        etNickname = findViewById(R.id.etNickname);
        tvNickname = findViewById(R.id.tvNickname);
        etNickname.setHint(loginDTO.getMem_nickname());

        etPass = findViewById(R.id.etPass);
        tvPass = findViewById(R.id.tvPass);

        etPassch = findViewById(R.id.etPassch);
        tvPassch = findViewById(R.id.tvPassch);

        radioGroup = findViewById(R.id.radioGroup);

        if(loginDTO.getMem_gender().equals("M")){
            RadioButton radioButton = findViewById(R.id.radio1);
            radioButton.setChecked(true);

        }else if (loginDTO.getMem_gender().equals("F")){
            RadioButton radioButton = findViewById(R.id.radio2);
            radioButton.setChecked(true);
        }

        tvAddr = findViewById(R.id.tvAddr);
        etAddr2 = findViewById(R.id.etAddr2);

        nickch = findViewById(R.id.nickch);
        btnAddr = findViewById(R.id.btnAddr);

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
                if(!Pattern.matches("^[a-z0-9가-힣]{1,20}$", etNickname.getText().toString())){
                    tvNickname.setText("닉네임을 확인해주세요.");
                    etNickname.setBackgroundResource(R.drawable.focusround);
                    return;
                }else {
                    tvNickname.setText("");
                    etNickname.setBackgroundResource(R.drawable.focusgreenround);

                }

            }
        });//etNickname

        //닉네임 중복검사
        nickch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNickname.getText().toString().trim().length() == 0){
                    Toast.makeText(UpdateActivity.this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    nickInt = 2;

                }else  if(!Pattern.matches("^[a-z0-9가-힣]{1,20}$", etNickname.getText().toString())){
                    Toast.makeText(UpdateActivity.this, "닉네임을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    nickInt = 2;

                }else {
                    String nickname =etNickname.getText().toString();

                    NickCheck nickCheck = new NickCheck(nickname);
                    try {
                        nickInt = Integer.parseInt(nickCheck.execute().get());
                        Log.d(String.valueOf(this), "nickInt 값::::::::::" + nickInt);
                        if(nickInt == 1){
                            Toast.makeText(UpdateActivity.this, "존재하는 닉네임입니다.", Toast.LENGTH_SHORT).show();
                            etNickname.setBackgroundResource(R.drawable.focusround);

                        } else if(nickInt == 0){
                            // 1. builder 선언
                            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                            // 2. 알림창 제목 설정
                            builder.setTitle("닉네임 중복검사");
                            //3. 알림창 내용
                            builder.setMessage("사용가능한 닉네임입니다.\n사용하시겠습니까?");
                            // 3. 알림창에 띄울 요소
                            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    etNickname.setEnabled(false);
                                    nickch.setEnabled(false);
                                }
                            });//회원가입취소

                            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();

                                }
                            });//돌아가기
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();


                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

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
                if(!Pattern.matches("^[a-z0-9ㄱ-ㅎㅏ-ㅣ~`!@#$%\\^&*()-]{1,20}$", etPass.getText().toString())) {
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

        //주소검색
        if(btnAddr != null){
            btnAddr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(UpdateActivity.this, WebViewActivity.class);
                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                }
            });
        }

        //취소키
        findViewById(R.id.btnExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1. builder 선언
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                // 2. 알림창 제목 설정
                builder.setTitle("정보수정 취소");
                //3. 알림창 내용
                builder.setMessage("정보수정을 취소하시겠습니까?\n입력된내용이 초기화됩니다.");
                // 3. 알림창에 띄울 요소
                builder.setPositiveButton("정보수정취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
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

        //정보수정
        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pattern emailPattern = Patterns.EMAIL_ADDRESS;

                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(id);
/*
                if(etNickname.getText().toString().length() == 0){
                    etNickname.setText(loginDTO.getMem_nickname());
                    nickInt = 0;
                }
*/
                String email = etEmail.getText().toString();
                String nickname =etNickname.getText().toString();
                String pw =etPass.getText().toString();
                String pwch =etPassch.getText().toString();
                String gender = radioButton.getText().toString();
                if(gender.equals("남")){
                    gender = "M";
                }else if (gender.equals("여")){
                    gender = "F";
                }
                String addr1 =tvAddr.getText().toString();
                String addr2 = etAddr2.getText().toString();
                String address = addr1 + "," + addr2;

                if(nickname.length() == 0){
                    nickname = loginDTO.getMem_nickname();
                    nickInt = 0;

                }

                if(addr1.length() == 0){
                    address = loginDTO.getMem_address();

                }

                Log.d(String.valueOf(this), ""+ email + ", " + nickname + ", " + pw + ", " + gender + ", " + address);

                if(nickInt != 0){
                    Toast.makeText(UpdateActivity.this, "닉네임 중복검사를 해주세요.", Toast.LENGTH_SHORT).show();
                    return;

                }

                if( !Pattern.matches("^[a-z0-9가-힣]{1,20}$", nickname) ){
                    Toast.makeText(UpdateActivity.this, "닉네임을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etNickname.requestFocus();
                    return;
                }

                if(!Pattern.matches("^[a-z0-9ㄱ-ㅎㅏ-ㅣ~`!@#$%\\^&*()-]{1,20}$", pw)){
                    Toast.makeText(UpdateActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    etPass.requestFocus();
                    return;
                }

                if(!pw.equals(pwch)){
                    Toast.makeText(UpdateActivity.this, "비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etPassch.requestFocus();
                    return;
                }

                // 1. builder 선언
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                // 2. 알림창 제목 설정
                builder.setTitle("정보수정");
                //3. 알림창 내용
                builder.setMessage("정보수정을 진행하시겠습니까?");
                // 3. 알림창에 띄울 요소
                String finalNickname = nickname;
                String finalAddress = address;
                String finalGender = gender;
                builder.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        MemberUpdate memberUpdate = new MemberUpdate(email, finalNickname, pw, finalGender, finalAddress);
                        try {
                            state = memberUpdate.execute().get();
                        } catch (ExecutionException e){
                            e.printStackTrace();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        loginDTO = null;
                        startActivity(intent);
                        Toast.makeText(UpdateActivity.this, "정보수정이 완료되었습니다. 다시 로그인 해야합니다.", Toast.LENGTH_SHORT).show();


                    }
                });//회원가입

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });//돌아가기
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }//onClick
        });//btnJoin

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode){
            case SEARCH_ADDRESS_ACTIVITY:
                if(resultCode == RESULT_OK){
                    String data = intent.getExtras().getString("data");
                    if(data != null){
                        tvAddr.setText(data);

                        if(tvAddr.length() != 0){
                            etAddr2.setEnabled(true);
                        }
                    }
                }
        }
    }
}
