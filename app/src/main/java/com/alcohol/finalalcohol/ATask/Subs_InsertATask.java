package com.alcohol.finalalcohol.ATask;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

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


public class Subs_InsertATask extends AsyncTask<Void,Void,String> {

    int mem_id, months, day, subs_price ;
    String mem_name, cardNum, subs_addr;

    private static final String TAG = "subsInsert태스크";

    public Subs_InsertATask(int mem_id, String mem_name, String subs_addr, int months, int day, String cardNum, int subs_price) {
        this.mem_name = mem_name;
        this.mem_id = mem_id;
        this.subs_addr = subs_addr;
        this.months = months;
        this.day = day;
        this.cardNum = cardNum;
        this.subs_price = subs_price;
    }

    String state = "";
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
        Log.d(TAG, "doInBackground: " + mem_id);

        try{
            //MultipartEntityBuilder 생성 : 무조건 해야함
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            //우리가 수정해야 하는 부분 : 서버에 보내는 데이터
            //문자열 및 데이터 추가하기
            builder.addTextBody("mem_name", mem_name, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("mem_id", String.valueOf(mem_id), ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("subs_addr", String.valueOf(subs_addr), ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("months", String.valueOf(months), ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("day", String.valueOf(day), ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("cardNum", cardNum, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("subs_price", String.valueOf(subs_price), ContentType.create("Multipart/related", "UTF-8"));

            //전송
            String postURL = ipConfig + "/app/subs_insert";

            InputStream inputStream = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                httpClient = AndroidHttpClient.newInstance("android");
            }
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
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
        }catch (Exception e){
            Log.d(TAG, "doInBackground: " + e.getMessage());
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }//if
            if(httpResponse != null){
                httpResponse = null;
            }//if
            if(httpPost != null){
                httpPost = null;
            }//if
            if(httpClient != null){
                httpClient = null;
            }//if
        }//try & catch & finally
        return state;
    }//doInBackground

    // doInBackground 끝난 후에 오는 부분
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG, "onPostExecute: result : " + result.trim());
    }//onPostExecute
}//ChkATask
