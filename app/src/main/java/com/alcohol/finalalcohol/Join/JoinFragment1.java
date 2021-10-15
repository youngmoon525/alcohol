package com.alcohol.finalalcohol.Join;

<<<<<<< HEAD
import static android.app.Activity.RESULT_OK;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
=======
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
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.Button;
=======
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alcohol.finalalcohol.ATask.EmailCheck;
import com.alcohol.finalalcohol.ATask.NickCheck;
import com.alcohol.finalalcohol.LoginActivity;
import com.alcohol.finalalcohol.R;
import com.alcohol.finalalcohol.WebViewActivity;
=======
import com.alcohol.finalalcohol.ATask.JoinInsert;
import com.alcohol.finalalcohol.JoinActivity;
import com.alcohol.finalalcohol.LoginActivity;
import com.alcohol.finalalcohol.MainActivity;
import com.alcohol.finalalcohol.Join.JoinFragment2;
import com.alcohol.finalalcohol.R;
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class JoinFragment1 extends Fragment {
<<<<<<< HEAD
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

=======
    private static final String TAG = "main : JoinFragment1";
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
    public int reqPicCode = 1004;
    ImageView imageView;
    File imgFile = null;
    String imgFilePath = "";

    String state;

    EditText etEmail;
    EditText etNickname;
<<<<<<< HEAD

    EditText etPass;
    EditText etPassch;
    TextView tvAddr;
    EditText etAddr2;
    RadioGroup radioGroup;
=======
    EditText etPass;
    EditText etPassch;
    EditText etAddr;
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0

    TextView tvEmail;
    TextView tvNickname;
    TextView tvPass;
    TextView tvPassch;

<<<<<<< HEAD
    Button emch, nickch, btnAddr;
    int nickInt = 2, emailInt = 2;
=======
    RadioGroup genderGroup1;
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_join1, container, false);

<<<<<<< HEAD
        //xml 내 요소
=======
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
        etEmail = rootView.findViewById(R.id.etEmail);
        tvEmail = rootView.findViewById(R.id.tvEmail);

        etNickname = rootView.findViewById(R.id.etNickname);
        tvNickname = rootView.findViewById(R.id.tvNickname);

        etPass = rootView.findViewById(R.id.etPass);
        tvPass = rootView.findViewById(R.id.tvPass);

        etPassch = rootView.findViewById(R.id.etPassch);
        tvPassch = rootView.findViewById(R.id.tvPassch);

<<<<<<< HEAD
        radioGroup = rootView.findViewById(R.id.radioGroup);

        tvAddr = rootView.findViewById(R.id.tvAddr);
        etAddr2 = rootView.findViewById(R.id.etAddr2);

        emch = rootView.findViewById(R.id.emch);
        nickch = rootView.findViewById(R.id.nickch);
        btnAddr = rootView.findViewById(R.id.btnAddr);
=======
        etAddr = rootView.findViewById(R.id.etAddr);
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0

/*        btnMale = rootView.findViewById(R.id.btnMale);
        btnFemale = rootView.findViewById(R.id.btnFemale);*/

        Pattern emailPattern = Patterns.EMAIL_ADDRESS;

<<<<<<< HEAD
        //genderGroup1 = (RadioGroup)rootView.findViewById(R.id.genderGroup1);
        // 체크박스와 달리 라디오 그룹은 그룹 전체를 등록한다
        // RadioButton 은 각각 선언하는 것이 아니라 RadioGroup 으로 선언해서 사용한다.


=======
        genderGroup1 = (RadioGroup)rootView.findViewById(R.id.genderGroup1);
        // 체크박스와 달리 라디오 그룹은 그룹 전체를 등록한다
        // RadioButton 은 각각 선언하는 것이 아니라 RadioGroup 으로 선언해서 사용한다.

>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
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
<<<<<<< HEAD
                if(!Pattern.matches("[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$", etEmail.getText().toString())){
=======
                if(!emailPattern.matcher(etEmail.getText().toString()).matches()){
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
                    tvEmail.setText("이메일형식이 아닙니다.");
                    etEmail.setBackgroundResource(R.drawable.focusround);
                    return;
                }else {
                    tvEmail.setText("");
                    etEmail.setBackgroundResource(R.drawable.focusgreenround);
                }
            }
        });//etEmail

