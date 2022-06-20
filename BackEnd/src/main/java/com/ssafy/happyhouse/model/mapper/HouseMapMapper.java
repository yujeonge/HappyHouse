package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	String getDongCode(String dongName) throws SQLException;
	String getVueCode(Map<String, String> map) throws SQLException;
	List<HouseInfoDto> searchAptName(String aptName) throws SQLException;
	
	
//	알고리즘
	List<HouseInfoDto> getAptInDong_Algo(String dong) throws SQLException;
	List<HouseInfoDto> getAptInDong_limit(Map<String, Object> map) throws SQLException;
}
