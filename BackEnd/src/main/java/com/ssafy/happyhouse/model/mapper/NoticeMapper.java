package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.QnADto;

@Mapper
public interface NoticeMapper {
	void regist(NoticeDto noticeDto) throws SQLException;

	void update(NoticeDto noticeDto) throws SQLException;

	void delete(int no) throws SQLException;

	List<NoticeDto> searchAll() throws SQLException;

	List<NoticeDto> searchByKeyword(String keyword) throws SQLException;

	NoticeDto searchByNo(int no) throws SQLException;
	
	List<NoticeDto> search(String keyword); // 키워드검색
}