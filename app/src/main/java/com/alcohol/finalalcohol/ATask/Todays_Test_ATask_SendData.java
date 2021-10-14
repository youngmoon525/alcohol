package com.alcohol.finalalcohol.ATask;

import static com.alcohol.finalalcohol.Common.CommonMethod.al_info_DTO;
import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.alcohol.finalalcohol.DTO.Al_Info_DTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

public class Todays_Test_ATask_SendData extends AsyncTask<Void,Void,Void> {
    private static final String TAG = "main: ATask_SendData";
    //사용할 변수
    String result;
    Bundle selectBundle;

    // 반드시 선언해야 할것들 : 무조건 해야함 복,붙
    HttpClient httpClient;  // 클라이언트 객체
    HttpPost httpPost;      // 클라이언트에 붙일 본문
    HttpResponse httpResponse; // 서버에서의 응답
    HttpEntity httpEntity;  // 응답 내용

    public Todays_Test_ATask_SendData(Bundle selectBundle) {
        this.selectBundle = selectBundle;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
            for (int i = 1; i <= 14; i++){
                builder.addTextBody("answer"+(i),String.valueOf(selectBundle.get(String.valueOf(i))), ContentType.create("Multipart/related", "UTF-8"));
                builder.addTextBody("testId"+(i),String.valueOf(selectBundle.get("testId"+i)), ContentType.create("Multipart/related", "UTF-8"));
            }

            // 전송 url
            String postURL = ipConfig + "/app/test.todaysAnswer";

            Log.d(TAG, "doInBackground: " + postURL);

            // DB와 연동할 http 객체
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost); // 응답

            //DB 에서 가져온 값 저장
            result = EntityUtils.toString(httpResponse.getEntity());

            //String 형태로 저장된 값을 사용하기 위해 List<> 형태로 변환
            al_info_DTO = ConvertDTO(result);

        }catch (Exception e){
            e.getMessage();
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

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
    }

    //String 형태로 저장된 값을 사용하기 위해 List<> 형태로 변환
    private Al_Info_DTO ConvertDTO(String result) throws IOException {
        //List<Al_info_DTO> list = new ArrayList<Al_info_DTO>();
        Al_Info_DTO dto = new Al_Info_DTO();

        try {
            //JSONArray array = new JSONArray(result);
            //for (int i = 0; i < array.length(); i++){
                JSONObject jObject = new JSONObject(result);

                dto.setAl_id(Integer.parseInt(jObject.getString("al_id")));
                dto.setAl_name(jObject.getString("al_name"));
                dto.setAl_manufacturer(jObject.getString("al_manufacturer"));
                dto.setAl_material(jObject.getString("al_material"));
                dto.setAl_vol(jObject.getString("al_vol"));
                dto.setAl_body(jObject.getString("al_body"));
                dto.setAl_alcohol_type(jObject.getString("al_alcohol_type"));
                dto.setAl_flavor(jObject.getString("al_flavor"));
                dto.setAl_smell(jObject.getString("al_smell"));
                dto.setAl_alcohol_bv(jObject.getString("al_alcohol_bv"));
                dto.setAl_real_alcohol_bv(jObject.getString("al_real_alcohol_bv"));
                dto.setAl_imgname(jObject.getString("al_imgname"));
                dto.setAl_imgpath(jObject.getString("al_imgpath"));
                dto.setAl_mini_have(jObject.getString("al_mini_have"));
                dto.setAl_mini_vol(jObject.getString("al_mini_vol"));
                dto.setAl_mini_imgname(jObject.getString("al_mini_imgname"));
                dto.setAl_mini_imgpath(jObject.getString("al_mini_imgpath"));

            } catch (JSONException e) {
            Log.d("main:", "ConvertListException: " + e.getStackTrace() + ", msg: " + e.getMessage());
        }


        //값 정상입력 확인용 메세지
        Log.d("main:", "readMessage: " + dto.getAl_name());

        return dto;

    }

}
