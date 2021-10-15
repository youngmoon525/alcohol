package com.alcohol.finalalcohol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.alcohol.finalalcohol.Chat.ChatActivity;
import com.alcohol.finalalcohol.bottomnavi.FragmentSubs;
import com.alcohol.finalalcohol.bottomnavi.FragmentMy;
import com.alcohol.finalalcohol.bottomnavi.Todays_Alcohol_Fragment;
import com.alcohol.finalalcohol.bottomnavi.Todays_Control_Fragment;
import com.alcohol.finalalcohol.bottomnavi.Todays_Test_Fragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main:MainActivity :";

    //바텀네비게이션 프래그먼트들
    FragmentSubs fragmentSubs;
    FragmentMy fragmentMy;
    BottomNavigationView bottomNavigationView;

    //상단 탭 툴바 및 프래그먼트들
    Toolbar toolbar;
    TabLayout tabs;
    Todays_Alcohol_Fragment todays_alcohol_fragment;
    Todays_Test_Fragment todays_test_fragment;
    Todays_Control_Fragment todays_control_fragment;
    Fragment selFragment = null;
    //Fragment1, Fragment2, Fragment3은 모두 Fragment을 상속받았으므로,
    //selFragment에 fragment1, fragment2, fragment3 을 담아서 쓸 수 있다.
    AppBarLayout appbarlayout;

    //메인플로팅버튼들
    FloatingActionButton fabMain;
    FloatingActionButton fabchat;
    FloatingActionButton fabsupport;
    // 플로팅버튼 상태
    private boolean fabMain_status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAppKeyHash();


        //메인플로팅버튼 찾아놓기
        fabMain = findViewById(R.id.fabMain);
        fabchat = findViewById(R.id.fabchat);
        fabsupport = findViewById(R.id.fabsupport);

        //상단탭 프래그먼트들
        todays_alcohol_fragment = new Todays_Alcohol_Fragment();//프래그먼트객체생성
        todays_test_fragment = new Todays_Test_Fragment();
        todays_control_fragment = new Todays_Control_Fragment();

        //내가 만든 툴바를 액션바로 지정한다
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//setSupportActionBar()에 내가 만든 toolbar로 지정

        //기존 타이틀 글자가 보이지 않게 만들기
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);//기존의 타이틀글자 있는 액션바가 안 보이도록 설정

        //appbarlayout 앱바레이아웃 찾기
        appbarlayout = findViewById(R.id.appbarlayout);

        //하단탭 프래그먼트들
        fragmentSubs = new FragmentSubs();
        fragmentMy = new FragmentMy();

        //초기화 : 맨처음 창 열었을 때 열려있을 프래그먼트 1개를 초기화해서 미리 붙여놓기
        getSupportFragmentManager().beginTransaction().replace(R.id.contain, todays_test_fragment).commit();

        //상단탭 생성
        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("오늘의 술 추천"));//인덱스 : 0
        tabs.addTab(tabs.newTab().setText("내 술 정보"));//인덱스 : 1
        tabs.addTab(tabs.newTab().setText("술장고컨트롤러"));//인덱스 : 2

        //플로팅버튼클릭시
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFab();
                //Toast.makeText(MainActivity.this, "버튼1클릭", Toast.LENGTH_SHORT).show();
            }
        });
        fabchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "버튼2클릭", Toast.LENGTH_SHORT).show();
                //
/*                Intent intent = new Intent(MainActivity.this, ChatActivity.class);//채팅액티비티로 전환
                startActivity(intent);//채팅액티비티*/
                //상담사으로서 채팅 입장
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra("userName", "회원1");//데이터넘기기
                intent.putExtra("chat_name", "상담사");//데이터넘기기
                startActivity(intent);
            }
        });
        fabsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "버튼3클릭", Toast.LENGTH_SHORT).show();
                //
                /*Intent intent = new Intent(MainActivity.this, ChatActivity.class);//챗봇액티비티로 전환
                startActivity(intent);//채팅액티비티*/
                //회원으로서 채팅 입장
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra("userName", "상담사");//데이터넘기기
                intent.putExtra("chat_name", "회원1");//데이터넘기기
                startActivity(intent);
            }
        });



        //탭 레이아웃에 선택리스너를 달아준다.
        //setOnTabSelectedListener에서 탭화면을 변화시킬 수 있다.
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //탭을 선택했을 때 사용
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // tab이라는 변수로 그 선택된 tab이 넘어온다.
                // 선택된 탭의 인덱스를 가져오기
                int postion = tab.getPosition();

                //겟포지션~ .getPosition선택한 인덱스를 가져오는 기능!
                Log.d(TAG, "onTabSelected: " + postion);

                if(postion == 0){
                    //getSupportFragmentManager().beginTransaction().replace(R.id.contain, fragment1).commit();
                    //위처럼 일일이 안 하려고 selFragment 쓴다.
                    selFragment = todays_test_fragment;
                } else if(postion == 1) {
                    selFragment = todays_alcohol_fragment;
                } else if(postion == 2) {
                    selFragment = todays_control_fragment;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.contain, selFragment).commit();

            }

            //탭의 선택되지 않았을 때 사용 : 잘 안씀.
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            //탭이 재선택됐을 때 사용 : 잘 안씀.
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });


        bottomNavigationView = findViewById(R.id.bottom_navi);
        //.setOnItemSelectedListener은 내가 선택한 메뉴 아이템 리스너
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //내가 선택한 메뉴아이템의 int id가 매개변수로 온다.
                switch (item.getItemId()){
                    case R.id.tab1:
                        toolbar.setVisibility(View.VISIBLE);
                        //appbarlayout.setVisibility(View.VISIBLE);
                        //초기화
                        getSupportFragmentManager().beginTransaction().replace(R.id.contain, todays_test_fragment).commit();
                        break;

                    case R.id.tab2:
                        toolbar.setVisibility(View.GONE);
                        //appbarlayout.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.contain, fragmentSubs).commit();
                        //Toast.makeText(getApplicationContext(), "프래그먼트2입니다", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.tab3:
                        toolbar.setVisibility(View.GONE);
                        //appbarlayout.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.contain, fragmentMy).commit();
                        //Toast.makeText(getApplicationContext(), "프래그먼트3입니다", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });
    }
    //플로팅버튼 애니메이션
    // 플로팅 액션 버튼 클릭시 애니메이션 효과
    public void toggleFab() {
        if(fabMain_status == true) {
            // 플로팅 액션 버튼 닫기
            // 애니메이션 추가
            ObjectAnimator fe_animation = ObjectAnimator.ofFloat(fabchat, "translationY", 0f);
            fe_animation.start();
            ObjectAnimator fc_animation = ObjectAnimator.ofFloat(fabsupport, "translationY", 0f);
            fc_animation.start();
            // 메인 플로팅 이미지 변경
            fabMain.setImageResource(R.drawable.inquiry);

        }else if(fabMain_status == false) {
            // 플로팅 액션 버튼 열기
            ObjectAnimator fe_animation = ObjectAnimator.ofFloat(fabchat, "translationY", -200f);
            fe_animation.start();
            ObjectAnimator fc_animation = ObjectAnimator.ofFloat(fabsupport, "translationY", -400f);
            fc_animation.start();
            // 메인 플로팅 이미지 변경
            fabMain.setImageResource(R.drawable.downarrow);
        }
        // 플로팅 버튼 상태 변경
        fabMain_status = !fabMain_status;
    }



    private void getAppKeyHash(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures){
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(),0));
                Log.e("Hash key", something);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
}

