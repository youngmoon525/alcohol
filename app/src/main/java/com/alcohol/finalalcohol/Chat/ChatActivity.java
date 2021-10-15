package com.alcohol.finalalcohol.Chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alcohol.finalalcohol.Dto.ChatDTO;
import com.alcohol.finalalcohol.MainActivity;
import com.alcohol.finalalcohol.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "main:ChatActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatDTO> chatDTOList;
    private String userName ;
    private String chat_name;
    private EditText edt_chat;
    private ImageButton btn_send;
    private ImageView prev;
    private TextView titletv;

    private DatabaseReference myRef;
    private DatabaseReference toRef;
    private int cnt, cntscroll = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room2);
        checkDangerousPermissions();
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");//접속한사람
        chat_name = intent.getStringExtra("chat_name");//상대방

        btn_send = findViewById(R.id.btn_send);
        edt_chat = findViewById(R.id.edt_chat);

        prev = findViewById(R.id.prev);
        titletv = findViewById(R.id.titletv);

        //뒤로가기아이콘누를시 채팅상담 종료
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(userName.equals("상담사")){
            titletv.setText("고객센터 채팅상담(상담사)");
        } else {
            titletv.setText("고객센터 채팅상담(고객)");
        }

        //채팅입력창에 엔터칠 시 이벤트 : 없앰
/*        edt_chat.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                    String msg = edt_chat.getText().toString().trim();
                    if (msg.trim() != null && !msg.equals(" ") && !msg.equals("")) {
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_ENTER:
                                ChatDTO dto = new ChatDTO();
                                dto.setNickname(userName);
                                msg.trim();
                                dto.setMsg(msg);
                                long now = System.currentTimeMillis();
                                Date mDate = new Date(now);
                                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm");
                                String getTime = simpleDate.format(mDate);
                                dto.setDate(getTime);
                                dto.setSelected("대화");
                                myRef.push().setValue(dto);
                                toRef.push().setValue(dto);
                                edt_chat.setText("");
                        }
                    }
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            finish();
                    }
                    return true;
            }
        });*/

    //메시지보내기누를때
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = edt_chat.getText().toString().trim();
                if (msg != null || !msg.equals(" ") || !msg.equals("")){
                    ChatDTO dto = new ChatDTO();
                    dto.setNickname(userName);
                    dto.setMsg(msg);
                    long now = System.currentTimeMillis();
                    Date mDate = new Date(now);
                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm");
                    String getTime = simpleDate.format(mDate);
                    dto.setDate(getTime);
                    dto.setSelected("대화");
                    myRef.push().setValue(dto);
                    toRef.push().setValue(dto);
                    edt_chat.setText("");
                }
            }
        });

        mRecyclerView = findViewById(R.id.rcview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        chatDTOList = new ArrayList<>();
        mAdapter = new ChatAdapter(chatDTOList , ChatActivity.this , userName, chat_name);
        mRecyclerView.setAdapter(mAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(userName).child(chat_name);
        toRef = database.getReference(chat_name).child(userName);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

      /*  ChatDTO dto = new ChatDTO();
        dto.setNickname(nick);
        dto.setMsg("hi");
        myRef.setValue(dto);*/

        if(!userName.equals("상담사") && cnt == 0){
            bot1();
        }
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChatDTO dto = snapshot.getValue(ChatDTO.class);
                ((ChatAdapter) mAdapter).addChat(dto);
                mRecyclerView.smoothScrollToPosition(cntscroll++);
                hideKeyboard();//키보드내리기
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void bot1(){
        ChatDTO dto = new ChatDTO();
        dto.setNickname("술장고봇");
        dto.setMsg("안녕하세요. 술장고봇입니다.\n문의사항을 선택하세요.");
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm");
        String getTime = simpleDate.format(mDate);
        dto.setDate(getTime);
        dto.setSelected("처음");
        myRef.push().setValue(dto);
        toRef.push().setValue(dto);
        Log.d(TAG, "bot1: "+dto.getNickname());
        Log.d(TAG, "bot1: "+dto.getMsg());
        Log.d(TAG, "bot1: "+dto.getDate());
        Log.d(TAG, "bot1: "+dto.getSelected());
        Log.d(TAG, "bot1: "+myRef);
        Log.d(TAG, "bot1: "+toRef);
        cnt++;
    }

    public void selectbot1(String textbot,String userName,String chat_name){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(userName).child(chat_name);
        toRef = database.getReference(chat_name).child(userName);

        Log.d(TAG, "selectbot1: 값넘김"+textbot);
        ChatDTO dto = new ChatDTO();
        dto.setNickname("술장고봇");
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm");
        String getTime = simpleDate.format(mDate);
        dto.setDate(getTime);
        String msg1 = "";
        if(textbot.equals("이용안내")){
            msg1 = "술장고는 사용자의 취향에 알맞는 전통주을 추천해주는 서비스입니다.";
        } else if(textbot.equals("제품정보")){
            msg1 = "술장고는 현재 6칸으로 이루어진 미니 술 냉장고가 알고리즘에 의해 회전을 통해 술을 추천해주는 서비스를 진행하고 있습니다.";
        } else if(textbot.equals("결제방법")){
            msg1 = "서비스 초기단계로 인해 카드결제만 제공하고 있습니다.";
        } else if(textbot.equals("환불방법")){
            msg1 = "환불관련하여 안내해드립니다. ";
        } else if(textbot.equals("술장고A/S")){
            msg1 = "술장고A/S시 010-0000-0000로 연락주시기 바랍니다.";
        } else if(textbot.equals("배송지변경")){
            msg1 = "배송지변경은 추후 안내해드리겠습니다.";
        } else if(textbot.equals("고객서비스상담사채팅")) {
            msg1 = "상담사와 연결중입니다. 잠시만 기다려주십시오.";
        } else {
            msg1 = "잘못된 요청입니다.";
        }
        dto.setMsg(msg1);
        dto.setSelected(textbot);
        Log.d(TAG, "selectbot1: "+dto.getNickname());
        Log.d(TAG, "selectbot1: "+dto.getMsg());
        Log.d(TAG, "selectbot1: "+dto.getDate());
        Log.d(TAG, "selectbot1: "+dto.getSelected());
        Log.d(TAG, "selectbot1: "+myRef);
        Log.d(TAG, "selectbot1: "+toRef);

        myRef.push().setValue(dto);
        toRef.push().setValue(dto);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        hideKeyboard();
        return super.dispatchTouchEvent(ev);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}