package com.alcohol.finalalcohol.Common;

import com.alcohol.finalalcohol.Dto.al_info_tbVO;
import com.alcohol.finalalcohol.Dto.fridge_info_tbVO;
import com.alcohol.finalalcohol.Dto.mem_info_tbVO;

import java.util.List;

public class CommonMethod {
    //내 ip
    //public static String ipConfig = "http://221.156.48.92:8081/alcohol";
    public static String ipConfig = "http://192.168.0.12/alcohol";

    // 어느곳에서나 로그인이 되어있는지 loginDTO를 static으로 생성
    public static mem_info_tbVO loginDTO = null;
    public static List<fridge_info_tbVO> fridgelist = null;
    public static List<al_info_tbVO> allist = null;
    public static al_info_tbVO aldto = null;
}//class
