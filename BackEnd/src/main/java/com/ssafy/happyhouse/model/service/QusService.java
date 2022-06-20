package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.QusDto;

public interface QusService {
	void regist(QusDto qusdto) throws SQLException;
	void update(QusDto qusdto) throws SQLException;
	void delete(int articleno) throws SQLException;
	List<QnADto> list() throws SQLException;
	List<QnADto> search(String keyword); //일단 키워드검색
	QusDto detail(int articleno) throws SQLException;
}
