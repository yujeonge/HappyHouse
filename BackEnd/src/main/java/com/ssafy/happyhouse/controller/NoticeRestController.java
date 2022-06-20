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

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.service.NoticeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeRestController {

   private static final Logger logger = LoggerFactory.getLogger(QnARestController.class);
   private static final String SUCCESS = "success";
   private static final String FAIL = "fail";

   @Autowired
   private NoticeService nsvc;

   // 공지글 등록(C)
   @ApiOperation(value = "공지사항 등록.", response = String.class)
   @PostMapping()
   public ResponseEntity<String> registNotice(@RequestBody NoticeDto noticeDto) throws Exception {
      logger.debug("공지사항 regist");
      nsvc.regist(noticeDto);
      return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
   }

   // 리스트 가져오기(R)
   @ApiOperation(value = "게시판 글목록", response = List.class)
   @GetMapping
   public ResponseEntity<List<NoticeDto>> listNotice() throws Exception {
      logger.debug("게시판 목록 불러오기");
      return new ResponseEntity<List<NoticeDto>>(nsvc.searchAll(), HttpStatus.OK);
   }

   // 세부내용 가져오기(R)
   @ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = NoticeDto.class)
   @GetMapping("{no}")
   public ResponseEntity<NoticeDto> detailNotice(@PathVariable int no) throws Exception {
      logger.debug("detailBoard - 호출");
      return new ResponseEntity<NoticeDto>(nsvc.searchByNo(no), HttpStatus.OK);
   }

   // 키워드 검색으로 가져오기(R)
   @ApiOperation(value = "키워드 검색", notes = "제목이나 내용에 키워드를 가지고 있는 게시글의 정보를 반환한다.", response = NoticeDto.class)
   @GetMapping("/search/{keyword}")
   public ResponseEntity<?> serachNotice(@PathVariable String keyword) throws Exception {
      logger.debug("serachNotice - 호출");
      List<NoticeDto> list = nsvc.search(keyword);
		if (list != null){
			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
   }
   
   // 공지글 수정(U)
   @ApiOperation(value = "공지사항 수정.", response = String.class)
   @PutMapping("{no}")
   public ResponseEntity<String> updateNotice(@RequestBody NoticeDto noticeDto) throws Exception {
      logger.debug("updateBoard - 호출");
      nsvc.update(noticeDto);
      return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
   }

   // 공지글 삭제(D)
   @ApiOperation(value = "공지사항 삭제.", response = String.class)
   @DeleteMapping("{no}")
   public ResponseEntity<String> deleteNotice(@PathVariable int no) throws Exception {
      logger.debug("공지사항 delete");
      nsvc.delete(no);
      return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
   }
}