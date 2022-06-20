package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

   @Autowired
   private NoticeMapper noticeMapper;

   @Override
   public void regist(NoticeDto noticeDto) throws SQLException {
      noticeMapper.regist(noticeDto);
   }

   @Override
   public void update(NoticeDto noticeDto) throws SQLException {
      noticeMapper.update(noticeDto);
   }

   @Override
   public void delete(int no) throws SQLException {
      noticeMapper.delete(no);
   }

   @Override
   public List<NoticeDto> searchByKeyword(String keyword) throws SQLException {
      return noticeMapper.searchByKeyword(keyword);
   }

   @Override
   public NoticeDto searchByNo(int no) throws SQLException {
      return noticeMapper.searchByNo(no);
   }

   @Override
   public List<NoticeDto> searchAll() throws SQLException {
      return noticeMapper.searchAll();
   }

@Override
public List<NoticeDto> search(String keyword) {
	List<NoticeDto> list = noticeMapper.search(keyword);
	return list;
}

}