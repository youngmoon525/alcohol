package com.alcohol.finalalcohol.Bottomnavi;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alcohol.finalalcohol.R;

import static com.alcohol.finalalcohol.Common.CommonMethod.allist;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alcohol.finalalcohol.ATask.AlcoholSelect;

import com.alcohol.finalalcohol.todaysAlcohol.todaysalcoholAdapter;

import java.util.concurrent.ExecutionException;

public class Todays_Alcohol_Fragment extends Fragment {
    private static String TAG = "todays:Todays_Alcohol_Fragment";
    Button btnal;

    RecyclerView recyclerView;

    //접근하는 회원 id를 1로 가정
    String input_id = "1";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_alcohol_todays, container, false);


        //checkDangerousPermissions();//onCreate 안에 checkDangerousPermissions을 실행시켜줘야한다.
        //checkDangerousPermissions은 권한을 주는 메소드~!
        Log.d(TAG, "onCreate: " + 1);
        AlcoholSelect alcoholSelect = new AlcoholSelect(input_id);

        try {
            alcoholSelect.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Getting reference of recyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // Setting the layout as Staggered Grid for vertical orientation
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        // Sending reference and data to Adapter
        todaysalcoholAdapter startAdapter = new todaysalcoholAdapter(getContext(), allist);

        // Setting Adapter to RecyclerView
        recyclerView.setAdapter(startAdapter);

        /*btnal = rootView.findViewById(R.id.btnal);
        btnal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{


                Intent intent = new Intent(getActivity(), todaysalcoholActivity.class);
                startActivity(intent);
                Log.d(TAG, "onClick: intent : 버튼 클릭");
                } catch (Exception e){
                    Log.d(TAG, "onClick: " + e.getMessage() + ", : " + e.getStackTrace());
                }
            }
        });*/

        return rootView;
    }
}