package com.alcohol.finalalcohol.Chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alcohol.finalalcohol.Dto.ChatDTO;
import com.alcohol.finalalcohol.MainActivity;
import com.alcohol.finalalcohol.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{
    private List<ChatDTO> mDataset;
    private String myNickName;
    ChatActivity chatActivity = new ChatActivity();
    private String chat_name;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txt_nickName , txt_msg , txt_date;
        public TextView  txt_msg2 , txt_date2;
        public CircleImageView imgv;
        public LinearLayout ln1 ;
/*        public ImageView down_arrow , down_arrow2;*/
        public View rootView;
        private LinearLayout Linearmenu1, Linearmenu2;
        private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;


        public MyViewHolder(View v, ChatAdapter listener){
            super(v);

            txt_msg = v.findViewById(R.id.txt_msg);
            txt_nickName = v.findViewById(R.id.txt_nickname);
            txt_date = v.findViewById(R.id.txt_date);
/*            ln1 = v.findViewById(R.id.ln1);*/
            txt_msg2 = v.findViewById(R.id.txt_msg2);
            txt_date2 = v.findViewById(R.id.txt_date2);
            imgv = v.findViewById(R.id.imgv);
/*          down_arrow = v.findViewById(R.id.down_arrow);
            down_arrow2 = v.findViewById(R.id.down_arrow2);*/
            Linearmenu1 = v.findViewById(R.id.Linearmenu1);
            Linearmenu2 = v.findViewById(R.id.Linearmenu2);
            textView1 = v.findViewById(R.id.textView1);
            textView2 = v.findViewById(R.id.textView2);
            textView3 = v.findViewById(R.id.textView3);
            textView4 = v.findViewById(R.id.textView4);
            textView5 = v.findViewById(R.id.textView5);
            textView6 = v.findViewById(R.id.textView6);
            textView7 = v.findViewById(R.id.textView7);

            v.setClickable(true);
            v.setEnabled(true);
        }
    }
    public ChatAdapter(List<ChatDTO> myDataset , Context context , String myNickName, String chat_name){
        mDataset = myDataset;
        this.myNickName = myNickName;
        this.chat_name = chat_name;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat,parent , false);

        MyViewHolder vh = new MyViewHolder(v, this);
        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ChatDTO dto = mDataset.get(position);
        holder.txt_nickName.setText(dto.getNickname());
        holder.txt_msg.setText(dto.getMsg());
        holder.txt_date.setText(dto.getDate());
        holder.txt_msg2.setText(dto.getMsg());
        holder.txt_date2.setText(dto.getDate());
        if(dto.getNickname().equals("술장고봇")){
            holder.imgv.setImageResource(R.drawable.chatbot);
            holder.Linearmenu1.setVisibility(View.VISIBLE);
            holder.Linearmenu2.setVisibility(View.VISIBLE);
            if(dto.getSelected().equals("처음")) {
                holder.textView1.setVisibility(View.VISIBLE);
                holder.textView2.setVisibility(View.VISIBLE);
                holder.textView3.setVisibility(View.VISIBLE);
                holder.textView4.setVisibility(View.VISIBLE);
                holder.textView5.setVisibility(View.VISIBLE);
                holder.textView6.setVisibility(View.VISIBLE);
                holder.textView7.setVisibility(View.VISIBLE);
                holder.txt_msg.setVisibility(View.VISIBLE);
            } else {
                holder.textView1.setVisibility(View.GONE);
                holder.textView2.setVisibility(View.GONE);
                holder.textView3.setVisibility(View.GONE);
                holder.textView4.setVisibility(View.GONE);
                holder.textView5.setVisibility(View.GONE);
                holder.textView6.setVisibility(View.GONE);
                holder.textView7.setVisibility(View.GONE);
                holder.txt_msg.setVisibility(View.GONE);
            }
            holder.txt_msg.setVisibility(View.VISIBLE);
            holder.imgv.setVisibility(View.VISIBLE);
            holder.txt_date.setVisibility(View.GONE);
            holder.txt_nickName.setVisibility(View.VISIBLE);

            holder.txt_msg2.setVisibility(View.GONE);
            holder.txt_date2.setVisibility(View.GONE);

            //리스너 : 온클릭리스너 :
            holder.textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chatActivity.selectbot1("이용안내",myNickName,chat_name);
                    //7. 메인에서 요구한 position에 따른 dto 반환 메소드
                    //아이템을 클릭했을 때 내가 클릭한 포지션을 리스너에게 넘겨준다.
                    //화면 만들었을 때 이벤트 걸어주기.
/*                    holder.txt_msg3.setVisibility(View.VISIBLE);
                    holder.txt_msg3.setText("이용 안내");
                    long now = System.currentTimeMillis();
                    Date mDate = new Date(now);
                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm");
                    String getTime = simpleDate.format(mDate);
                    holder.txt_date3.setVisibility(View.VISIBLE);
                    holder.txt_date3.setText(getTime);
                    holder.txt_msg3.setVisibility(View.VISIBLE);
                    holder.txt_msg3.setText("술장고는 사용자의 취향에 알맞는 전통주을 추천해주는 서비스입니다.");*/
                }
            });
            holder.textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chatActivity.selectbot1("제품정보",myNickName,chat_name);
