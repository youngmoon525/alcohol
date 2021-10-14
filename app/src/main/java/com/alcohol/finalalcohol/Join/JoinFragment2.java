package com.alcohol.finalalcohol.Join;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alcohol.finalalcohol.LoginActivity;
import com.alcohol.finalalcohol.R;

import java.io.File;
import java.util.regex.Pattern;

public class JoinFragment2 extends Fragment {
    private static final String TAG = "main : JoinFragment2";
    public int reqPicCode = 1004;
    ImageView imageView;
    File imgFile = null;
    String imgFilePath = "";

    String state;

    EditText etName;
    EditText etBirth;
    EditText etPhone;

    TextView tvName;
    TextView tvBirth;
    TextView tvPhone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_join2, container, false);

        String mem_email = getArguments().getString("mem_email"); // 프래그먼트1에서 받아온 값 넣기
        String mem_nickname = getArguments().getString("mem_nickname"); // 프래그먼트1에서 받아온 값 넣기
        String mem_pw = getArguments().getString("mem_pw"); // 프래그먼트1에서 받아온 값 넣기
        String mem_address = getArguments().getString("mem_address"); // 프래그먼트1에서 받아온 값 넣기
        String mem_gender = getArguments().getString("mem_gender"); // 프래그먼트1에서 받아온 값 넣기
        Log.d(TAG, ""+ mem_email + ", " + mem_nickname + ", " + mem_pw + ", " + mem_gender + ", " + mem_address);

        etName = rootView.findViewById(R.id.etName);
        tvName = rootView.findViewById(R.id.tvName);

        etBirth = rootView.findViewById(R.id.etBirth);
        tvBirth = rootView.findViewById(R.id.tvBirth);

        etPhone = rootView.findViewById(R.id.etPhone);
        tvPhone = rootView.findViewById(R.id.tvPhone);


        //이름 포커스
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!Pattern.matches("^[가-힣]*$", etName.getText().toString())){
                    tvName.setText("이름을 확인해주세요.");
                    etName.setBackgroundResource(R.drawable.focusround);
                    return;
                }else {
                    tvName.setText("");
                    etName.setBackgroundResource(R.drawable.focusgreenround);
                }
            }
        });

        //생일 포커스
        etBirth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!Pattern.matches("^[0-9]{8}$", etBirth.getText().toString())){
                    tvBirth.setText("생년월일을 확인해주세요.");
                    etBirth.setBackgroundResource(R.drawable.focusround);
                    return;
                }else {
                    tvBirth.setText("");
                    etBirth.setBackgroundResource(R.drawable.focusgreenround);
                }
            }
        });

        //폰번호 포커스
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", etPhone.getText().toString())){
                    tvPhone.setText("핸드폰 번호을 확인해주세요.");
                    etPhone.setBackgroundResource(R.drawable.focusround);
                    return;
                }else {
                    tvPhone.setText("");
                    etPhone.setBackgroundResource(R.drawable.focusgreenround);
                }

            }
        });


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
                        Intent intent = new Intent(getContext(), LoginActivity.class);
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
        rootView.findViewById(R.id.btnJoin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mem_name=etName.getText().toString();
                String mem_birth=etBirth.getText().toString();
                String mem_phone =etPhone.getText().toString();

                JoinFragment3 fragment3 = new JoinFragment3();//프래그먼트2 선언

                if( !Pattern.matches("^[가-힣]*$", mem_name) ){
                    Toast.makeText(getContext(), "이름을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;
                }

                if( !Pattern.matches("^[0-9]{8}$", mem_birth) ){
                    Toast.makeText(getContext(), "생년월일을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etBirth.requestFocus();
                    return;
                }

                if( !Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", mem_phone) ){
                    Toast.makeText(getContext(), "핸드폰번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    etPhone.requestFocus();
                    return;
                }

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("mem_name",mem_name);//번들에 넘길 값 저장
                bundle.putString("mem_birth",mem_birth);//번들에 넘길 값 저장
                bundle.putString("mem_phone",mem_phone);//번들에 넘길 값 저장

                //앞 프래그먼트에서 값 가져온 것 넣기
                //name = getArguments().getString("name"); // 프래그먼트1에서 받아온 값 넣기
                bundle.putString("mem_email", mem_email);//번들에 넘길 값 저장
                bundle.putString("mem_nickname", mem_nickname);//번들에 넘길 값 저장
                bundle.putString("mem_pw", mem_pw);//번들에 넘길 값 저장
                bundle.putString("mem_address", mem_address);//번들에 넘길 값 저장
                bundle.putString("mem_gender", mem_gender);//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragment3.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.frame, fragment3);
                transaction.commit();
/*
                // 1. builder 선언
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                // 2. 알림창 제목 설정
                builder.setTitle("회원가입");
                //3. 알림창 내용
                builder.setMessage("회원가입을 진행하시겠습니까?");
                // 3. 알림창에 띄울 요소
                builder.setPositiveButton("회원가입", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {






                        JoinInsert joinInsert = new JoinInsert(mem_name, mem_birth, mem_phone, mem_email, mem_nickname, mem_pw, mem_address, mem_gender);
                        try {
                            state = joinInsert.execute().get();
                        } catch (ExecutionException e){
                            e.printStackTrace();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
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
*/
                //Log.d(String.valueOf(this),  email + "," + email2 + "," + nickname + "," + pass + "," + passch + "," + addr );
/*                if(){

                } else if(){

                }*/

            }//onClick
        });//btnJoin

        return rootView;
    }


/*
    //ACCESS_NETWORK_STATE와 ACCESS_WIFI_STATE은 꼭 넣어야 MAP API사용가능
    //CAMERA가 있어야 카메라 사용가능
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(getContext(), permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions[0])) {

                Toast.makeText(getContext(), "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

 */
}