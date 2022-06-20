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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AdminDto;
import com.ssafy.happyhouse.model.UserInfoDto;
import com.ssafy.happyhouse.model.service.AdminService;
import com.ssafy.happyhouse.model.service.UserInfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin") // 맞는지 모르겠음
@CrossOrigin("*")
public class AdminRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserInfoService usvc;

	// 전체 회원 목록 띄우기
	@ApiOperation(value = "전체 회원 목록.", response = List.class)
	@GetMapping(value = "/user") 
	public ResponseEntity<List<UserInfoDto>> userList() throws Exception {
		logger.debug("userList - 호출");
		List<UserInfoDto> list = usvc.searchAll();
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity<List<UserInfoDto>>(list, HttpStatus.OK);
//			return new ResponseEntity<List<UserInfoDto>>(HttpStatus.NOT_FOUND);
//			return new ResponseEntity<List<UserInfoDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	// 회원 등록
	@ApiOperation(value = "회원 등록.", response = String.class)
	@PostMapping(value = "/user")
	public ResponseEntity<String> userRegister(@RequestBody UserInfoDto userinfoDto) throws Exception {
		logger.debug("userRegister - 호출");
		usvc.regist(userinfoDto);
//		List<UserInfoDto> list = usvc.searchAll();
//		return new ResponseEntity<List<UserInfoDto>>(list, HttpStatus.OK);
//		return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 회원 검색
	@ApiOperation(value = "회원 검색.", response = UserInfoDto.class)
	@GetMapping(value = "/user/{userid}")
	public ResponseEntity<UserInfoDto> userInfo(@PathVariable("userid") String userid) throws Exception {
		logger.debug("userInfo - 호출");
		UserInfoDto memberDto = usvc.searchById(userid);
		if (memberDto != null)
			return new ResponseEntity<UserInfoDto>(memberDto, HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	// 회원 수정
	@ApiOperation(value = "회원 수정.", response = String.class)
	@PutMapping(value = "/user")
	public ResponseEntity<String> userModify(@RequestBody UserInfoDto userinfoDto) throws Exception {
		logger.debug("userModify - 호출");
		usvc.update(userinfoDto);
//		List<UserInfoDto> list = usvc.searchAll();
//		return new ResponseEntity<List<UserInfoDto>>(list, HttpStatus.OK);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 회원 삭제 
	@ApiOperation(value = "회원 삭제.", response = String.class)
	@DeleteMapping(value = "/user/{userid}")
	public ResponseEntity<String> userDelete(@PathVariable("userid") String userid) throws Exception {
		logger.debug("userDelete - 호출");
		usvc.deleteI(userid); //관심지역 삭제
		usvc.deleteU(userid); //회원정보 삭제
//		List<UserInfoDto> list = usvc.searchAll();
//		return new ResponseEntity<List<UserInfoDto>>(list, HttpStatus.OK);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}


	/*
	 * @RequestMapping(value = "/user", method = RequestMethod.GET, headers = {
	 * "Content-type=application/json" }) public List<MemberDto> userList() throws
	 * Exception { List<MemberDto> list = memberService.listMember();
	 * logger.debug("회원목록 : {}", list); return list; // return
	 * memberService.listMember(); }
	 * 
	 * @RequestMapping(value = "/user", method = RequestMethod.POST, headers = {
	 * "Content-type=application/json" }) public List<MemberDto>
	 * userRegister(@RequestBody MemberDto memberDto) throws Exception {
	 * memberService.registerMember(memberDto); return memberService.listMember(); }
	 * 
	 * @RequestMapping(value = "/user/{userid}", method = RequestMethod.GET, headers
	 * = { "Content-type=application/json" }) public MemberDto
	 * userInfo(@PathVariable("userid") String userid) throws Exception { return
	 * memberService.getMember(userid); }
	 * 
	 * @RequestMapping(value = "/user", method = RequestMethod.PUT, headers = {
	 * "Content-type=application/json" }) public List<MemberDto>
	 * userModify(@RequestBody MemberDto memberDto) throws Exception {
	 * memberService.updateMember(memberDto); return memberService.listMember(); }
	 * 
	 * @RequestMapping(value = "/user/{userid}", method = RequestMethod.DELETE,
	 * headers = { "Content-type=application/json" }) public List<MemberDto>
	 * userDelete(@PathVariable("userid") String userid) throws Exception {
	 * memberService.deleteMember(userid); return memberService.listMember(); }
	 */

}
