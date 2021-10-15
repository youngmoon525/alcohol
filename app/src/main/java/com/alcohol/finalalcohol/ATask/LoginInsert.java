package com.alcohol.finalalcohol.ATask;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;
import static com.alcohol.finalalcohol.Dto.loginDTO.*;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.alcohol.finalalcohol.Common.CommonMethod;
import com.alcohol.finalalcohol.Dto.mem_info_tbVO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class LoginInsert extends AsyncTask<Void, Void, Void> {
    String email, pw;

    public LoginInsert(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }

    // 반드시 선언해야 할 것들 : 무조건 해야함 복, 붙
    HttpClient httpClient;      // 클라이언트 객체
    HttpPost httpPost;          // 클라이언트에 붙일 본문
    HttpResponse httpResponse;  // 서버에서의 응답
    HttpEntity httpEntity;      // 응답 내용

    @Override
    protected Void doInBackground(Void... voids) {


        try{
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            builder.addTextBody("email", email, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("pw", pw, ContentType.create("Multipart/related","UTF-8"));
            Log.d(String.valueOf(this),  "2. 로그인값 넘겨주기 전" + email + ":" + pw);

            String postURL = ipConfig + "/tpr/prLogin";
            Log.d(String.valueOf(this), "3. 로그인 값 넘김");

            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);    //응답
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            //loginDTO = readMessage(inputStream);
            //Log.d(String.valueOf(this), "dto값" + String.valueOf(loginDTO));

            /*
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");

            }
            state = stringBuilder.toString();
             */

            inputStream.close();

        } catch (Exception e){
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

            }//if

        }
        return null;
    }

    public mem_info_tbVO readMessage(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        String email = "", nickname = "", addr = "";

        Log.d(String.valueOf(this), "DTO 받음 : " + addr);
        reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("email")){
                email = reader.nextString();

            }else if(readStr.equals("nickname")){
                nickname = reader.nextString();
                Log.d(String.valueOf(this), "닉네임" +  nickname);

            }else if(readStr.equals("addr")){
                addr = reader.nextString();

            }else {
                reader.skipValue();

            }//if

        }//while
        reader.endObject();
        Log.d(String.valueOf(this), email + "," + nickname + "," + addr);

        return new mem_info_tbVO();
    }
}
