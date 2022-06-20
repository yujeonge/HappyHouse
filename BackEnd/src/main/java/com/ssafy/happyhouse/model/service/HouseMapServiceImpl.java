package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.InterestAreaDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {

   @Autowired
   private HouseMapMapper houseMapMapper;

   @Override
   public List<SidoGugunCodeDto> getSido() throws Exception {
      return houseMapMapper.getSido();
   }

   @Override
   public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
      return houseMapMapper.getGugunInSido(sido);
   }

   @Override
   public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
      return houseMapMapper.getDongInGugun(gugun);
   }

   @Override
   public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
      return houseMapMapper.getAptInDong(dong);
   }

   @Override
   public String getDongCode(String dongName) throws SQLException {
      return houseMapMapper.getDongCode(dongName);
   }

   /* 알고리즘 적용 */
   @Override
   public List<HouseInfoDto> getAptInDong_Algo(String dong, int target_lower, int targer_upper) throws SQLException {
      System.out.println("getAptInDong_Algo 시작 --------------------------");
      List<HouseInfoDto> list = houseMapMapper.getAptInDong_Algo(dong);// order by dealAmount로 얻어온 list
      int lowerBound = 0;
      int upperBound = list.size();
      // lower bound
      int left = 0;
      int right = list.size();
      int mid = 0;
      while (left < right) {
         mid = (left + right) / 2;
         // ,가 들어간 Stirng 값을 int로 바꾸기
         String strPrice = list.get(mid).getRecentPrice();
         strPrice = strPrice.replaceAll(",", "");
         strPrice = strPrice.trim();
         int price = Integer.parseInt(strPrice);
         if (price < target_lower) {
            left = mid + 1;
         } else {
            right = mid;
         }
      }
      lowerBound = right;

      // upper bound
      left = 0;
      right = list.size();
      mid = 0;
      while (left < right) {
         mid = (left + right) / 2;
         String strPrice = list.get(mid).getRecentPrice();
         strPrice = strPrice.replaceAll(",", "");
         strPrice = strPrice.trim();
         int price = Integer.parseInt(strPrice);

         if (price < targer_upper) {
            left = mid + 1;
         } else {
            right = mid;
         }
      }

      upperBound = right;

      System.out.println("---이분 탐색 결과---");
      System.out.println("lowerBound : " + lowerBound);
      System.out.println("upperBound : " + upperBound);

      Map<String, Object> map = new HashMap<String, Object>();
      map.put("dong", dong);
      map.put("lowerBound", lowerBound);
      map.put("upperBound", (upperBound - lowerBound) + 1);

      return houseMapMapper.getAptInDong_limit(map);
   }

   // 날짜순 정렬 - 내림차순
   public List<HouseInfoDto> getAptInDong_OrderByDate(List<HouseInfoDto> list) {
      List<HouseInfoDto> result = sort(list, 0, list.size() - 1, 0);
      Collections.reverse(result);
      return result;
   }

   // 이름순 정렬 - 오름차순
   public List<HouseInfoDto> getAptInDong_OrderByName(List<HouseInfoDto> list) {
      return sort(list, 0, list.size() - 1, 1);
   }

   // 오름차순 정렬
   private static List<HouseInfoDto> sort(List<HouseInfoDto> list, int lo, int hi, int type) {
      if (lo >= hi) { // 정렬할 원소가 1개 이하일 때 정렬 끝
         return list;
      }
      List<HouseInfoDto> result = new LinkedList<>();
      int pivot = partition(list, lo, hi, type);
      result = sort(list, lo, pivot - 1, type); // pivot 기준 왼쪽
      result = sort(result, pivot + 1, hi, type); // pivot 기준 오른쪽
      return result; // 정렬한 배열 리턴
   }

   // 오름차순 정렬 
   private static int partition(List<HouseInfoDto> list, int left, int right, int type) {
      int lo = left;
      int hi = right;
      HouseInfoDto pivot = list.get(left); // pivot 설정
      if (type == 0) { // BuildYear
         while (lo < hi) {
            // hi 값이 pivot 값보다 큰 값일 경우 반복(작은 값 만날 때까지)
            while (list.get(hi).getBuildYear() > pivot.getBuildYear() && lo < hi) {
               hi--;
            }
            // lo 값이 pivot 값보다 작은 값일 경우 반복(큰 값 만날 때까지)
            while (list.get(lo).getBuildYear() <= pivot.getBuildYear() && lo < hi) {
               lo++;
            }
            swap(list, lo, hi); // lo와 hi 위치가 정해지면 교환
         }
      } else { // AptName
         while (lo < hi) {
            // hi 값이 pivot 값보다 큰 값일 경우 반복(작은 값 만날 때까지)
            while (list.get(hi).getAptName().compareTo(pivot.getAptName()) > 0 && lo < hi) {
               hi--;
            }
            // lo 값이 pivot 값보다 작은 값일 경우 반복(큰 값 만날 때까지)
            while (list.get(lo).getAptName().compareTo(pivot.getAptName()) <= 0 && lo < hi) {
               lo++;
            }
            swap(list, lo, hi); // lo와 hi 위치가 정해지면 교환
         }

      }
      swap(list, left, lo); // pivot 재설정
      return lo; // pivot 위치 반환
   }

   // 정렬 관련 메서드 - node 2개 위치 변경
   private static List<HouseInfoDto> swap(List<HouseInfoDto> list, int i, int j) {
      HouseInfoDto temp1 = list.get(i);
      HouseInfoDto temp2 = list.get(j);
      list.set(i, temp2);
      list.set(j, temp1);
      return list;
   }

@Override
public String getVueCode(Map<String, String> map) throws SQLException {
	// TODO Auto-generated method stub
	return houseMapMapper.getVueCode(map);
}

@Override
public List<HouseInfoDto> searchAptName(String aptName) throws SQLException {
	// TODO Auto-generated method stub
	return houseMapMapper.searchAptName(aptName);
}

}