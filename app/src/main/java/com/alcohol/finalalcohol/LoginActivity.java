package com.alcohol.finalalcohol;

import static com.alcohol.finalalcohol.Common.CommonMethod.loginDTO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alcohol.finalalcohol.ATask.KakaoChk_ATask;
import com.alcohol.finalalcohol.ATask.KakaoJoin_ATask;
import com.alcohol.finalalcohol.ATask.KakaoLogin_ATask;
import com.alcohol.finalalcohol.ATask.LoginInsert;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import java.util.concurrent.ExecutionException;
//bbbbbbbbbbbbb
public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPw;
    //
    private ISessionCallback mSessionCallback;
    String state;
    int state2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //checkDangerousPermissions();

        etEmail = findViewById(R.id.etEmail);
        etPw = findViewById(R.id.etPw);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etEmail.getText().toString().length() != 0 && etPw.getText().toString().length() != 0) {
                    String email = etEmail.getText().toString();
                    String pw = etPw.getText().toString();
                    Log.d(String.valueOf(this), "1. 아이디비번 입력");

                    LoginInsert loginInsert = new LoginInsert(email, pw);

                    try {
                        loginInsert.execute().get();
                        Log.d(String.valueOf(this), "execute 성공");
                    } catch (ExecutionException e){
                        e.getMessage();
                    } catch (InterruptedException e){
                        e.getMessage();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "아이디와 암호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

//                Log.d("main", "onClick: " + loginDTO);


                if(loginDTO.getMem_email() != null){
                    Toast.makeText(LoginActivity.this, "로그인완료", Toast.LENGTH_SHORT).show();
                    Log.d(String.valueOf(this), String.valueOf(loginDTO.getMem_alcohol_type()));
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(LoginActivity.this, "아이디비번확인해주세요.", Toast.LENGTH_SHORT).show();
//                    Log.d(String.valueOf(this), "logindto" + loginDTO.getMem_nickname());
                    etEmail.setText("");
                    etPw.setText("");
                    etEmail.requestFocus();

                }
            }
        });

        //회원가입
        findViewById(R.id.btnJoin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);

            }
        });//btnJoin


        //카카오 로그인
        mSessionCallback = new ISessionCallback() {
            @Override
            public void onSessionOpened() {
                //로그인 요청
                UserManagement.getInstance().me(new MeV2ResponseCallback() {

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        //로그인 실패
                        Toast.makeText(LoginActivity.this, "로그인 도중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                        Log.d("실패!!", "onFailure: ");
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        //세션 닫힘
                        Toast.makeText(LoginActivity.this, "세션이 닫혔습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {
                        String member_nickname = result.getKakaoAccount().getProfile().getNickname();
                        String profile_imgname = result.getKakaoAccount().getProfile().getProfileImageUrl();
                        String member_kakao= result.getKakaoAccount().getEmail();
                        String member_gender= result.getKakaoAccount().getGender().toString();
                        String member_birth= result.getKakaoAccount().getBirthday();
                        String member_age_range = result.getKakaoAccount().getAgeRange().toString();    //바뀐부분

                        Log.d("member_age_range", "member_age_range: " + member_age_range);

                        //카카오 아이디 체크 : DB에 저장된 member_kakao값 조회
                        KakaoChk_ATask chk = new KakaoChk_ATask(member_kakao);
                        int check_kakao = 0;
                        try {
                            check_kakao = Integer.parseInt(chk.execute().get());    //login
                            Log.d("체크_카카오", "onSuccess: "+ check_kakao);
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // 카카오 로그인 : 카카오 아이디 조회됨
                        if(check_kakao == 1){
                            KakaoLogin_ATask login_kakao = new KakaoLogin_ATask(member_kakao);
                            try {
                                login_kakao.execute().get();
                                Log.d("카카오 로그인", "완료" + loginDTO.getMem_kakao());
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else if (check_kakao == 0){ //아이디 조회 안됨
                            //카카오 회원가입
                            int chk_join_kakao = 0;
                            KakaoJoin_ATask join_kakao = new KakaoJoin_ATask(member_nickname, profile_imgname, member_kakao, member_gender, member_age_range);
                            try {
                                chk_join_kakao = Integer.parseInt(join_kakao.execute().get());
                                Log.d("조인 카카오 체크", "onSuccess: "+ chk_join_kakao);
                                if(chk_join_kakao == 1){
                                    KakaoLogin_ATask login_kakao = new KakaoLogin_ATask(member_kakao);
                                    login_kakao.execute().get();
                                }
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        // (로그인 성공) 값 있으면 메인화면으로 이동
                        if(loginDTO.getMem_kakao() != null) {
                            Log.d("g", "onSuccess: " + loginDTO.getMem_kakao());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {}
        };

        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
        //카카오 로그인

    }


/*        //탈퇴
        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(intent);

            }
        });//btnDelete*/



    }

/*
    //권한
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
*/
