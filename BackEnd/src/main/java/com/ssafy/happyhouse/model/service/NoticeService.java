package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.QnADto;

public interface NoticeService {
   void regist(NoticeDto noticeDto) throws SQLException;

   void update(NoticeDto noticeDto) throws SQLException;

   void delete(int no) throws SQLException;

   List<NoticeDto> searchAll() throws SQLException;

   List<NoticeDto> searchByKeyword(String keyword) throws SQLException;

   NoticeDto searchByNo(int no) throws SQLException;
   List<NoticeDto> search(String keyword); //키워드검색
}