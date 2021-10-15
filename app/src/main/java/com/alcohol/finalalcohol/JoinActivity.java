package com.alcohol.finalalcohol;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.alcohol.finalalcohol.Join.JoinFragment1;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //프래그먼트1 연결
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        JoinFragment1 fragment1 = new JoinFragment1();
        transaction.replace(R.id.frame, fragment1);
        transaction.commit();
    }
}