<<<<<<< HEAD
        //이메일 중복검사
        emch.setOnClickListener(new View.OnClickListener() {

            int ch = 0;
            String email = etEmail.getText().toString();

            @Override
            public void onClick(View view) {
                if(etEmail.getText().toString().trim().length() == 0){
                    Toast.makeText(getContext(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();

                }else if(!Pattern.matches("[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$", etEmail.getText().toString())){
                    Toast.makeText(getContext(), "이메일형식이 아닙니다.", Toast.LENGTH_SHORT).show();

                }else {
                    String email = etEmail.getText().toString();

                    EmailCheck emailCheck = new EmailCheck(email);
                    try {
                        emailInt = Integer.parseInt(emailCheck.execute().get());
                        Log.d(String.valueOf(this), "emailInt 값::::::::::" + emailInt);

                        if(emailInt == 1){
                            Toast.makeText(getContext(), "존재하는 이메일입니다.", Toast.LENGTH_SHORT).show();
                            etEmail.setBackgroundResource(R.drawable.focusround);

                        } else if(emailInt == 0){
                            // 1. builder 선언
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            // 2. 알림창 제목 설정
                            builder.setTitle("이메일 중복검사");
                            //3. 알림창 내용
                            builder.setMessage("사용가능한 이메일입니다.\n사용하시겠습니까?");
                            // 3. 알림창에 띄울 요소
                            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    etEmail.setEnabled(false);
                                    emch.setEnabled(false);
                                }
                            });//회원가입취소

                            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                    emailInt = 1;
                                }
                            });//돌아가기
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }

                    } catch (ExecutionException e){
                        e.getMessage();
                    } catch (InterruptedException e){
                        e.getMessage();
                    }
                }
            }
        });


=======
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
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
<<<<<<< HEAD
                if(!Pattern.matches("^[a-z0-9가-힣]{1,20}$", etNickname.getText().toString())){
=======
                if(!Pattern.matches("^[a-z0-9가-힣]{8,20}$", etNickname.getText().toString())){
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
                    tvNickname.setText("닉네임을 확인해주세요.");
                    etNickname.setBackgroundResource(R.drawable.focusround);
                    return;
                }else {
                    tvNickname.setText("");
                    etNickname.setBackgroundResource(R.drawable.focusgreenround);
<<<<<<< HEAD
                }
            }
        });//etNickname

        //닉네임 중복검사
        nickch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNickname.getText().toString().trim().length() == 0){
                    Toast.makeText(getContext(), "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();

                }else if(!Pattern.matches("^[a-z0-9가-힣]{1,20}$", etNickname.getText().toString())){
                    Toast.makeText(getContext(), "닉네임을 확인해주세요.", Toast.LENGTH_SHORT).show();

                }else {
                    String nickname =etNickname.getText().toString();

                    NickCheck nickCheck = new NickCheck(nickname);
                    try {
                        nickInt = Integer.parseInt(nickCheck.execute().get());
                        Log.d(String.valueOf(this), "nickInt 값::::::::::" + nickInt);
                        if(nickInt == 1){
                            Toast.makeText(getContext(), "존재하는 닉네임입니다.", Toast.LENGTH_SHORT).show();
                            etNickname.setBackgroundResource(R.drawable.focusround);

                        } else if(nickInt == 0){
                            // 1. builder 선언
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                                    nickInt = 1;
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
=======

                }

            }
        });//etNickname
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0

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
<<<<<<< HEAD
                if(!Pattern.matches("^[a-z0-9ㄱ-ㅎㅏ-ㅣ~`!@#$%\\^&*()-]{1,20}$", etPass.getText().toString())) {
=======
                if(!Pattern.matches("^[a-z0-9ㄱ-ㅎㅏ-ㅣ~`!@#$%\\^&*()-]{8,20}$", etPass.getText().toString())) {
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
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
<<<<<<< HEAD
            }
        });//etPass
