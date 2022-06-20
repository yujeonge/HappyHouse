package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.InterestAreaDto;

public interface InterestAreaService {
	void registerArea(InterestAreaDto area) throws SQLException;
	List<InterestAreaDto> searchAllArea(String id) throws SQLException;
	void deleteArea(Map<String, String> map) throws SQLException;
	List<InterestAreaDto> searchPopular() throws SQLException;
	List<InterestAreaDto> searchPopular2(int num) throws SQLException;
}
