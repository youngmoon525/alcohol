package com.alcohol.finalalcohol.todaysAlcohol;

import android.content.ActivityNotFoundException;
import com.kakao.sdk.common.util.KakaoCustomTabsClient;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.link.LinkClient;
import com.kakao.sdk.link.WebSharerClient;
import com.kakao.sdk.link.model.LinkResult;
import com.kakao.sdk.template.model.Button;
import com.kakao.sdk.template.model.Content;
import com.kakao.sdk.template.model.FeedTemplate;
import com.kakao.sdk.template.model.ItemContent;
import com.kakao.sdk.template.model.ItemInfo;
import com.kakao.sdk.template.model.Link;
import com.kakao.sdk.template.model.Social;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alcohol.finalalcohol.Common.CommonMethod;
import com.alcohol.finalalcohol.Dto.al_info_tbVO;
import com.alcohol.finalalcohol.R;
import com.bumptech.glide.Glide;


public class OptionCodeTypeDialog extends Dialog {
    private static final String TAG = "OptionCodeTypeDialog : ";

    //TextView textView;
    //public KakaoLink kakaoLink;
    private Context context;
    //private CustomDialogClickListener customDialogClickListener;
    private TextView option_codetype_dialog_title_tv, option_codetype_dialog_negative, alcohol_text;
    private ImageView alcohol_img, prev, shared;
    private al_info_tbVO aldto;
    todaysalcoholAdapter adapter = new todaysalcoholAdapter();

    /*public OptionCodeTypeDialog(@NonNull Context context, CustomDialogClickListener customDialogClickListener
    ) {
        super(context);
        this.context = context;
        this.customDialogClickListener = customDialogClickListener;
        setContentView(R.layout.todays_custom_dialog);
        alcohol_img = findViewById(R.id.alcohol_img);//술 이미지뷰
        option_codetype_dialog_title_tv = findViewById(R.id.option_codetype_dialog_title_tv);//술 이름
        prev = findViewById(R.id.prev);//이전 누르기
        shared = findViewById(R.id.shared);//카카오톡공유하기 누르기
        alcohol_text = findViewById(R.id.alcohol_text);//해당  술의 정보가 나올 예정인 텍스트뷰
        option_codetype_dialog_negative = findViewById(R.id.option_codetype_dialog_negative);//술 취소버튼
    }*/
    public OptionCodeTypeDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.todays_custom_dialog);
        this.context = context;
//        this.customDialogClickListener = customDialogClickListener;
        //this.aldto = aldto;
        alcohol_img = findViewById(R.id.alcohol_img);//술 이미지뷰
        option_codetype_dialog_title_tv = findViewById(R.id.option_codetype_dialog_title_tv);//술 이름
        prev = findViewById(R.id.prev);//이전 누르기
        shared = findViewById(R.id.shared);//카카오톡공유하기 누르기
        alcohol_text = findViewById(R.id.alcohol_text);//해당  술의 정보가 나올 예정인 텍스트뷰
        option_codetype_dialog_negative = findViewById(R.id.option_codetype_dialog_negative);//술 취소버튼
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todays_custom_dialog);

        KakaoSdk.init(getContext(),"f172a490a62149fd61b48189584b22cb");//카카오링크
        alcohol_img = findViewById(R.id.alcohol_img);//술 이미지뷰
        option_codetype_dialog_title_tv = findViewById(R.id.option_codetype_dialog_title_tv);//술
        prev = findViewById(R.id.prev);//이전 누르기
        shared = findViewById(R.id.shared);//카카오톡공유하기 누르기
        alcohol_text = findViewById(R.id.alcohol_text);//해당  술의 정보가 나올 예정인 텍스트뷰
        option_codetype_dialog_negative = findViewById(R.id.option_codetype_dialog_negative);//술 취소버튼
