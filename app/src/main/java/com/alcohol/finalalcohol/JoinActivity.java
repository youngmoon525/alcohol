package com.alcohol.finalalcohol;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

=======
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
import com.alcohol.finalalcohol.Join.JoinFragment1;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

<<<<<<< HEAD
=======

>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
        //프래그먼트1 연결
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        JoinFragment1 fragment1 = new JoinFragment1();
        transaction.replace(R.id.frame, fragment1);
        transaction.commit();
<<<<<<< HEAD
=======


>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
    }
}