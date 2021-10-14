package com.alcohol.finalalcohol.MySubsDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alcohol.finalalcohol.DTO.My_SubsDetail_DTO;
import com.alcohol.finalalcohol.R;

import java.util.ArrayList;

public class mySubsDetailAdapter extends RecyclerView.Adapter<mySubsDetailAdapter.ViewHolder> implements mySubsDetailListener {
    mySubsDetailListener listener;

    //넘겨받는 데이터
    ArrayList<My_SubsDetail_DTO> mySubsList;
    Context context;
    LayoutInflater inflater;

    //생성자
    public mySubsDetailAdapter(ArrayList<My_SubsDetail_DTO> mySubsList, Context context) {
        this.mySubsList = mySubsList;
        this.context = context;

        inflater = LayoutInflater.from(this.context);
    }
//////////////////임플리먼트 메소드 및 생성자 메소드/////////////////
    //화면 인플레이트
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.intent_my_subsdetail_view, parent, false);
        return new ViewHolder(itemview, listener);
    }

    //인플레이트한 화면에 데이터 세팅
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        My_SubsDetail_DTO dto = mySubsList.get(position);
        holder.setDTO(dto);

        holder.linearParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    // ArrayList<My_SubsDetail_DTO> 에 dto 를 추가하는 메소드
    public void addDto(My_SubsDetail_DTO dto){
        mySubsList.add(dto);
        //Log.d("main", "addDTO: getMonth() : " + dto.getMonth());
    }

    @Override
    public int getItemCount() {
        return mySubsList.size();
    }

    //mySubsDetail 에서 dto를 요청할 경우 사용
    public My_SubsDetail_DTO getItem(int position){
        return mySubsList.get(position);
    }
    //mySubsDetail 에서 클릭시 어댑터에서 선언한 리스너로 연결
    public void setOnItemClickListener(mySubsDetailListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }
//////////////////////////////////////////////////////////////////////////


    public class ViewHolder extends RecyclerView.ViewHolder{
        //view 에서 사용할 요소를 선언
        TextView tvMonth, tvAl_List, tvPrice_State;
        LinearLayout linearParent;

        public ViewHolder(@NonNull View itemView, mySubsDetailListener listener) {
            super(itemView);
            //ViewHolder 에서 선언한 요소를 찾음
            tvMonth = itemView.findViewById(R.id.tvMonth);
            tvAl_List = itemView.findViewById(R.id.tvAl_List);
            tvPrice_State = itemView.findViewById(R.id.tvPrice_State);
            linearParent = itemView.findViewById(R.id.linearParent);


            //해당 view 의 클릭리스너 생성
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }

                }
            });


        }

    //view 에 데이터 셋팅
    public void setDTO(My_SubsDetail_DTO dto){
        tvMonth.setText(dto.getMonth());
        tvAl_List.setText(dto.getAl_List());
        tvPrice_State.setText(dto.getPrice_state());

    }



    }//ViewHolder

}
