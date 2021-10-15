package com.alcohol.finalalcohol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.alcohol.finalalcohol.Bottomnavi.FragmentSubs;
import com.alcohol.finalalcohol.Bottomnavi.FragmentMy;
import com.alcohol.finalalcohol.Bottomnavi.Todays_Alcohol_Fragment;
import com.alcohol.finalalcohol.Bottomnavi.Todays_Control_Fragment;
import com.alcohol.finalalcohol.Bottomnavi.Todays_Test_Fragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}