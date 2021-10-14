package com.alcohol.finalalcohol.ATask;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MemberUpdate extends AsyncTask<Void, Void, String> {
    String email, nickname, pw, gender, address;

    public MemberUpdate(String email, String nickname, String pw, String gender, String address) {
        this.email = email;
        this.nickname = nickname;
        this.pw = pw;
        this.gender = gender;
        this.address = address;
    }

    String state = "";
    // 반드시 선언해야 할 것들 : 무조건 해야함 복, 붙
    HttpClient httpClient;      // 클라이언트 객체
    HttpPost httpPost;          // 클라이언트에 붙일 본문
    HttpResponse httpResponse;  // 서버에서의 응답
    HttpEntity httpEntity;      // 응답 내용

    @Override
    protected String doInBackground(Void... voids) {

        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            builder.addTextBody("mem_email", email, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_nickname", nickname, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_pw", pw, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_gender", gender, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_address", address, ContentType.create("Multipart/related","UTF-8"));

            String postURL = ipConfig + "/app/tprUpdate";

            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);    //응답
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            // 응답 : 문자열 형태

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");

            }
            state = stringBuilder.toString();

            inputStream.close();

        } catch (Exception e){
            e.printStackTrace();

        } finally {
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

            }//if
        }

        return state;
    }
}
