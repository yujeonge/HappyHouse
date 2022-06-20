package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AnsDto;
import com.ssafy.happyhouse.model.mapper.AnsMapper;

@Service
public class AnsServiceImpl implements AnsService {
   
   @Autowired
   private AnsMapper ansMapper;

   @Autowired
   private SqlSession sqlSession;

   @Override
   public void regist(AnsDto ansDto) throws SQLException {
      ansMapper.regist(ansDto);
      
   }

   @Override
   public void update(AnsDto ansDto) throws SQLException {
      ansMapper.update(ansDto);
      
   }

   @Override
   public void delete(int articleno) throws SQLException {
      ansMapper.delete(articleno);
      
   }

   @Override
   public AnsDto detail(int articleno) throws SQLException{
      return ansMapper.detail(articleno);
   }

@Override
public List<AnsDto> list() throws SQLException {
	// TODO Auto-generated method stub
	return ansMapper.list();
}

   

   

}