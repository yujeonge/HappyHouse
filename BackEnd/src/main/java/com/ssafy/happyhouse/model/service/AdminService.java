package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.AdminDto;

public interface AdminService {
	List<AdminDto> dailyReg() throws SQLException;
}
