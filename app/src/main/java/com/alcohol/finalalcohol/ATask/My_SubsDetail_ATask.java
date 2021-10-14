package com.alcohol.finalalcohol.ATask;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;
import static com.alcohol.finalalcohol.Common.CommonMethod.loginDTO;
import static com.alcohol.finalalcohol.Common.CommonMethod.my_subsDetail_list;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.alcohol.finalalcohol.DTO.My_SubsDetail_DTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class My_SubsDetail_ATask extends AsyncTask<Void, Void, String> {

    //가져온 값 담을 변수
    String result;
    Bundle bundle;

    // 반드시 선언해야 할것들 : 무조건 해야함 복,붙
    HttpClient httpClient;  // 클라이언트 객체
    HttpPost httpPost;      // 클라이언트에 붙일 본문
    HttpResponse httpResponse; // 서버에서의 응답
    HttpEntity httpEntity;  // 응답 내용

    public My_SubsDetail_ATask(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // MultipartEntityBuilder 생성 : 무조건 해야함
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 서버에 보내는 데이터
            // 문자열 및 데이터 추가하기 - 별도의 전송 파라미터가 없을 수 있음
            //---현재 로그인한 멤버 정보를 전달--
            loginDTO.getMem_id();

            builder.addTextBody("mem_id", String.valueOf(loginDTO.getMem_id()), ContentType.create("Multipart/related", "UTF-8"));

            if (bundle.get("page") == "RefundOn"){
                for (int i = 0; i < Integer.parseInt(String.valueOf(bundle.get("refundSize"))); i++) {
                    if (bundle.get("refund"+i) != null) {
                        builder.addTextBody("refund"+i, String.valueOf(bundle.get("refund"+i)), ContentType.create("Multipart/related", "UTF-8"));
                    }
                }
                builder.addTextBody("length", String.valueOf(bundle.get("refundSize")), ContentType.create("Multipart/related", "UTF-8"));
            }

            // 전송 url
            String postURL = ipConfig + "/ndd/my.subs"+String.valueOf(bundle.get("page"));


            // DB와 연동할 http 객체
            httpClient = AndroidHttpClient.newInstance("hanul");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost); // 응답

            //DB 에서 가져온 값 저장
            result = EntityUtils.toString(httpResponse.getEntity());

            //String 형태로 저장된 값을 사용하기 위해 List<> 형태로 변환
            if(result != null){
                my_subsDetail_list = (List<My_SubsDetail_DTO>) converList(result);
            }

        }catch (Exception e){
            Log.d("main:", "doInBackground: Exception Occurred: " + e.getMessage());
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

    // doInBackground 끝난후에 오는부분
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d("main:", "onPostExecute: result => " + result);
    }

    private List<My_SubsDetail_DTO> converList(String result) {
        List<My_SubsDetail_DTO> list = new ArrayList<My_SubsDetail_DTO>();

        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++){
                JSONObject jObject = array.getJSONObject(i);
                My_SubsDetail_DTO dto = new My_SubsDetail_DTO("","","");
                dto.setMonth(jObject.getString("month"));
                dto.setAl_List(jObject.getString("al_List"));
                dto.setPrice_state(jObject.getString("price_state"));
                Log.d("main:", "My_SubsDetail_DTO: "+dto.getMonth());
                list.add(dto);
            }

        }catch (Exception e){
            Log.d("main:", "ConverList: Exception Occurred: " + e.getMessage());
            e.printStackTrace();
        }
        //값 정상입력 확인용 메세지
        if (list != null){
            Log.d("main:", "readMessage: " + list.get(0).getMonth());
        }

        return list;
    }


}
