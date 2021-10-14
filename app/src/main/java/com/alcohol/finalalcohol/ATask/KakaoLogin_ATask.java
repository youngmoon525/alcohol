package com.alcohol.finalalcohol.ATask;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;
import static com.alcohol.finalalcohol.Common.CommonMethod.loginDTO;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.alcohol.finalalcohol.DTO.Mem_Info_DTO;

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

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class KakaoLogin_ATask extends AsyncTask<Void, Void, Mem_Info_DTO> {

    private static final String TAG = "AsyncChk:";
    String member_kakao;
    List<Mem_Info_DTO> list;

    public KakaoLogin_ATask(String member_kakao) {
        this.member_kakao = member_kakao;
    }

    //반드시 선언해야 할것들 : 무조건 해야함
    HttpClient httpClient;      // 클라이언트 객체
    HttpPost httpPost;          // 클라이언트에 붙일 본문
    HttpResponse httpResponse;  // 서버에서의 응답
    HttpEntity httpEntity;      // 응답 내용

    String body;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Mem_Info_DTO doInBackground(Void... voids) {
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
            String postURL = ipConfig + "/app/login_kakao";

            httpClient = AndroidHttpClient.newInstance("android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            Log.d("카카오체크", "doInBackground: 보내기");
            httpResponse = httpClient.execute(httpPost);    //응답
            
            //응답을 문자열 형태로 body에 담기
            body = EntityUtils.toString(httpResponse.getEntity()).trim();
            // JSON을 list로 변환 후 loginDTO에 담기
            loginDTO = readMessage(body).get(0);

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
        return loginDTO;
    }//doInBackground

    // doInBackground 끝난 후에 오는 부분
    @Override
    protected void onPostExecute(Mem_Info_DTO result) {
        super.onPostExecute(result);
        //Log.d("main:", "onPostExecute: result => " + result);
    }//onPostExecute



    public List<Mem_Info_DTO> readMessage(String body) throws IOException {
        //값을 담기 위한 list 선언
        ArrayList<Mem_Info_DTO> temp = new ArrayList<Mem_Info_DTO>();
        try {
            //body에 담은 값을 json형태로 변환
            JSONArray jArray = new JSONArray(body);

            //for문을 돌려 값을 list<dto> 형태로 변환한다.
            for(int i=0; i<jArray.length();i++) {
                JSONObject row = jArray.getJSONObject(i);
                Mem_Info_DTO dto = new Mem_Info_DTO();
                dto.setMem_id(Integer.parseInt(row.getString("mem_id")));
                dto.setMem_email(row.getString("mem_email"));
                //dto.setMem_name(row.getString("mem_name"));
                dto.setMem_nickname(row.getString("mem_nickname"));
                dto.setMem_body(Integer.parseInt(row.getString("mem_body")));
                dto.setMem_alcohol_type(Integer.parseInt(row.getString("mem_alcohol_type")));
                dto.setMem_flavor(Integer.parseInt(row.getString("mem_flavor")));
                dto.setMem_smell(Integer.parseInt(row.getString("mem_smell")));
                dto.setMem_alcohol_bv(Integer.parseInt(row.getString("mem_alcohol_bv")));
                //dto.setMem_birth(row.getString("mem_birth"));
                dto.setMem_adult(row.getString("mem_adult"));
                dto.setMem_subs(row.getString("mem_subs"));
                dto.setMem_social_type(row.getString("mem_social_type"));
                dto.setMem_kakao(row.getString("mem_kakao"));
                dto.setMem_kakao_nickname(row.getString("mem_kakao_nickname"));
                dto.setMem_join_dt(row.getString("mem_join_dt"));
                dto.setMem_profile_imgname(row.getString("mem_profile_imgname"));
                //dto.setMem_address(row.getString("mem_address"));
                //dto.setMem_post(row.getString("mem_post"));
                dto.setMem_gender(row.getString("mem_gender"));
                //dto.setMem_phone(row.getString("mem_phone"));
                //dto.setMem_card(row.getString("mem_card"));
                //dto.setMem_event_dt_brew(row.getString("mem_event_dt_brew"));
                //dto.setMem_event_dt_mini(row.getString("mem_event_dt_mini"));
                temp.add(dto);
            }
        } catch (JSONException e) {
            Log.d(TAG, "readMessage: " + e.getStackTrace() + ", msg : " + e.getMessage());
        }
        // 핸들러에게 메시지를 요청
        Log.d("main:", "readMessage: " + temp.get(0).getMem_kakao() );

        return temp;
    }
}
