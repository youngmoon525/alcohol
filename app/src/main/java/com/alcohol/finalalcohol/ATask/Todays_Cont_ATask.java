package com.alcohol.finalalcohol.ATask;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.nio.charset.Charset;

public class Todays_Cont_ATask extends AsyncTask <Void, Void, Void> {
    private static final String TAG = "main: Todays_Cont_ATask";
    //사용할 변수
    String result;
    Bundle bundle;

    // 반드시 선언해야 할것들 : 무조건 해야함 복,붙
    HttpClient httpClient;  // 클라이언트 객체
    HttpPost httpPost;      // 클라이언트에 붙일 본문
    HttpResponse httpResponse; // 서버에서의 응답
    HttpEntity httpEntity;  // 응답 내용

    public Todays_Cont_ATask(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // MultipartEntityBuilder 생성 : 무조건 해야함
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 우리가 수정해야 하는 부분 : 서버에 보내는 데이터
            // 문자열 및 데이터 추가하기
            if (bundle.get("progress") != null) {
                builder.addTextBody("progress", String.valueOf(bundle.get("progress")), ContentType.create("Multipart/related", "UTF-8"));
            }
            if(bundle.get("color") != null){
                builder.addTextBody("color", String.valueOf(bundle.get("color")), ContentType.create("Multipart/related", "UTF-8"));
            }
            if(bundle.get("angle") != null){
                builder.addTextBody("angle", String.valueOf(bundle.get("angle")),ContentType.create("Multipart/related", "UTF-8"));
            }
            if(bundle.get("switchFridge") != null){
                builder.addTextBody("switchFridge", String.valueOf(bundle.get("switchFridge")),ContentType.create("Multipart/related", "UTF-8"));
            }
            if(bundle.get("moodLight") != null){
                builder.addTextBody("moodLight", String.valueOf(bundle.get("moodLight")),ContentType.create("Multipart/related", "UTF-8"));
            }
            if(bundle.get("switchLight") != null){
                builder.addTextBody("switchLight", String.valueOf(bundle.get("switchLight")),ContentType.create("Multipart/related", "UTF-8"));
            }



            // 전송 url
            String postURL = ipConfig + "/app/todays.Control";

            Log.d(TAG, "doInBackground: " + postURL);

            // DB와 연동할 http 객체
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost); // 응답


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            if(httpClient != null){
                httpClient = null;
            }
        }

        return null;
    }
}
