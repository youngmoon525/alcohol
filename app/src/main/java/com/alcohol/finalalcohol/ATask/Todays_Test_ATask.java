package com.alcohol.finalalcohol.ATask;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;
import static com.alcohol.finalalcohol.Common.CommonMethod.todays_Test_DTO_List;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.alcohol.finalalcohol.DTO.Todays_Test_DTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Todays_Test_ATask extends AsyncTask<Void, Void, String> {

    //private static final String TAG = "TodayRecoATask:";

    //가져온 값 담을 변수
    String result;
    Todays_Test_DTO dto = new Todays_Test_DTO();

    // 반드시 선언해야 할것들 : 무조건 해야함 복,붙
    HttpClient httpClient;  // 클라이언트 객체
    HttpPost httpPost;      // 클라이언트에 붙일 본문
    HttpResponse httpResponse; // 서버에서의 응답
    HttpEntity httpEntity;  // 응답 내용

    // doInBackground 하기전에 설정 및 초기화
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(Void... voids) {

        try {

            // MultipartEntityBuilder 생성 : 무조건 해야함
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 우리가 수정해야 하는 부분 : 서버에 보내는 데이터
            // 문자열 및 데이터 추가하기

            // 전송 url
            String postURL = ipConfig + "/app/test.todays";

            // DB와 연동할 http 객체
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost); // 응답

            //DB 에서 가져온 값 저장
            result = EntityUtils.toString(httpResponse.getEntity());

            //String 형태로 저장된 값을 사용하기 위해 List<> 형태로 변환
            todays_Test_DTO_List = (List<Todays_Test_DTO>) ConvertList(result);


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


    // doInBackground 끝난후에 오는부분
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.d("main:", "onPostExecute: result => " + result);
    }

    //String 형태로 저장된 값을 사용하기 위해 List<> 형태로 변환
    private List<Todays_Test_DTO> ConvertList(String result) throws IOException {
        List<Todays_Test_DTO> list = new ArrayList<Todays_Test_DTO>();

        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++){
                JSONObject jObject = array.getJSONObject(i);
                Todays_Test_DTO dto = new Todays_Test_DTO();

                dto.setTodays_a(jObject.getString("todays_a"));
                dto.setTodays_a_result(jObject.getString("todays_a_result"));
                dto.setTodays_b(jObject.getString("todays_b"));
                dto.setTodays_b_result(jObject.getString("todays_b_result"));
                dto.setTodays_id(jObject.getString("todays_id"));
                dto.setTodays_taste(jObject.getString("todays_taste"));
                dto.setTodays_q(jObject.getString("todays_q"));

                list.add(dto);
            }

        } catch (JSONException e) {
            //Log.d(TAG, "ConvertList: " + e.getStackTrace() + ", msg: " + e.getMessage());
        }

        //값 정상입력 확인용 메세지
        Log.d("main:", "readMessage: " + list.get(0).getTodays_q());

        return list;

    }



}