=======

            }
        });//etNickname
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0

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
<<<<<<< HEAD
            }
        });//etPassch

        //주소검색
        if(btnAddr != null){
            btnAddr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity().getApplicationContext(), WebViewActivity.class);
                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                }
            });

        }

=======

            }
        });//etPassch

>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
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
<<<<<<< HEAD
                        Intent intent = new Intent(getContext(), LoginActivity.class);
=======
                        Intent intent = new Intent(getContext(), MainActivity.class);
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
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

<<<<<<< HEAD
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) rootView.findViewById(id);

                String email =etEmail.getText().toString();
                String nickname =etNickname.getText().toString();
                String pw =etPass.getText().toString();
                String pwch =etPassch.getText().toString();
                String gender = radioButton.getText().toString();
                String addr1 =tvAddr.getText().toString();
                String addr2 = etAddr2.getText().toString();
                String address = addr1 + "," + addr2;

                //Log.d(TAG, ""+ email + ", " + nickname + ", " + pw + ", " + gender + ", " + address);

                if(emailInt != 0){
                    Toast.makeText(getContext(), "이메일 중복검사를 해주세요.", Toast.LENGTH_SHORT).show();
                    return;

                }
/*
=======
                String email =etEmail.getText().toString();
                String nickname =etNickname.getText().toString();
                String pass =etPass.getText().toString();
                String passch =etPassch.getText().toString();
                String addr =etAddr.getText().toString();

>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
                if(!emailPattern.matcher(email).matches()){
                    Toast.makeText(getContext(), "이메일을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etEmail.requestFocus();
                    return;
                }
<<<<<<< HEAD
*/
                if(nickInt != 0){
                    Toast.makeText(getContext(), "닉네임 중복검사를 해주세요.", Toast.LENGTH_SHORT).show();
                    return;

                }
/*
                if( !Pattern.matches("^[a-z0-9가-힣]{1,20}$", nickname) ){
=======

                if( !Pattern.matches("^[a-z0-9가-힣]{4,20}$", nickname) ){
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
                    Toast.makeText(getContext(), "닉네임을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etNickname.requestFocus();
                    return;
                }
<<<<<<< HEAD
*/
                if(!Pattern.matches("^[a-z0-9ㄱ-ㅎㅏ-ㅣ~`!@#$%\\^&*()-]{1,20}$", pw)){
=======

                if(!Pattern.matches("^[a-z0-9ㄱ-ㅎㅏ-ㅣ~`!@#$%\\^&*()-]{8,20}$", pass)){
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
                    Toast.makeText(getContext(), "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etPass.requestFocus();
                    return;
                }

<<<<<<< HEAD
                if(!pw.equals(pwch)){
=======
                if(!pass.equals(passch)){
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
                    Toast.makeText(getContext(), "비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etPassch.requestFocus();
                    return;
                }

<<<<<<< HEAD
                JoinFragment2 fragment2 = new JoinFragment2();//프래그먼트2 선언

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("mem_email",email);//번들에 넘길 값 저장
                bundle.putString("mem_nickname",nickname);//번들에 넘길 값 저장
                bundle.putString("mem_pw",pw);//번들에 넘길 값 저장
                bundle.putString("mem_gender",gender);//번들에 넘길 값 저장
                bundle.putString("mem_address",address);//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.frame, fragment2);
                transaction.commit();
=======
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
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
            }//onClick
        });//btnJoin

        return rootView;
    }
<<<<<<< HEAD
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode){
            case SEARCH_ADDRESS_ACTIVITY:
                if(resultCode == RESULT_OK){
                    String data = intent.getExtras().getString("data");
                    if(data != null){
                        tvAddr.setText(data);
                        //Log.d(String.valueOf(this), "2.주소값 ::::::::::::::::::::" + tvAddr.length());

                        if(tvAddr.length() != 0){
                            etAddr2.setEnabled(true);
                        }
                    }
                }
        }
    }
=======
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
}