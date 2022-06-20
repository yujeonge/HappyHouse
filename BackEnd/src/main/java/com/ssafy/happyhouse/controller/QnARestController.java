package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AnsDto;
import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.QusDto;
import com.ssafy.happyhouse.model.service.AnsService;
import com.ssafy.happyhouse.model.service.QusService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/qna")
@CrossOrigin("*")
public class QnARestController {

	private static final Logger logger = LoggerFactory.getLogger(QnARestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AnsService asvc;

	@Autowired
	private QusService qsvc;

	/////////////////////////////// 질문 ///////////////////////////////////

	// 질문등록
	@ApiOperation(value = "질문 등록.", response = List.class)
	@PostMapping("/regist")
	public ResponseEntity<String> qusRegist(@RequestBody QusDto qusDto) throws Exception {
		logger.debug("질문 regist");
		qsvc.regist(qusDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 질문목록
	@ApiOperation(value = "질문 목록.", response = List.class)
	@GetMapping("/list")
	public ResponseEntity<List<QnADto>> qusList() throws Exception {
		logger.debug("질문 list");
		List<QnADto> list = qsvc.list();
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity<List<QnADto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	// 질문삭제 > 답변도 함께 삭제
	@ApiOperation(value = "질문 삭제.", response = List.class)
	@DeleteMapping("/delete/{articleno}")
	public ResponseEntity<List<QnADto>> qusDelete(@PathVariable int articleno) throws Exception {
		logger.debug("질문 delete");
		asvc.delete(articleno);
		qsvc.delete(articleno);
		List<QnADto> list = qsvc.list();
		return new ResponseEntity<List<QnADto>>(list, HttpStatus.OK); // 이것도 list말고 다른거반환
	}

	// 질문 수정 / articleno은 어떻게 mapper로 넘기지? userModify와 똑같이 만들긴했음
	// 프론트에서 넘어오는 값 보고 바꾸던가 할것!
	@ApiOperation(value = "질문 수정.", response = String.class)
	@PutMapping("/modify/{articleno}")
	public ResponseEntity<String> qusModify(@RequestBody QusDto qusDto) throws Exception {
		logger.debug("질문 modify");
//		System.out.println(qusDto);
		qsvc.update(qusDto);
//		List<QusDto> list = qsvc.list();
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 질문 상세
	@ApiOperation(value = "질문 상세정보.", response = QusDto.class)
	@GetMapping("/detail/{articleno}")
	public ResponseEntity<QusDto> qusDetail(@PathVariable int articleno) throws Exception {
		logger.debug("질문 detail");
		return new ResponseEntity<QusDto>(qsvc.detail(articleno), HttpStatus.OK);
	}

	// 질문검색
	@ApiOperation(value = "질문 키워드검색.", response = List.class)
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<QnADto>> qusSearch(@PathVariable String keyword) throws Exception {
		logger.debug("질문 search");
		List<QnADto> list = qsvc.search(keyword);
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity<List<QnADto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	////////////////////////////// 답변 ////////////////////////////////////
	
	@ApiOperation(value = "답변 전체 목록.", response = List.class)
	@GetMapping("/anslist")
	public ResponseEntity<List<AnsDto>> ansList() throws Exception {
		logger.debug("답변 전체 list");
		List<AnsDto> list = asvc.list();
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity<List<AnsDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	// 답변 등록
	@ApiOperation(value = "답변 등록.", response = String.class)
	@PostMapping("/ansreg/{articleno}")
	public ResponseEntity<String> writeAnswer(@RequestBody AnsDto ansDto) throws Exception {
		logger.debug("답변 regist");
		asvc.regist(ansDto);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// 답변 수정
	@ApiOperation(value = "답변 수정.", response = String.class)
	@PutMapping("/ansmod/{articleno}")
	public ResponseEntity<String> modifyAnswer(@RequestBody AnsDto ansDto) throws Exception {
		logger.debug("답변 modify");
		asvc.update(ansDto);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// 답변 삭제
	@ApiOperation(value = "답변 삭제.", response = String.class)
	@DeleteMapping("/ansdel/{articleno}")
	public ResponseEntity<String> deleteAnswer(@PathVariable int articleno) throws Exception {
		logger.debug("답변 delete");
		asvc.delete(articleno);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// 글 번호에 해당하는 답변 가져오기
	@ApiOperation(value = "해당 게시글의 답변 가져오기.", response = AnsDto.class)
	@GetMapping("/ans/{articleno}")
	public ResponseEntity<AnsDto> detailBoard(@PathVariable int articleno) throws Exception {
		logger.debug("해당 질문에 이미 등록된 답변 가져오기");
		return new ResponseEntity<AnsDto>(asvc.detail(articleno), HttpStatus.OK);
	}
}
