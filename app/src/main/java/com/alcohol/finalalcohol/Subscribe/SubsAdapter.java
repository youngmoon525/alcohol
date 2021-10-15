package com.alcohol.finalalcohol.Subscribe;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import static com.alcohol.finalalcohol.Common.CommonMethod.ipConfig;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alcohol.finalalcohol.DTO.Al_Info_DTO;
import com.alcohol.finalalcohol.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SubsAdapter extends RecyclerView.Adapter<SubsAdapter.ViewHolder> {
    private static final String TAG = "main:SubsAdapter";

    ArrayList<Al_Info_DTO> dtos;
    Context context;
    LayoutInflater inflater;

    // 생성자 만들기
    public SubsAdapter(ArrayList<Al_Info_DTO> dtos, Context context){
        this.dtos = dtos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.adapter_subs, parent, false);

        return new ViewHolder(itemView, this);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        Log.d(TAG, "onBindViewHolder: " + position);

        Al_Info_DTO dto = dtos.get(position);
        holder.setDTO(dto);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, dtos.get(position).getAl_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //인플레이트한 화면을 구성한다
    public class ViewHolder extends RecyclerView.ViewHolder {
        // adapter_subs에 있는 모든 위젯을 정의한다
        TextView tv_AlName, tv_AlDetail;
        ImageView iv_alcohol;
        FrameLayout parentLayout;

        public ViewHolder(@NonNull View itemView, SubsAdapter subsAdapter) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            iv_alcohol = itemView.findViewById(R.id.iv_alcohol);
            tv_AlName = itemView.findViewById(R.id.tv_AlName);
            tv_AlDetail = itemView.findViewById(R.id.tv_AlDetail);
        }

        public void setDTO(Al_Info_DTO dto) {
            tv_AlName.setText(dto.getAl_name());
            tv_AlDetail.setText("#" + dto.getAl_flavor()+ " #" + dto.getAl_smell() +" #" + dto.getAl_real_alcohol_bv()+"도");
            if(dto.getAl_imgpath() != null){
                String filepath = ipConfig + "/app" + dto.getAl_imgpath().trim();
                Glide.with(itemView).load(filepath).override(300,300).into(iv_alcohol);
                Log.d(TAG, "setDTO: " + filepath);
            }
        }
    }

    //list의 크기, 길이 구하기
    @Override
    public int getItemCount() {
        return dtos.size();
    }

    //arrayList<SingerDTO>에 추가 메소드
    public void addDTO(Al_Info_DTO dto){
        dtos.add(dto);
    }

    // 메인에서 요구한 dto를 넘겨준다.
    public Al_Info_DTO getItem(int position){
        return dtos.get(position);
    }

}