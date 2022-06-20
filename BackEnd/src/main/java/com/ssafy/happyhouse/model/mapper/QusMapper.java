package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.QusDto;

@Mapper
public interface QusMapper {
	
	List<QnADto> list() throws SQLException;
	void regist(QusDto qusdto) throws SQLException;
	void update(QusDto qusdto) throws SQLException;
	void delete(int articleno) throws SQLException;
	List<QnADto> search(String keyword); //일단 키워드검색
	QusDto detail(int articleno) throws SQLException;
	
}
