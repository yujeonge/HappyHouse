package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.AdminDto;
@Mapper
public interface AdminMapper {
	List<AdminDto> dailyReg() throws SQLException;
}