/*                    holder.txt_msg3.setVisibility(View.VISIBLE);
                    holder.txt_msg3.setText("제품 정보");
                    long now = System.currentTimeMillis();
                    Date mDate = new Date(now);
                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm");
                    String getTime = simpleDate.format(mDate);
                    holder.txt_date3.setVisibility(View.VISIBLE);
                    holder.txt_date3.setText(getTime);
                    holder.txt_msg3.setVisibility(View.VISIBLE);
                    holder.txt_msg3.setText("술장고는 현재 6칸으로 이루어진 미니 술 냉장고가 알고리즘에 의해 회전을 통해 술을 추천해주는 서비스를 진행하고 있습니다.");*/
                }
            });
            holder.textView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chatActivity.selectbot1("결제방법",myNickName,chat_name);
                }
            });
            holder.textView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chatActivity.selectbot1("환불방법",myNickName,chat_name);
                }
            });
            holder.textView5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chatActivity.selectbot1("술장고A/S",myNickName,chat_name);
                }
            });
            holder.textView6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chatActivity.selectbot1("배송지변경",myNickName,chat_name);
                }
            });
            holder.textView7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chatActivity.selectbot1("고객서비스상담사채팅",myNickName,chat_name);
                }
            });

        }else if(dto.getNickname().equals(myNickName)){
            holder.txt_msg.setVisibility(View.GONE);
            holder.imgv.setVisibility(View.GONE);
            holder.txt_date.setVisibility(View.GONE);
            holder.txt_nickName.setVisibility(View.GONE);
            /*            holder.down_arrow.setVisibility(View.GONE);*/
            holder.txt_msg2.setVisibility(View.VISIBLE);
            holder.txt_date2.setVisibility(View.VISIBLE);
            /*            holder.down_arrow2.setVisibility(View.VISIBLE);*/
            holder.txt_msg2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            holder.txt_date2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
/*            LinearLayout ln11 =  (LinearLayout) holder.ln1.getParent();
            LinearLayout ln12 =  (LinearLayout) holder.ln1;*/
            /*            ln11. setGravity(Gravity.LEFT);*/
            //holder.txt_msg2.setBackgroundColor(Color.WHITE);
            holder.Linearmenu1.setVisibility(View.GONE);
            holder.Linearmenu2.setVisibility(View.GONE);
            holder.textView1.setVisibility(View.GONE);
            holder.textView2.setVisibility(View.GONE);
            holder.textView3.setVisibility(View.GONE);
            holder.textView4.setVisibility(View.GONE);
            holder.textView5.setVisibility(View.GONE);
            holder.textView6.setVisibility(View.GONE);
            holder.textView7.setVisibility(View.GONE);

        }else{
            if(!myNickName.equals("상담사")){
                holder.imgv.setImageResource(R.drawable.supporter);
            } else {
                holder.imgv.setImageResource(R.drawable.customer);
            }
            holder.imgv.setVisibility(View.VISIBLE);
            holder.txt_msg.setVisibility(View.VISIBLE);
            holder.txt_date.setVisibility(View.VISIBLE);
            holder.txt_nickName.setVisibility(View.VISIBLE);
            holder.txt_msg.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            holder.txt_nickName.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            holder.txt_date.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            holder.txt_msg2.setVisibility(View.GONE);
            holder.txt_date2.setVisibility(View.GONE);
/*            LinearLayout ln11 =  (LinearLayout) holder.ln1.getParent();
            LinearLayout ln12 =  (LinearLayout) holder.ln1;*/
            /*            ln11.setGravity(Gravity.RIGHT);*/
            //holder.txt_msg.setBackgroundColor(Color.YELLOW);
            holder.Linearmenu1.setVisibility(View.GONE);
            holder.Linearmenu2.setVisibility(View.GONE);
            holder.textView1.setVisibility(View.GONE);
            holder.textView2.setVisibility(View.GONE);
            holder.textView3.setVisibility(View.GONE);
            holder.textView4.setVisibility(View.GONE);
            holder.textView5.setVisibility(View.GONE);
            holder.textView6.setVisibility(View.GONE);
            holder.textView7.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset==null ? 0: mDataset.size();
    }
    public ChatDTO getChat(int position){
        return mDataset != null ? mDataset.get(position) : null;
    }
    public void addChat(ChatDTO dto){
        mDataset.add(dto);
        notifyItemInserted(mDataset.size()-1);
    }

}