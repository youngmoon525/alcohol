package com.alcohol.finalalcohol.Common;

<<<<<<< HEAD
import com.alcohol.finalalcohol.DTO.Al_Info_DTO;
import com.alcohol.finalalcohol.DTO.Mem_Info_DTO;
import com.alcohol.finalalcohol.DTO.My_SubsDetail_DTO;
import com.alcohol.finalalcohol.DTO.Todays_Test_DTO;

import java.util.ArrayList;
import java.util.List;

public class CommonMethod {
    // 나의 ip를 선언해 놓는다.
    public static String ipConfig = "http://192.168.0.22:80";

    //구독탭 - 구독시 배송예정 술 목록 담을 리스트
    public static ArrayList<Al_Info_DTO> alcoholList = new ArrayList<>();

    // DB에서 가져온 값을 List 형태로 사용하기 위함
    public static List<Todays_Test_DTO> todays_Test_DTO_List = null;
    public static Al_Info_DTO al_info_DTO = null;
    public static List<My_SubsDetail_DTO> my_subsDetail_list = null;

    // 어느곳에서나 로그인이 되어있는지 loginDTO를 static으로 생성
    public static Mem_Info_DTO loginDTO = new Mem_Info_DTO();
    public static List<Mem_Info_DTO> loginList = new ArrayList<>();
}
=======
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
>>>>>>> 6baa67f0e48b2e13dea4961ee838a544bc11fcb0
