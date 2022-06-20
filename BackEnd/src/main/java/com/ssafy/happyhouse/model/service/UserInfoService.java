package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.UserInfoDto;

public interface UserInfoService {
	UserInfoDto login(Map<String, String> map) throws SQLException;
	UserInfoDto exist(Map<String, String> map) throws SQLException;
	void regist(UserInfoDto userinfo) throws SQLException;
	void update(UserInfoDto userinfo)throws SQLException;
	void deleteI(String id) throws SQLException; //연관된 관심지역삭제
	void deleteU(String id) throws SQLException; //회원삭제
	UserInfoDto searchById(String id)throws SQLException;
	List<UserInfoDto> searchAll()throws SQLException;
}
