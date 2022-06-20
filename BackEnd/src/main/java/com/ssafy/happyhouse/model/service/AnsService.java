package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.AnsDto;

public interface AnsService {
   void regist(AnsDto ansDto) throws SQLException;
   void update(AnsDto ansDto) throws SQLException;
   void delete(int articleno) throws SQLException;
   AnsDto detail(int articleno) throws SQLException; // 질문에 맞는 답변 보여줄 때
   List<AnsDto> list() throws SQLException;
}