package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.InterestAreaDto;

@Mapper
public interface InterestAreaMapper {

	void registerArea(InterestAreaDto area) throws SQLException;
	List<InterestAreaDto> searchAllArea(String id) throws SQLException;
	void deleteArea(Map<String, String> map) throws SQLException;
	List<InterestAreaDto> searchPopular() throws SQLException;
	List<InterestAreaDto> searchPopular2(int num) throws SQLException;
}
