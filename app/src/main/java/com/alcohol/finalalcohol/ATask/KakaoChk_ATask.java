package com.alcohol.finalalcohol.ATask;

import com.alcohol.finalalcohol.Common.CommonMethod;
import com.alcohol.finalalcohol.DTO.Mem_Info_DTO;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;
import static com.alcohol.finalalcohol.Common.CommonMethod.loginDTO;
import static com.alcohol.finalalcohol.Common.CommonMethod.loginList;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class KakaoChk_ATask extends AsyncTask<Void,Void,String> {

    private static final String TAG = "AsyncChk:";
    String member_kakao;
    ArrayList<Mem_Info_DTO> list;

    public KakaoChk_ATask(String member_kakao) {
        this.member_kakao = member_kakao;
    }

    String state = ""; // 결과 값 넣을 변수

    //반드시 선언해야 할것들 : 무조건 해야함
    HttpClient httpClient;      // 클라이언트 객체
    HttpPost httpPost;          // 클라이언트에 붙일 본문
    HttpResponse httpResponse;  // 서버에서의 응답
    HttpEntity httpEntity;      // 응답 내용

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        //loginDTO = new Mem_Info_DTO();
        try {
            //MultipartEntityBuilder 생성 : 무조건 해야함
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            //우리가 수정해야 하는 부분 : 서버에 보내는 데이터
            //문자열 및 데이터 추가하기
            builder.addTextBody("mem_kakao", member_kakao, ContentType.create("Multipart/related", "UTF-8"));
            Log.d("카카오체크", "doInBackground: 빌더");

            //전송
            String postURL = ipConfig + "/app/Chk_kakao";

            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            Log.d("카카오체크", "doInBackground: 보내기");
            httpResponse = httpClient.execute(httpPost);    //응답
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            // 응답 : 문자열 형태
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null){
                stringBuilder.append(line);
            }//while

            state = stringBuilder.toString();
            inputStream.close();

        } catch (Exception e) {
            Log.d(TAG, "doInBackground: " + e.getMessage());
        } finally {
            if (httpEntity != null) {
                httpEntity = null;
            }//if
            if (httpResponse != null) {
                httpResponse = null;
            }//if
            if (httpPost != null) {
                httpPost = null;
            }//if
            if (httpClient != null) {
                httpClient = null;
            }//if
        }//try & catch & finally
        return state;
    }//doInBackground

    // doInBackground 끝난 후에 오는 부분
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.d("main:", "onPostExecute: result => " + result);
    }//onPostExecute
}
