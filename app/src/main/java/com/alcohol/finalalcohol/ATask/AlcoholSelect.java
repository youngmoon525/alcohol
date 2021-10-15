package com.alcohol.finalalcohol.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.alcohol.finalalcohol.Dto.al_info_tbVO;
import com.alcohol.finalalcohol.Dto.fridge_info_tbVO;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;
import static com.alcohol.finalalcohol.Common.CommonMethod.allist;

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
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class AlcoholSelect extends AsyncTask<Void, Void, List<al_info_tbVO>>  {
    private static final String TAG = "술:AlcoholSelect";

    //필수 1. extends AsyncTask<>
    //필수 2. 생성자
    //필수 3. doInBackground
    // extends AsyncTask 을 상속받아야 쓸 수 있다.

    //빨간줄 뜰 때 알트 + 엔터 : 오버라이드doInBackground메소드
    //Void → doInBackground
    //Integer → onProgressUpdate
    //String → onPostExcute

    //항상 생성자를 만들어서 필요한 데이터를 받는다.
    //무조곤 태스크 만들때 생성자 만들기!
      /*
    ① 생성자 만들기
    ② onPreExecute() : 넘겨받은 객체들에 초기화가 필요할때 사용
    ③ doInBackGround  : 대부분의 기능들은 여기다 작성
    ④ onProgressUpdate : 필요하면 씀, 안쓰면 매개 변수의 Integer를 Void로 바꿔준다
    ⑤ onPostExecute() : String도 필요하면 쓰고 안쓰면 String을 Void로 바꿔준다
     */
    String input_id;
    int al_id ;
    String al_name;
    String al_manufacturer;
    String al_material;
    String al_vol;
    int al_body ;
    int al_alcohol_type;
    int al_flavor;
    int al_smell;
    int al_alcohol_bv;
    String al_real_alcohol_bv;

    String content2;

    /*생성자*/

    public AlcoholSelect(String input_id) {
        //this.al_id = al_id;
        this.input_id = input_id;
        this.al_id = Integer.parseInt(input_id);
    }

    /*반드시 선언해야함*/
    HttpClient httpClient;  /*클라이언트 객체 : */
    HttpPost httpPost;  /*클라이언트에 보낼 내용*/
    HttpResponse httpResponse;  /*서버에서의 응답을 받는다*/
    HttpEntity httpEntity;  /*응답의 내용을 가져온다*/

    //초기화시키는 메소드(doInBackground 메소드 실행되기 전에 설정 및 초기화)
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    //doInBackground 를 반드시 오버라이드 해야 쓸 수 있다.
    //실질적으로 일을 하는 부분 : 접근 못함(textView, button 등을 접근 못함.)

    /*doInBackground()의 변수 : AsyncTask를 execute할 때 전해줄 값
    onProgressUpdate()의 변수 : 진행상황을 업데이트할 때 사용 할 값
    onPostExecute()의 변수 : AsyncTask가 끝난 뒤 결과 값*/
    protected List<al_info_tbVO> doInBackground(Void... voids) {

        try {
            /*MultipartEntityBuilder 생성 : 반드시 선언*/
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            /*문자열 추가*/
            builder.addTextBody("input_id", input_id, ContentType.create("Multipart/related","UTF-8"));

            /*전송*/
            String postURL = ipConfig + "/Select_al_info_tb";


            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);//여기 라인에서 DB에 보냄

            //httpEntity = httpResponse.getEntity();
            //inputStream = httpEntity.getContent();
            content2 = EntityUtils.toString(httpResponse.getEntity());

            /*응답*/
            //dto = readMessage(inputStream);
            allist = (List<al_info_tbVO>) readMessage2(content2);

            //nputStream.close();

        }catch (Exception e){
            Log.d(TAG, "doInBackground: " + e.getMessage());
            e.getMessage();
        }finally {  //마지막에 무조건 수행해야할 것
            if(httpEntity != null){
                httpEntity = null;
            }else if(httpResponse != null){
                httpResponse = null;
            }else if(httpPost != null) {
                httpPost = null;
            }else if(httpClient != null){
                httpClient= null;
            }
        }
        return allist;
    }

    /*종료된 후 */
    protected void onPostExecute(String result) {
        //super.onPostExecute(result);
        Log.d("알코올셀렉트어싱크", "onPostExecute: result : " + result);
    }//onPostExecute

    public List<al_info_tbVO> readMessage2(String content2) throws IOException {
        //JsonReader reader = new JsonReader(new InputStreamReader(inputStream,"UTF-8"));
        List<al_info_tbVO> list = new ArrayList<al_info_tbVO>();
        try {
            JSONArray jArray = new JSONArray(content2);
            for(int i=0; i<jArray.length();i++) {
                JSONObject row = jArray.getJSONObject(i);
                al_info_tbVO dto = new al_info_tbVO();
                dto.setAl_id(row.getInt("al_id"));
                dto.setAl_name(row.getString("al_name"));
                dto.setAl_manufacturer(row.getString("al_manufacturer"));
                dto.setAl_material(row.getString("al_material"));
                dto.setAl_vol(row.getString("al_vol"));
                dto.setAl_body(row.getInt("al_body"));
                dto.setAl_alcohol_type(row.getInt("al_alcohol_type"));
                dto.setAl_flavor(row.getInt("al_flavor"));
                dto.setAl_smell(row.getInt("al_smell"));
                dto.setAl_alcohol_bv(row.getInt("al_alcohol_bv"));
                dto.setAl_real_alcohol_bv(row.getString("al_real_alcohol_bv"));
                if(row.getString("al_imgname") != null){
                    dto.setAl_imgname(row.getString("al_imgname"));
                }
                if(row.getString("al_imgpath") != null){
                    dto.setAl_imgpath(row.getString("al_imgpath"));
                }
/*                if(row.getString("al_mini_have") != null){
                    dto.setAl_mini_have(row.getString("al_mini_have"));
                }
                if(row.getString("al_mini_vol") != null){
                    dto.setAl_mini_vol(row.getString("al_mini_vol"));
                }
                if(row.getString("al_mini_imgname") != null){
                    dto.setAl_mini_imgname(row.getString("al_mini_imgname"));
                }
                if(row.getString("al_mini_imgpath") != null){
                    dto.setAl_mini_imgpath(row.getString("al_mini_imgpath"));
                }*/

                list.add(dto);
            }
        } catch (JSONException e) {
            Log.d(TAG, "readMessage: " + e.getStackTrace() + ", msg : " + e.getMessage());
        }
        // 핸들러에게 메시지를 요청
        Log.d("main:", "readMessage: " + list.get(0).getAl_name() );

        return list;
    }


}
