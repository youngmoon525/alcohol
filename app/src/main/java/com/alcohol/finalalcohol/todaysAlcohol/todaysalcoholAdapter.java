package com.alcohol.finalalcohol.todaysAlcohol;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alcohol.finalalcohol.Common.CommonMethod;
import com.alcohol.finalalcohol.Dto.al_info_tbVO;
import com.alcohol.finalalcohol.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class todaysalcoholAdapter extends RecyclerView.Adapter<todaysalcoholAdapter.ViewHolder> {
        private static final String TAG = "todaysalcoholAdapter : ";

        //ArrayList images;
        Context context;
        List<al_info_tbVO> allist;

 public todaysalcoholAdapter(){}

public todaysalcoholAdapter(Context context, List<al_info_tbVO> allist) {
        this.context = context;
        //this.images = images;
        this.allist = allist;
    Log.d(TAG, "todaysalcoholAdapter: "+allist.get(0).getAl_id());
        }



@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todays_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
        }

// Binding data to the into specified position
@Override
public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            /*fridge_info_tb_dto = fridge_info_tb_dtos.get(position);
            al_info_tb_dto = al_info_tb_dtos.get(position);*/
            //Log.d(TAG, "onBindViewHolder: "+ allist.get(position).getAl_imgpath());
            //int res = Integer.parseInt(CommonMethod.ipConfig + "/resources/" + allist.get(position).getAl_imgpath());
            //String res = CommonMethod.ipConfig + "/resources/" + allist.get(position).getAl_imgpath();
            //holder.images.setImageURI(res);

            /*int res = Integer.parseInt(CommonMethod.ipConfig + "/resources/" + allist.get(position).getAl_imgpath());
            holder.images.setImageResource(res);*/

            //String imgpath = CommonMethod.ipConfig + "/resources/"  + allist.get(position).getAl_imgpath();
            String imgpath = allist.get(position).getAl_imgpath();
            Log.d(TAG, "alcohol_img_input: 파일패스:" + imgpath);
            Glide.with(context).load(imgpath).into(holder.images);

/*            int res = (int) images.get(position);
            holder.images.setImageDrawable();*/


            holder.images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //커스텀다이알로그 생성
                    //todaysCustomDialog dialog = new todaysCustomDialog(context);

                    Log.d(TAG, "onClick: 번호 :"+position+
                            "\nonClick: fridge_info_tb_dto.getFridge_position() :"+allist.get(position).getAl_name());
                    //dialog.callFunction(fridge_info_tb_dtos.get(position), al_info_tb_dtos.get(position));

                    //다이알로그호출

                    OptionCodeTypeDialog octDialog = new OptionCodeTypeDialog(context);
                    // 아래 코드를 꼭 적어준다


                    octDialog.setCanceledOnTouchOutside(true);
                    octDialog.setCancelable(true);
                    //octDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                    octDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    //술이름넣기
                   // octDialog.option_codetype_dialog_title_tv_input(allist.get(position).getAl_name());//술이름넣기

                    //술정보
                    //octDialog.alcohol_text_input(allist.get(position));//술정보넣기

                    //이미지넣기
                    /*octDialog.alcohol_img_input(allist.get(position).getAl_name());*/

                    octDialog.show();


                    //int res = (int) images.get(position);
                    //String filepath = CommonMethod.ipConfig + "/resources/"  + dto.getAl_imgpath();
                    //Log.d(TAG, "onBindViewHolder: 이미지 파일패스:" + filepath);
                    //holder.images.setImageResource(res);
                    //Glide.with(context).load(filepath).fitCenter().into(alcohol_img);

                    octDialog.option_codetype_dialog_title_tv_input(allist.get(position).getAl_name());
                    octDialog.alcohol_text_input(allist.get(position));
                    octDialog.alcohol_img_input(allist.get(position));
                    octDialog.prev_click();
                    //octDialog.shared_click(allist.get(position));
                    octDialog.shared_click(allist.get(position));
                    /*
                    .setCanceledOnTouchOutside(true);  다이알로그 밖에 터치했을 때 다이알로그가 꺼짐.
                       .setCancelable(true); 다이알로그 취소 가능. ( back버튼 )
                    */
                }
            });

            //holder.fridge_info_tb_dto = fridge_info_tb_dtos.get(position);
            /*holder.fridge_info_tb_dto.setFridge_al_id(fridge_info_tb_dto.getFridge_al_id());
            holder.fridge_info_tb_dto.setFridge_mem_id(fridge_info_tb_dto.getFridge_mem_id());
            holder.fridge_info_tb_dto.setFridge_position(fridge_info_tb_dto.getFridge_position());
            */

        }

@Override
public int getItemCount() {
        return allist.size();
        }

// Initializing the Views
public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView images;

    public ViewHolder(View view) {
        super(view);
        images = (ImageView) view.findViewById(R.id.imageView);
    }
}

}
