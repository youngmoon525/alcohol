package com.alcohol.finalalcohol.Join;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.alcohol.finalalcohol.ATask.JoinInsert;
import com.alcohol.finalalcohol.LoginActivity;
import com.alcohol.finalalcohol.R;

import java.io.File;
import java.util.concurrent.ExecutionException;
=======

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alcohol.finalalcohol.ATask.JoinInsert;
import com.alcohol.finalalcohol.MainActivity;
import com.alcohol.finalalcohol.R;

import java.io.File;
import java.util.regex.Pattern;
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0

public class JoinFragment3 extends Fragment {

    private static final String TAG = "main : JoinFragment3";
    public int reqPicCode = 1004;
    ImageView imageView;
    File imgFile = null;
    String imgFilePath = "";

    String state;
<<<<<<< HEAD
    RadioGroup Group_flavor;
    RadioGroup Group_alcohol_bv;
    RadioGroup Group_smell;
    RadioGroup Group_alcohol_type;
    RadioGroup Group_body;
=======
    RadioGroup Group_body;
    RadioGroup Group_alcohol_type;
    RadioGroup Group_smell;
    RadioGroup Group_alcohol_bv;
    RadioGroup Group_flavor;
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
<<<<<<< HEAD
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_join3, container, false);

        String mem_email = getArguments().getString("mem_email"); // 프래그먼트1에서 받아온 값 넣기
        String mem_nickname = getArguments().getString("mem_nickname"); // 프래그먼트1에서 받아온 값 넣기
        String mem_pw = getArguments().getString("mem_pw"); // 프래그먼트1에서 받아온 값 넣기
        String mem_address = getArguments().getString("mem_address"); // 프래그먼트1에서 받아온 값 넣기
        Log.d(TAG, "onCreateView: " + mem_address);
        String mem_gender = "";
        if(getArguments().getString("mem_gender").equals("남")){
            mem_gender = "M";
        }else if (getArguments().getString("mem_gender").equals("여")){
            mem_gender = "F";
        }
        String mem_name = getArguments().getString("mem_name"); // 프래그먼트1에서 받아온 값 넣기
        String mem_birth = getArguments().getString("mem_birth"); // 프래그먼트1에서 받아온 값 넣기
        String mem_phone = getArguments().getString("mem_phone"); // 프래그먼트1에서 받아온 값 넣기
        //Log.d(TAG, ""+ mem_email + ", " + mem_nickname + ", " + mem_pw + ", " + mem_gender + ", "
        //        + mem_address + ", " + mem_name + ", " + mem_birth + ", " + mem_phone);

        Group_flavor = (RadioGroup)rootView.findViewById(R.id.Group_flavor);
        Group_alcohol_bv = (RadioGroup)rootView.findViewById(R.id.Group_alcohol_bv);
        Group_smell = (RadioGroup)rootView.findViewById(R.id.Group_smell);
        Group_alcohol_type = (RadioGroup)rootView.findViewById(R.id.Group_alcohol_type);
        Group_body = (RadioGroup)rootView.findViewById(R.id.Group_body);
=======
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_join1, container, false);

        Group_body = (RadioGroup)rootView.findViewById(R.id.Group_body);
        Group_alcohol_type = (RadioGroup)rootView.findViewById(R.id.Group_alcohol_type);
        Group_smell = (RadioGroup)rootView.findViewById(R.id.Group_smell);
        Group_alcohol_bv = (RadioGroup)rootView.findViewById(R.id.Group_alcohol_bv);
        Group_flavor = (RadioGroup)rootView.findViewById(R.id.Group_flavor);

>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0

        // 체크박스와 달리 라디오 그룹은 그룹 전체를 등록한다
        // RadioButton 은 각각 선언하는 것이 아니라 RadioGroup 으로 선언해서 사용한다.

        //취소키
        rootView.findViewById(R.id.btnExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1. builder 선언
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                // 2. 알림창 제목 설정
                builder.setTitle("회원가입 취소");
                //3. 알림창 내용
                builder.setMessage("회원가입을 취소하시겠습니까?\n입력된내용이 초기화됩니다.");
                // 3. 알림창에 띄울 요소
                builder.setPositiveButton("회원가입취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
<<<<<<< HEAD
                        Intent intent = new Intent(getContext(), LoginActivity.class);
=======
                        Intent intent = new Intent(getContext(), MainActivity.class);
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
                        startActivity(intent);
                    }
                });//회원가입취소

                builder.setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });//돌아가기
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });//btnExit

        //회원가입
<<<<<<< HEAD
        String finalMem_gender = mem_gender;
