package com.alcohol.finalalcohol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alcohol.finalalcohol.ATask.LoginInsert;
import com.alcohol.finalalcohol.Dto.loginDTO;

import java.util.concurrent.ExecutionException;
//gggggggggggggggggggggggggggggggggggggggggggggggggggggg
public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkDangerousPermissions();

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

/*
                if(loginDTO != null){
                    Toast.makeText(LoginActivity.this, "로그인완료", Toast.LENGTH_SHORT).show();
                    Log.d(String.valueOf(this), loginDTO.getEmail());

                }else {
                    Toast.makeText(LoginActivity.this, "아이디비번확인해주세요.", Toast.LENGTH_SHORT).show();
                    etEmail.setText("");
                    etPass.setText("");
                    etEmail.requestFocus();

                }
*/


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

/*        //탈퇴
        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(intent);

            }
        });//btnDelete*/



    }


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

}