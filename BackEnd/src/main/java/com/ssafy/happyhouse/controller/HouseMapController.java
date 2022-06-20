package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;

	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido - 호출");
		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}

	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		logger.debug("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		logger.debug("dong - 호출");
		List<HouseInfoDto> list = haHouseMapService.getDongInGugun(gugun);
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}
	
	
	// vue에서 dongName으로만 받아와져서 guguncode와 dongName으로 dongCode찾기
	@GetMapping("/code/{gugun}/{dong}")
	public ResponseEntity<String> dong(@PathVariable("gugun") String guguncode,@PathVariable("dong") String dongname) throws Exception {
		logger.debug("code - 호출");
//		List<HouseInfoDto> list = haHouseMapService.getDongInGugun(gugun);
		Map<String,String> map = new HashMap<String, String>();
		map.put("guguncode", guguncode);
		map.put("dongname", dongname);
		String temp = haHouseMapService.getVueCode(map);
		System.out.println("temp : "+temp);
		return new ResponseEntity<String>(temp, HttpStatus.OK);
	}

//	@GetMapping("/dongCode")
//	public String dong2(@RequestParam("dongName") String dongName) throws Exception {
//		String dongCode = haHouseMapService.getDongCode(dongName);
//		return dongCode;
//	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dongCode") String dong) throws Exception {
		logger.debug("apt - 호출");
		List<HouseInfoDto> list = haHouseMapService.getAptInDong(dong);
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}
	
	
	//아파트 이름으로 검색
	@GetMapping("/search")
	public ResponseEntity<List<HouseInfoDto>> search(@RequestParam String aptName) throws Exception {
		logger.debug("search - 호출");
		List<HouseInfoDto> list = haHouseMapService.searchAptName(aptName);
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}
	
	

//	@GetMapping("/apt")
//	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong,
//			@RequestParam("target_lower") int target_lower, @RequestParam("target_upper") int target_upper,
//			@RequestParam("orderType") String orderType) throws Exception {
//		logger.debug("apt - 호출");
//		System.out.println("--- 아파트 조회 메서드 진입 --- ");
//		System.out
//				.println("dong : " + dong + "bound : " + target_lower + " ~ " + target_upper + "정렬 방법 : " + orderType);
//
//		// 처음에 범위 조건을 만족하는 리스트를 가격순으로 먼저 받아옴
//		List<HouseInfoDto> list = haHouseMapService.getAptInDong_Algo(dong, target_lower, target_upper);
//		// 필요에 따라서 다시 정렬
//		if (orderType.equals("최신순")) {
//			System.out.println("최신순으로 다시 정렬");
//			list = haHouseMapService.getAptInDong_OrderByDate(list);
//		} else if (orderType.equals("이름순")) {
//			System.out.println("이름순으로 다시 정렬");
//			list = haHouseMapService.getAptInDong_OrderByName(list);
//		}
//
//		if (!list.isEmpty())
//			return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
//		else {
//			System.out.println("검색 결과가 없을때 에러로 처리(다른 방법으로 구현하면 더 좋을듯..)");
//			return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}
