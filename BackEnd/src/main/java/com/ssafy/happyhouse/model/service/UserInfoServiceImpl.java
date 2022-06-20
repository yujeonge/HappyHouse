package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.UserInfoDto;
import com.ssafy.happyhouse.model.mapper.UserInfoMapper;


@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userinfoMapper;

	@Override
	public UserInfoDto login(Map<String, String> map) throws SQLException {
		return userinfoMapper.login(map);
	}

	@Override
	public void regist(UserInfoDto userinfo) throws SQLException {
		userinfoMapper.regist(userinfo);
	}
	
	@Override
	public void update(UserInfoDto userinfo) throws SQLException {
		userinfoMapper.update(userinfo);

	}
	
	@Override
	public void deleteI(String id) throws SQLException {
		userinfoMapper.deleteI(id);

	}
	
	@Override
	public void deleteU(String id) throws SQLException {
		userinfoMapper.deleteU(id);

	}
	
	@Override
	public UserInfoDto searchById(String id) throws SQLException {
		return userinfoMapper.searchById(id);
	}


	@Override
	public List<UserInfoDto> searchAll() throws SQLException {
		return userinfoMapper.searchAll();
	}

	@Override
	public UserInfoDto exist(Map<String, String> map) throws SQLException {
		return userinfoMapper.exist(map);
	}	
}
