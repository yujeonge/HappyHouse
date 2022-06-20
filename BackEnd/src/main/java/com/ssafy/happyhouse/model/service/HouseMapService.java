package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseMapService {

   List<SidoGugunCodeDto> getSido() throws Exception;
   List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
   List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
   List<HouseInfoDto> getAptInDong(String dong) throws Exception;
   String getDongCode(String dongName) throws SQLException;
   String getVueCode(Map<String, String> map) throws SQLException;
   List<HouseInfoDto> searchAptName(String aptName) throws SQLException;
   
//   알고리즘
   List<HouseInfoDto> getAptInDong_Algo(String dong, int lower, int upper) throws SQLException;
   List<HouseInfoDto> getAptInDong_OrderByDate(List<HouseInfoDto> list) throws SQLException;
   List<HouseInfoDto> getAptInDong_OrderByName(List<HouseInfoDto> list) throws SQLException;
   
}