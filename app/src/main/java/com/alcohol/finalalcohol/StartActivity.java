package com.alcohol.finalalcohol;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    //TextView textView;

=======
import android.app.Activity;
import android.os.Bundle;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.message.template.FeedTemplate;
import com.kakao.util.KakaoParameterException;


public class StartActivity extends AppCompatActivity {

    //TextView textView;
    public KakaoLink kakaoLink;
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

<<<<<<< HEAD
=======
        try {
            kakaoLink = KakaoLink.getKakaoLink(StartActivity.this);
        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }

>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
        //Hello hello = new Hello();

        //textView = (TextView) findViewById(R.id.textView);
        //textView.setText(hello.formatMessage("Android with kotlin"));

    }
<<<<<<< HEAD
=======

>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
}