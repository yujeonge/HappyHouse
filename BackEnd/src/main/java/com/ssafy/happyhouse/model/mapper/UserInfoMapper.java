package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserInfoDto;

@Mapper
public interface UserInfoMapper {
	UserInfoDto login(Map<String, String> map) throws SQLException;
	UserInfoDto exist(Map<String, String> map) throws SQLException;
	void regist(UserInfoDto userinfo) throws SQLException;
	void update(UserInfoDto userinfo)throws SQLException;
	void deleteI(String id) throws SQLException;
	void deleteU(String id) throws SQLException;
	UserInfoDto searchById(String id)throws SQLException;
	List<UserInfoDto> searchAll() throws SQLException;
	
}
