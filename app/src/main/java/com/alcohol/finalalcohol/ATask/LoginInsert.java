package com.alcohol.finalalcohol.ATask;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;
import static com.alcohol.finalalcohol.Common.CommonMethod.loginDTO;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.alcohol.finalalcohol.DTO.Mem_Info_DTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

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

            builder.addTextBody("mem_email", email, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_pw", pw, ContentType.create("Multipart/related","UTF-8"));
            Log.d(String.valueOf(this),  "2. 로그인값 넘겨주기 전" + email + ":" + pw);

            String postURL = ipConfig + "/app/tprLogin";
            Log.d(String.valueOf(this), "3. 로그인 값 넘김");

            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);    //응답
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            loginDTO = readMessage(inputStream);
            //Log.d(String.valueOf(this), "dto값" + loginDTO.getMem_email());

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

    public Mem_Info_DTO readMessage(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        int mem_id = 0, mem_body = 0, mem_alcohol_type = 0, mem_flavor=0, mem_smell=0, mem_alcohol_bv=0,mem_kakao_id=0;
        String mem_email = "",  mem_name = "", mem_nickname = "", mem_birth="",mem_adult="",mem_subs="", mem_social_type="", mem_kakao="",mem_kakao_nickname="",mem_naver="",
                mem_naver_nickname="",mem_naver_id="",mem_join_dt="",mem_profile_imgname="",mem_profile_imgpath="",mem_address="",mem_post="",mem_gender="",mem_phone="",
                mem_card="",mem_event_dt_brew="",mem_event_dt_mini="" ;
        reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();

            if(readStr.equals("mem_id")){
                mem_id = reader.nextInt();

            }else if(readStr.equals("mem_email")){
                mem_email = reader.nextString();

            }else if(readStr.equals("mem_name")){
                mem_name = reader.nextString();
//                Log.d(String.valueOf(this), "닉네임" +  mem_nickname);

            }else if(readStr.equals("mem_nickname")){
                mem_nickname = reader.nextString();

            }else if(readStr.equals("mem_body")){
                mem_body = reader.nextInt();

            }else if(readStr.equals("mem_alcohol_type")){
                mem_alcohol_type = reader.nextInt();

            }else if(readStr.equals("mem_flavor")){
                mem_flavor = reader.nextInt();

            }else if(readStr.equals("mem_smell")){
                mem_smell = reader.nextInt();

            }else if(readStr.equals("mem_alcohol_bv")){
                mem_alcohol_bv = reader.nextInt();

            }else if(readStr.equals("mem_address")){
                mem_address = reader.nextString();

            }else if(readStr.equals("mem_flavor")){
                mem_flavor = reader.nextInt();

            }else if(readStr.equals("mem_birth")){
                mem_birth = reader.nextString();

            }else if(readStr.equals("mem_adult")){
                mem_adult = reader.nextString();

            }else if(readStr.equals("mem_subs")){
                mem_subs = reader.nextString();

            }else if(readStr.equals("mem_social_type")){
                mem_social_type = reader.nextString();

            }else if(readStr.equals("mem_kakao")){
                mem_kakao = reader.nextString();

            }else if(readStr.equals("mem_kakao_nickname")){
                mem_kakao_nickname = reader.nextString();

            }else if(readStr.equals("mem_kakao_id")){
                mem_kakao_id = reader.nextInt();

            }else if(readStr.equals("mem_naver")){
                mem_naver = reader.nextString();

            }else if(readStr.equals("mem_naver_nickname")){
                mem_naver_nickname = reader.nextString();

            }else if(readStr.equals("mem_naver_id")){
                mem_naver_id = reader.nextString();

            }else if(readStr.equals("mem_join_dt")){
                mem_join_dt = reader.nextString();

            }else if(readStr.equals("mem_profile_imgname")){
                mem_profile_imgname = reader.nextString();

            }else if(readStr.equals("mem_profile_imgpath")){
                mem_profile_imgpath = reader.nextString();

            }else if(readStr.equals("mem_address")){
                mem_address = reader.nextString();

            }else if(readStr.equals("mem_post")){
                mem_post = reader.nextString();

            }else if(readStr.equals("mem_gender")){
                mem_gender = reader.nextString();

            }else if(readStr.equals("mem_phone")){
                mem_phone = reader.nextString();

            }else if(readStr.equals("mem_card")){
                mem_card = reader.nextString();

            }else if(readStr.equals("mem_event_dt_brew")){
                mem_event_dt_brew = reader.nextString();

            }else if(readStr.equals("mem_event_dt_mini")){
                mem_event_dt_mini = reader.nextString();

            }else {
                reader.skipValue();

            }//if

        }//while
        reader.endObject();
        Log.d(String.valueOf(this), email + "," + mem_nickname + "," + mem_address);

        loginDTO = new Mem_Info_DTO(mem_id, mem_email, mem_name, mem_nickname, mem_body, mem_alcohol_type,mem_flavor,
                                    mem_smell, mem_alcohol_bv, mem_birth, mem_adult, mem_subs, mem_social_type, mem_kakao, mem_kakao_nickname, mem_naver,
                                    mem_naver_nickname, mem_naver_id, mem_join_dt, mem_profile_imgname, mem_profile_imgpath, mem_address, mem_post, mem_gender, mem_phone,
                                    mem_card, mem_event_dt_brew, mem_event_dt_mini);

        return loginDTO;
    }
}
