package com.alcohol.finalalcohol;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.message.template.FeedTemplate;
import com.kakao.util.KakaoParameterException;


public class StartActivity extends AppCompatActivity {

    //TextView textView;
    public KakaoLink kakaoLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        try {
            kakaoLink = KakaoLink.getKakaoLink(StartActivity.this);
        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }

        //Hello hello = new Hello();

        //textView = (TextView) findViewById(R.id.textView);
        //textView.setText(hello.formatMessage("Android with kotlin"));

    }

}