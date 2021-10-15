package com.alcohol.finalalcohol.ATask;

import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;
<<<<<<< HEAD

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

=======
import static com.alcohol.finalalcohol.Common.CommonMethod.loginDTO;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.alcohol.finalalcohol.Dto.mem_info_tbVO;

>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.BufferedReader;
<<<<<<< HEAD
=======
import java.io.IOException;
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class JoinInsert extends AsyncTask<Void, Void, String> {
    //String email, nickname, pass, addr;

<<<<<<< HEAD
    String mem_email, mem_pw , mem_name, mem_nickname, mem_birth,  mem_address, mem_post, mem_gender, mem_phone;
    int mem_body, mem_alcohol_type, mem_flavor, mem_smell, mem_alcohol_bv;


    public JoinInsert(String mem_email, String mem_pw, String mem_name, String mem_nickname, String mem_birth,  String mem_address, String mem_post, String mem_gender, String mem_phone, int mem_body, int mem_alcohol_type, int mem_flavor, int mem_smell, int mem_alcohol_bv) {
=======
    String mem_email, mem_pw , mem_name, mem_nickname, mem_birth, mem_profile_imgname, mem_profile_imgpath, mem_address, mem_post, mem_gender, mem_phone;
    int mem_body, mem_alcohol_type, mem_flavor, mem_smell, mem_alcohol_bv;


    public JoinInsert(String mem_email, String mem_pw, String mem_name, String mem_nickname, String mem_birth, String mem_profile_imgname, String mem_profile_imgpath, String mem_address, String mem_post, String mem_gender, String mem_phone, int mem_body, int mem_alcohol_type, int mem_flavor, int mem_smell, int mem_alcohol_bv) {
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
        this.mem_email = mem_email;
        this.mem_pw = mem_pw;
        this.mem_name = mem_name;
        this.mem_nickname = mem_nickname;
        this.mem_birth = mem_birth;
<<<<<<< HEAD
=======
        this.mem_profile_imgname = mem_profile_imgname;
        this.mem_profile_imgpath = mem_profile_imgpath;
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
        this.mem_address = mem_address;
        this.mem_post = mem_post;
        this.mem_gender = mem_gender;
        this.mem_phone = mem_phone;
        this.mem_body = mem_body;
        this.mem_alcohol_type = mem_alcohol_type;
        this.mem_flavor = mem_flavor;
        this.mem_smell = mem_smell;
        this.mem_alcohol_bv = mem_alcohol_bv;
    }

    String state = "";
    // 반드시 선언해야 할 것들 : 무조건 해야함 복, 붙
    HttpClient httpClient;      // 클라이언트 객체
    HttpPost httpPost;          // 클라이언트에 붙일 본문
    HttpResponse httpResponse;  // 서버에서의 응답
    HttpEntity httpEntity;      // 응답 내용

<<<<<<< HEAD
=======
    public JoinInsert(String mem_email, String mem_nickname, String mem_pw, String mem_address, String mem_gender, String mem_name, String mem_birth, String mem_phone, int mem_body, int mem_alcohol_type, int mem_smell, int mem_alcohol_bv, int mem_flavor) {
        this.mem_email = mem_email;
        this.mem_pw = mem_pw;
        this.mem_name = mem_name;
        this.mem_nickname = mem_nickname;
        this.mem_birth = mem_birth;
        this.mem_profile_imgname = mem_profile_imgname;
        this.mem_profile_imgpath = mem_profile_imgpath;
        this.mem_address = mem_address;
        this.mem_post = mem_post;
        this.mem_gender = mem_gender;
        this.mem_phone = mem_phone;
        this.mem_body = mem_body;
        this.mem_alcohol_type = mem_alcohol_type;
        this.mem_flavor = mem_flavor;
        this.mem_smell = mem_smell;
        this.mem_alcohol_bv = mem_alcohol_bv;
    }

>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
    // doInBackground 하기전에 설정 및 초기화
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // MultipartEntity 생성 : 무조건해야됨
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

<<<<<<< HEAD
            /*
            Log.d(String.valueOf(this), "email : " + mem_email + "\npw" + mem_pw + "\nname" + mem_name + "\nnickname"
                    + mem_nickname + "\nbirth" + mem_birth + "\nimgna" + mem_profile_imgname
                    + "\nimgpa" + mem_profile_imgpath + "\naddr" + mem_address + "\npost" + mem_post
                    + "\ngender" + mem_gender + "\nphone" + mem_phone + "\nbody" + mem_body
                    + "\nalcohol" + mem_alcohol_type  + "\nflavor" + mem_flavor + "\nsmell" + mem_smell
                    + "\nbv" + mem_alcohol_bv);

             */

            builder.addTextBody("mem_email", mem_email, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_pw", mem_pw, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_name", mem_name, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_nickname", mem_nickname, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_birth", mem_birth, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_address", mem_address, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_post", mem_post, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_gender", mem_gender, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_phone", mem_phone, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_body", String.valueOf(mem_body), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_alcohol_type", String.valueOf(mem_alcohol_type), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_flavor", String.valueOf(mem_flavor), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_smell", String.valueOf(mem_smell), ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("mem_alcohol_bv", String.valueOf(mem_alcohol_bv), ContentType.create("Multipart/related","UTF-8"));

            String postURL = ipConfig + "/app/tprJoin";
            //Log.d(String.valueOf(this), email);
=======
         /*   builder.addTextBody("email", email, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("nickname", nickname, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("pass", pass, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("addr", addr, ContentType.create("Multipart/related","UTF-8"));


            String postURL = ipConfig + "/tpr/prJoin";
            Log.d(String.valueOf(this), email);

>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0

            // 그대로 사용
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);    //응답
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            // 응답 : 문자열 형태

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");

            }
            state = stringBuilder.toString();

            inputStream.close();
<<<<<<< HEAD
=======
*/
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0

        } catch (Exception e){
            e.getMessage();

        } finally {
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

        }//try

        return state;
    }//doinback

    // doInBackground 끝난후에 오는부분
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.d("main:", "onPostExecute: result =>" + result);

    }


}//class