/*        this.customDialogClickListener.onDatafridge(fridge_info_tb_dtos);
        this.customDialogClickListener.onDatal(al_info_tb_dtos);*/
        //int res = (int) images.get(position);
        //holder.images.setImageResource(res);
        //alcohol_text.setText(al_info_tb_dtos.get(0).getAl_id());
        //cohol_img.setImageResource(al_info_tb_dtos.get(0).getAl_imgpath().);

       /* tvPositive.setOnClickListener(v -> {
            // 저장버튼 클릭
            this.customDialogClickListener.onPositiveClick();
            dismiss();
        });*/
        option_codetype_dialog_negative.setOnClickListener(v -> {
            // 취소버튼 클릭
            //this.customDialogClickListener.onNegativeClick();
            dismiss();
        });
    }

    public void alcohol_img_input(al_info_tbVO dto){
/*      int res = (int) images.get(dto.getAl_mini_imgpath());
        alcohol_img.setImageResource(res);*/
        alcohol_img = findViewById(R.id.alcohol_img);
        //alcohol_img.setImageResource();
        //String filepath = CommonMethod.ipConfig + "/resources/"  + dto.getAl_imgpath() + dto.getAl_imgname();
        //String filepath = CommonMethod.ipConfig + "/resources/"  + dto.getAl_imgpath();
        String filepath = dto.getAl_imgpath();
        Log.d(TAG, "alcohol_img_input: 파일패스:" + filepath);
        Glide.with(getContext()).load(filepath).fitCenter().into(alcohol_img);
        //술 이미지넣기
    }
    public void option_codetype_dialog_title_tv_input(String name){
        option_codetype_dialog_title_tv = findViewById(R.id.option_codetype_dialog_title_tv);
        //술 이름 넣는 곳
        option_codetype_dialog_title_tv.setText(name);
    }
    public void prev_click(){
        findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        //이전버튼클릭시
    }
    public void shared_click(al_info_tbVO aldto){
        //카카오톡공유하기클릭시
        findViewById(R.id.shared).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 파라미터 설정
                //kakao{YOUR_NATIVE_APP_KEY}://kakaolink${androidExecutionParams}



                testAndroid(aldto);


                /*어싱크로 해야하는지 알았는데 아닌가봄... 다르게 해봄
                Log.d(TAG, "onClick: 카톡공유클릭됨");
                //kakao_shared
                kakaosharedATask kakaosharedATask = new kakaosharedATask(al_id);

                try {
                    kakaosharedATask.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

            }
        });

    }
    public void alcohol_text_input(al_info_tbVO dto){
        alcohol_text = findViewById(R.id.alcohol_text);
        //술 정보 넣는 곳
        String altype = "";
        if(dto.getAl_alcohol_type() == 4){
            altype = "탁주";
        } else if(dto.getAl_alcohol_type() ==5){
            altype = "리퀴르";
        } else if(dto.getAl_alcohol_type() == 6){
            altype = "약주";
        } else if(dto.getAl_alcohol_type() == 7){
            altype = "소주";
        } else if(dto.getAl_alcohol_type() == 8){
            altype = "과일주";
        }
        String flavor = "";
        if(dto.getAl_flavor() == 9){
            flavor = "단맛";
        } else if(dto.getAl_flavor() ==10){
            flavor = "신맛";
        } else if(dto.getAl_flavor() ==11){
            flavor = "쓴맛";
        } else if(dto.getAl_flavor() ==12){
            flavor = "짠맛";
        } else if(dto.getAl_flavor() ==13){
            flavor = "화한맛";
        }
        String smell = "";
        if(dto.getAl_smell() == 14){
            smell = "과일향";
        } else if(dto.getAl_smell() ==15){
            smell = "나무향";
        } else if(dto.getAl_smell() ==16){
            smell = "곡물향";
        } else if(dto.getAl_smell() ==17){
            smell = "기타 식물향";
        }
        String body = "";
        if(dto.getAl_body() == 1){
            body = "가벼움";
        } else if(dto.getAl_body() ==2){
            body = "중간";
        } else if(dto.getAl_body() ==3){
            body = "무거움";
        }
        alcohol_text.setText("주종 : "+altype+"\n용량 : "+dto.getAl_vol()+"\n도수 : "+dto.getAl_real_alcohol_bv()
                +"\n맛 : "+flavor+"\n향 : "+smell+"\n바디감 : "+body);

/*        al_manufacturer            VARCHAR2(500),                --해당 술의 제조사/제조원
        al_material                VARCHAR2(500),                --해당 술의 주원료
        al_vol                    VARCHAR2(500),                --해당 술의  용량 ex) 500ml
        al_body                   number,                --  mst_code에서 해당되는 바디취향 번호id넣기
        al_alcohol_type            number,                -- mst_code에서 해당되는 주종취향 번호id넣기
        al_flavor                   number,                --  mst_code에서 해당되는 맛취향 번호id넣기
        al_smell                   number,                -- mst_code에서 해당되는 향취향 번호id넣기
        al_alcohol_bv              number,                --  (취향용)mst_code에서 해당되는 도수취향 번호id넣기
        al_real_alcohol_bv         VARCHAR2(500),         --  (술정보제공용)실제 도수 작성*/
    }





    public void testWeb(al_info_tbVO aldto){
        KakaoSdk.init(getContext(),"f172a490a62149fd61b48189584b22cb");//카카오링크
        String imgpath = aldto.getAl_imgpath();

        Log.d(TAG, "testAndroid: "+imgpath);
        int flavorint = aldto.getAl_flavor();
        int smellint = aldto.getAl_smell();

        Link link = new Link("https://developers.kakao.com","https://developers.kakao.com");
        Content content = new Content(aldto.getAl_name(),
                imgpath,
                link,
                "#"+flavor_hashtag(flavorint)+" #"+smell_hashtag(smellint)
        );
        /*ItemContent itemContent = new ItemContent(
                aldto.getAl_name(),
                imgpath,
                imgpath,
                imgpath,
                "술장고"
        );*/
        ItemContent itemContent = new ItemContent(
        );
        /*List<ItemInfo> list = new ArrayList<>();
        list.add(new ItemInfo("item1","item2"));
        list.add(new ItemInfo("item3","item4"));
        ItemContent itemContent = new ItemContent(
                aldto.getAl_name(),
                imgpath,
                imgpath,
                imgpath,
                "술장고"
                ,list,
                "Total",
                "15000원"
        );*/

        Map<String, String> serverCallbackArgs = new HashMap<String, String>();
        serverCallbackArgs.put("user_id", "${current_user_id}");
        serverCallbackArgs.put("product_id", "${shared_product_id}");

        Social social = new Social(1,2,3,4,5);
        List<Button> buttons = new ArrayList<>();
        buttons.add(new Button("자세히보기",link));
        //buttons.add(new Button("3",link));
        FeedTemplate template = new FeedTemplate(content,itemContent,social,buttons,"buttonttile");


        String url = "http://192.168.0.12/alcohol/link/";


        Uri sharerUrl = WebSharerClient.getInstance().defaultTemplateUri(template);

        // CustomTabs으로 웹 브라우저 열기

        // 1. CustomTabs으로 Chrome 브라우저 열기
        try {
            KakaoCustomTabsClient.INSTANCE.openWithDefault(getContext(),sharerUrl);
        } catch( UnsupportedOperationException e) {
            // Chrome 브라우저가 없을 때 예외처리
        }

        // 2. CustomTabs으로 디바이스 기본 브라우저 열기
        try {
            KakaoCustomTabsClient.INSTANCE.open(getContext(), sharerUrl);
        } catch (ActivityNotFoundException e) {
            // 인터넷 브라우저가 없을 때 예외처리
        }
    }


    public void testAndroid(al_info_tbVO aldto){

        KakaoSdk.init(getContext(),"f172a490a62149fd61b48189584b22cb");//카카오링크

        String imgpath = aldto.getAl_imgpath();

        Log.d(TAG, "testAndroid: "+imgpath);
        int flavorint = aldto.getAl_flavor();
        int smellint = aldto.getAl_smell();

        Link link = new Link("https://developers.kakao.com","https://developers.kakao.com");
        Content content = new Content(aldto.getAl_name(),
                imgpath,
                link,
                "#"+flavor_hashtag(flavorint)+" #"+smell_hashtag(smellint)
        );
        /*ItemContent itemContent = new ItemContent(
                aldto.getAl_name(),
                imgpath,
                imgpath,
                imgpath,
                "술장고"
        );*/
        ItemContent itemContent = new ItemContent(
        );
        /*List<ItemInfo> list = new ArrayList<>();
        list.add(new ItemInfo("item1","item2"));
        list.add(new ItemInfo("item3","item4"));
        ItemContent itemContent = new ItemContent(
                aldto.getAl_name(),
                imgpath,
                imgpath,
                imgpath,
                "술장고"
                ,list,
                "Total",
                "15000원"
        );*/

        Map<String, String> serverCallbackArgs = new HashMap<String, String>();
        serverCallbackArgs.put("user_id", "${current_user_id}");
        serverCallbackArgs.put("product_id", "${shared_product_id}");

        Social social = new Social(1,2,3,4,5);
        List<Button> buttons = new ArrayList<>();
        buttons.add(new Button("자세히보기",link));
        /*buttons.add(new Button("술장고웹",link));*/
        FeedTemplate template = new FeedTemplate(content,itemContent,social,buttons,"buttonttile");

        //카카오톡 설치여부를 판단함.
        if(LinkClient.getInstance().isKakaoLinkAvailable(getContext())){

            LinkClient.getInstance().defaultTemplate(getContext(), template, new Function2<LinkResult, Throwable, Unit>() {
                @Override
                public Unit invoke(LinkResult linkResult, Throwable throwable) {

                    if (throwable != null) {
                        Log.e(TAG, "카카오링크 보내기 실패", throwable);
                    }
                    else if (linkResult != null) {
                        Log.d(TAG, "카카오링크 보내기 성공 ${linkResult.intent}");
                        context.startActivity(linkResult.getIntent());

                        // 카카오링크 보내기에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                        Log.w(TAG, "Warning Msg: ${linkResult.warningMsg}");
                        Log.w(TAG, "Argument Msg: ${linkResult.argumentMsg}");
                    }
                    return  null;
                }

            });

        }else{
            testWeb(aldto);
        }

    }

    public String flavor_hashtag(int flavorint){
        String flavor = "";
        if(flavorint == 9){
            flavor = "단맛";
        } else if(flavorint ==10){
            flavor = "신맛";
        } else if(flavorint ==11){
            flavor = "쓴맛";
        } else if(flavorint ==12){
            flavor = "짠맛";
        } else if(flavorint ==13){
            flavor = "화한맛";
        }
        return flavor;
    }

    public String smell_hashtag(int smellint){
        String smell = "";
        if(smellint == 14){
            smell = "과일향";
        } else if(smellint ==15){
            smell = "나무향";
        } else if(smellint ==16){
            smell = "곡물향";
        } else if(smellint ==17){
            smell = "기타 식물향";
        }
        return smell;
    }

}