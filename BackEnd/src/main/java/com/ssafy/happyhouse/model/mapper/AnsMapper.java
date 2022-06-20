package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.AnsDto;

@Mapper
public interface AnsMapper {
   void regist(AnsDto ansDto) throws SQLException;
   void update(AnsDto ansDto) throws SQLException;
   void delete(int articleno) throws SQLException;
   AnsDto detail(int articleno) throws SQLException;
   List<AnsDto> list() throws SQLException;
}