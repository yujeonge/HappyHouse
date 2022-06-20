package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.QusDto;
import com.ssafy.happyhouse.model.mapper.QusMapper;

@Service
public class QusSeviceImpl implements QusService {
	
	@Autowired
	private QusMapper qusMapper;

	@Override
	public void regist(QusDto qusdto) throws SQLException {
		qusMapper.regist(qusdto);
	}

	@Override
	public void update(QusDto qusdto) throws SQLException {
		qusMapper.update(qusdto);
	}

	@Override
	public void delete(int articleno) throws SQLException {
		qusMapper.delete(articleno);
	}

	@Override
	public List<QnADto> list() throws SQLException {
		List<QnADto> list = qusMapper.list();
		return list;
	}

	@Override
	public List<QnADto> search(String keyword) {
		List<QnADto> list = qusMapper.search(keyword);
		return list;
	}

	@Override
	public QusDto detail(int articleno) throws SQLException {
		return qusMapper.detail(articleno);
	}


}
