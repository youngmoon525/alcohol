package com.alcohol.finalalcohol.ATask;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.alcohol.finalalcohol.Common.CommonMethod;
import com.alcohol.finalalcohol.DTO.Al_Info_DTO;

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
import java.util.ArrayList;

public class SubsATask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "AsyncChk:";
    String mem_id;
    ArrayList<Al_Info_DTO> list;

    public SubsATask(String mem_id) {
        this.mem_id = mem_id;
    }

    String state = "";
    //반드시 선언해야 할것들 : 무조건 해야함
    HttpClient httpClient;      // 클라이언트 객체
    HttpPost httpPost;          // 클라이언트에 붙일 본문
    HttpResponse httpResponse;  // 서버에서의 응답
    HttpEntity httpEntity;      // 응답 내용

    //값을 담기 위한 변수
    String body;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected String doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground: 진행중");
        try {
            //MultipartEntityBuilder 생성 : 무조건 해야함
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            //우리가 수정해야 하는 부분 : 서버에 보내는 데이터
            //문자열 및 데이터 추가하기
            builder.addTextBody("mem_id",  mem_id, ContentType.create("Multipart/related", "UTF-8"));

            //전송
            String postURL = ipConfig + "/app/subs_dataLoad";
            
            try{
                InputStream inputStream = null;
                httpClient = AndroidHttpClient.newInstance("android");
                httpPost = new HttpPost(postURL);
                httpPost.setEntity(builder.build());
                Log.d(TAG, "doInBackground: 포스트 전송");
                httpResponse = httpClient.execute(httpPost);    //응답
                httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();

                readJsonStream(inputStream);
                CommonMethod.alcoholList = list;
            } catch (IOException e){
                e.getStackTrace().toString();
                Log.d(TAG, "doInBackground: 전송 에러");
            }


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

        return null;
    }//doInBackground

    // doInBackground 끝난 후에 오는 부분
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Log.d(TAG, "onPostExecute: result : " + result);
    }//onPostExecute

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        list = new ArrayList<>(2);
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                list.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public Al_Info_DTO readMessage(JsonReader reader) throws IOException {
        int al_id = 0;
        String al_name = "", al_alcohol_type = "", al_flavor = "",
               al_smell = "", al_real_alcohol_bv="", al_imgpath="";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("al_id")) {
                al_id = Integer.parseInt(reader.nextString());
            } else if (readStr.equals("al_name")) {
                al_name = reader.nextString();
            } else if (readStr.equals("al_alcohol_type")) {
                al_alcohol_type = reader.nextString();
            }else if (readStr.equals("al_flavor")) {
                al_flavor = reader.nextString();
            }else if (readStr.equals("al_smell")) {
                al_smell = reader.nextString();
            }else if (readStr.equals("al_real_alcohol_bv")) {
                al_real_alcohol_bv = reader.nextString();
            }else if (readStr.equals("al_imgpath")) {
                al_imgpath = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("listselect:myitem", al_id + "," + al_name + "," + al_alcohol_type + "," + al_flavor+ "," + al_smell
                + "," + al_real_alcohol_bv+ "," + al_imgpath);

        Al_Info_DTO dto = new Al_Info_DTO(al_id, al_name, al_alcohol_type, al_flavor, al_smell, al_real_alcohol_bv, al_imgpath);
        return dto;

    }
}
