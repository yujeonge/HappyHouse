package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.UserInfoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(QnARestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserInfoService usvc;
	
	@Autowired
	private AdminService asvc;

//	jwt 이용한 로그인
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserInfoDto userinfoDto) {
		logger.debug("login");
		Map<String, Object> resultMap = new HashMap<>();
		logger.debug("UserInfoDto: {}", userinfoDto);
		Map<String, String> login = new HashMap<>(); // mapper로 넘길 id, password
		login.put("id", userinfoDto.getId());
		login.put("password", userinfoDto.getPassword());

		HttpStatus status = null;
		try {
			UserInfoDto loginUser = usvc.login(login);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				UserInfoDto userDto = usvc.searchById(userinfoDto.getId());
				logger.debug("userDto: {}", userDto);
				resultMap.put("userinfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// jwt 이용한 토큰확인
	@ApiOperation(value = "회원인증(테스트하지말것)", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String id,
			HttpServletRequest request) {
//		logger.debug("id : {} ", id);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserInfoDto userinfoDto = usvc.searchById(id);
				resultMap.put("userInfo", userinfoDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

//	// 로그인 - 유라코드에는 user객체 전체가 옴
//	@ApiOperation(value = "로그인. - 현재 수정중(jwt토큰)", response = String.class)
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody UserInfoDto userinfoDto) throws Exception {
////		logger.debug("login - 호출");
////		UserInfoDto userinfoDto = usvc.login(map);
////		if (userinfoDto != null) {
////			session.setAttribute("userinfo", userinfoDto);
////			Cookie cookie = new Cookie("id", map.get("userId"));
////			cookie.setPath("/");
////			if ("saveok".equals(map.get("idsave"))) {
////				cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
////			} else {
////				cookie.setMaxAge(0);
////			}
////			response.addCookie(cookie);
////			return "redirect:/";
////		} else {
////			model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
////			return "login";
////		}
//		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//	}

	// 회원가입
	@ApiOperation(value = "회원가입.", response = String.class)
	@PostMapping("/signup")
	public ResponseEntity<String> signup(UserInfoDto userinfoDto, Model model) throws SQLException {
		logger.debug("userSignup - 호출");
		usvc.regist(userinfoDto);
//		return "redirect:/user/login";
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 회원정보수정
	@ApiOperation(value = "회원정보 수정.", response = String.class)
	@PutMapping("/edit") // 얘를 put으로 하면 보안상에 문제가 생기나? 일단 post방법으로 둔다.
	public ResponseEntity<String> update(UserInfoDto userinfoDto) throws SQLException {
		logger.debug("userUpdate - 호출");
		logger.debug("userinfoDto: {}", userinfoDto);
		usvc.update(userinfoDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 회원삭제(Delete Mapping으로 변경)
	@ApiOperation(value = "회원정보 삭제.", response = String.class)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
		logger.debug("userDelete - 호출");
		logger.debug("삭제할 id : {}", id);
		usvc.deleteI(id); // fk로 참조하는 테이블의 레코드부터 먼저 삭제
		usvc.deleteU(id);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 비밀번호찾기
	@PostMapping("/exist")
	public ResponseEntity<Map<String, Object>> exist(
			@RequestBody @ApiParam(value = "비밀번호 찾는데 필요한 정보(id,que,ans).", required = true) UserInfoDto userinfoDto) {
		logger.debug("Exist 호출");
		Map<String, Object> resultMap = new HashMap<>();
		logger.debug("UserInfoDto: {}", userinfoDto);
		Map<String, String> exist = new HashMap<>(); // mapper로 넘길 id,que,ans
		exist.put("id", userinfoDto.getId());
		exist.put("que", userinfoDto.getQue());
		exist.put("ans", userinfoDto.getAns());

		HttpStatus status = null;
		try {
			UserInfoDto existUser = usvc.exist(exist);
			if (existUser != null) {
				UserInfoDto userDto = usvc.searchById(userinfoDto.getId());
				logger.debug("userDto: {}", userDto);
				resultMap.put("userinfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("회원찾기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 유저리스트(관리자)
	@ApiOperation(value = "회원 목록.", response = List.class)
	@GetMapping("/list")
	public ResponseEntity<List<UserInfoDto>> userlist() throws Exception {
		logger.debug("회원목록");
		List<UserInfoDto> list = usvc.searchAll();
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity<List<UserInfoDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
		
	//관리자 일병 회원가입 수 보기
	@ApiOperation(value = "일별 회원 목록.", response = List.class)
	@GetMapping("/dailyreg")
	public ResponseEntity<List<AdminDto>> dailyReglist() throws Exception {
		logger.debug("일별 회원 목록");
		List<AdminDto> list = asvc.dailyReg();
		
		
		
//		for (AdminDto adminDto : list) {
//			System.out.println(adminDto);
//		}
		
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity<List<AdminDto>>(list, HttpStatus.OK);
//			return new ResponseEntity<List<UserInfoDto>>(HttpStatus.NOT_FOUND);
//			return new ResponseEntity<List<UserInfoDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			System.out.println("에러");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}
	}
}