=======
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
        rootView.findViewById(R.id.btnJoin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

<<<<<<< HEAD
                int mem_flavor_int = Group_flavor.getCheckedRadioButtonId();
                RadioButton buttonFlavor = (RadioButton) rootView.findViewById(mem_flavor_int);
                String mem_flavor_str = buttonFlavor.getText().toString();
                //Log.d(TAG, "onClick: " + mem_flavor_str);
                int mem_flavor = 0;

                if(mem_flavor_str.equals("sweet")){
                    mem_flavor = 9;
                } else if(mem_flavor_str.equals("acidity")){
                    mem_flavor = 10;
                } else if(mem_flavor_str.equals("bitter")){
                    mem_flavor = 11;
                } else if(mem_flavor_str.equals("salty")){
                    mem_flavor = 12;
                } else if(mem_flavor_str.equals("spicy")){
                    mem_flavor = 13;
                }
                //RadioButton flavorButton = (RadioButton) rootView.findViewById(mem_flavor);

                int mem_alcohol_bv_int = Group_alcohol_bv.getCheckedRadioButtonId();
                RadioButton buttonAlcoholBv = (RadioButton) rootView.findViewById(mem_alcohol_bv_int);
                String mem_alcohol_bv_str = buttonAlcoholBv.getText().toString();
                int mem_alcohol_bv = 0;

                if(mem_alcohol_bv_str.equals("1~10")){
                    mem_alcohol_bv = 18;
                } else if(mem_alcohol_bv_str.equals("11~20")){
                    mem_alcohol_bv = 19;
                } else if(mem_alcohol_bv_str.equals("21~30")){
                    mem_alcohol_bv = 20;
                } else if(mem_alcohol_bv_str.equals("31~40")){
                    mem_alcohol_bv = 21;
                }
                //RadioButton bvButton = (RadioButton) rootView.findViewById(mem_alcohol_bv);

                int mem_smell_int = Group_smell.getCheckedRadioButtonId();
                RadioButton buttonSmell = (RadioButton) rootView.findViewById(mem_smell_int);
                String mem_smell_str = buttonSmell.getText().toString();
                int mem_smell = 0;

                if(mem_smell_str.equals("fruit")){
                    mem_smell = 14;
                } else if(mem_smell_str.equals("root")){
                    mem_smell = 15;
                } else if(mem_smell_str.equals("grain")){
                    mem_smell = 16;
                } else if(mem_smell_str.equals("plant")){
                    mem_smell = 17;
                }
                //RadioButton smellButton = (RadioButton) rootView.findViewById(mem_smell);

                int mem_alcohol_type_int = Group_alcohol_type.getCheckedRadioButtonId();
                RadioButton buttonAlcoholType = (RadioButton) rootView.findViewById(mem_alcohol_type_int);
                String mem_alcohol_type_str = buttonAlcoholType.getText().toString();
                int mem_alcohol_type = 0;

                if(mem_alcohol_type_str.equals("takju")){
                    mem_alcohol_type = 4;
                } else if(mem_alcohol_type_str.equals("lijueur")){
                    mem_alcohol_type = 5;
                } else if(mem_alcohol_type_str.equals("yakju")){
                    mem_alcohol_type = 6;
                } else if(mem_alcohol_type_str.equals("soju")){
                    mem_alcohol_type = 7;
                }else if(mem_alcohol_type_str.equals("fruitwine")){
                    mem_alcohol_type = 8;
                }
                //RadioButton typeButton = (RadioButton) rootView.findViewById(mem_type);

                int mem_body_int = Group_body.getCheckedRadioButtonId();
                RadioButton buttonBody = (RadioButton) rootView.findViewById(mem_body_int);
                String mem_body_str = buttonBody.getText().toString();
                int mem_body = 0;

                if(mem_body_str.equals("LightBody")){
                    mem_body = 1;
                } else if(mem_body_str.equals("MidiumBody")){
                    mem_body = 2;
                } else if(mem_body_str.equals("FullBody")){
                    mem_body = 3;
                }
                //RadioButton bodyButton = (RadioButton) rootView.findViewById(mem_body);

                Log.d(TAG, ""+ mem_flavor +","+mem_alcohol_bv+","+mem_smell+","+mem_alcohol_type+","+mem_body+",,,,,"+ finalMem_gender);

                /*
                String mem_flavor = flavorButton.getText().toString();
                String mem_alcohol_bv = bvButton.getText().toString();
                String mem_smell = smellButton.getText().toString();
                String mem_alcohol_type = typeButton.getText().toString();
                String mem_body = bodyButton.getText().toString();
                 */

                //Log.d(TAG, ""+ mem_flavor + ", " + mem_alcohol_bv + ", " + mem_smell + ", " + mem_alcohol_type + ", "
                //        + mem_body);

=======
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
                // 1. builder 선언
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                // 2. 알림창 제목 설정
                builder.setTitle("회원가입");
                //3. 알림창 내용
                builder.setMessage("회원가입을 진행하시겠습니까?");
                // 3. 알림창에 띄울 요소
<<<<<<< HEAD
                int finalMem_flavor = mem_flavor;
                int finalMem_alcohol_bv = mem_alcohol_bv;
                int finalMem_smell = mem_smell;
                int finalMem_body = mem_body;
                int finalMem_alcohol_type = mem_alcohol_type;
                builder.setPositiveButton("회원가입", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String mem_post = "";
                        int mem_id = 1;

                        JoinInsert joinInsert =
                                new JoinInsert(mem_email, mem_pw , mem_name, mem_nickname, mem_birth
                                        ,  mem_address, mem_post
                                        , finalMem_gender, mem_phone, finalMem_body, finalMem_alcohol_type, finalMem_flavor
                                        , finalMem_smell, finalMem_alcohol_bv);

                        try {
                            state = joinInsert.execute().get();
                        } catch (ExecutionException e){
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
=======
                builder.setPositiveButton("회원가입", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //다음에 넘겨야함

                        Group_body = (RadioGroup)rootView.findViewById(R.id.Group_body);
                        Group_alcohol_type = (RadioGroup)rootView.findViewById(R.id.Group_alcohol_type);
                        Group_smell = (RadioGroup)rootView.findViewById(R.id.Group_smell);
                        Group_alcohol_bv = (RadioGroup)rootView.findViewById(R.id.Group_alcohol_bv);
                        Group_flavor = (RadioGroup)rootView.findViewById(R.id.Group_flavor);

                        int id_Group_body = Group_body.getCheckedRadioButtonId();
                        RadioButton rb_Group_body = (RadioButton)rootView.findViewById(id_Group_body);
                        // 바디감 : rb_Group_body.getText().toString()

                        int id_Group_alcohol_type = Group_alcohol_type.getCheckedRadioButtonId();
                        RadioButton rb_Group_alcohol_type = (RadioButton)rootView.findViewById(id_Group_alcohol_type);
                        // 주종 : rb_Group_alcohol_type.getText().toString()

                        int id_Group_smell = Group_smell.getCheckedRadioButtonId();
                        RadioButton rb_Group_smell = (RadioButton)rootView.findViewById(id_Group_smell);
                        // 향 : rb_Group_smell.getText().toString()

                        int id_Group_alcohol_bv = Group_alcohol_bv.getCheckedRadioButtonId();
                        RadioButton rb_Group_alcohol_bv = (RadioButton)rootView.findViewById(id_Group_alcohol_bv);
                        // 도수 : rb_Group_alcohol_bv.getText().toString()

                        int id_Group_flavor = Group_flavor.getCheckedRadioButtonId();
                        RadioButton rb_Group_flavor = (RadioButton)rootView.findViewById(id_Group_flavor);
                        // 맛 : rb_Group_flavor.getText().toString()

                        //JoinFragment3 fragment3 = new JoinFragment3();//프래그먼트3선언

                        //앞 프래그먼트에서 값 가져온 것 넣기
                        //name = getArguments().getString("name"); // 프래그먼트1에서 받아온 값 넣기

                        String mem_email = getArguments().getString("mem_email");
                        String mem_nickname = getArguments().getString("mem_nickname");
                        String mem_pw = getArguments().getString("mem_pw");
                        String mem_address = getArguments().getString("mem_address");
                        String mem_gender = getArguments().getString("mem_gender");
                        String mem_name = getArguments().getString("mem_name");
                        String mem_birth = getArguments().getString("mem_birth");
                        String mem_phone = getArguments().getString("mem_phone");

                        //취향넣기
                        String mem_body = rb_Group_body.getText().toString();
                        String mem_alcohol_type = rb_Group_alcohol_type.getText().toString();
                        String mem_smell= rb_Group_smell.getText().toString();
                        String mem_alcohol_bv = rb_Group_alcohol_bv.getText().toString();
                        String mem_flavor = rb_Group_flavor.getText().toString();

                        Log.d(TAG, "mem_body:"+mem_body
                                +"\nmem_alcohol_type:"+mem_alcohol_type
                                +"\nmem_email"+mem_email);

                        /*JoinInsert joinInsert = new JoinInsert(
                                mem_email, mem_nickname, mem_pw, mem_address, mem_gender, mem_name, mem_birth, mem_phone,
                                mem_body, mem_alcohol_type, mem_smell, mem_alcohol_bv, mem_flavor
                                );

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                        transaction.replace(R.id.frame, fragment2);
                        transaction.commit();*/

                        /*Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);*/
                        //Toast.makeText(getContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
                    }
                });//회원가입이어서 진행

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });//돌아가기
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                //Log.d(String.valueOf(this),  email + "," + email2 + "," + nickname + "," + pass + "," + passch + "," + addr );

                //JoinInsert joinInsert = new JoinInsert(email, nickname, pass, addr);
/*                try {
                    state = joinInsert.execute().get();
                } catch (ExecutionException e){
                    e.printStackTrace();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }*/
            }//onClick
        });//btnJoin

        return rootView;
    }
}