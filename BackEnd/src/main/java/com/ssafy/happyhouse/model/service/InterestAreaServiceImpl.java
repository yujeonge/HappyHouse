package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.InterestAreaDto;
import com.ssafy.happyhouse.model.mapper.InterestAreaMapper;

@Service
public class InterestAreaServiceImpl implements InterestAreaService {

   @Autowired
   private InterestAreaMapper interestAreaMapper;

   @Override
   public void registerArea(InterestAreaDto area) throws SQLException {
      interestAreaMapper.registerArea(area);
   }

   // 가나다 정렬
   @Override
   public List<InterestAreaDto> searchAllArea(String id) throws SQLException {
      List<InterestAreaDto> list = interestAreaMapper.searchAllArea(id);
      // 가나다 정렬(퀵 정렬(왼쪽 pivot 사용)) 후 리턴
      return sort(list, 0, list.size() - 1);
   }

   @Override
   public List<InterestAreaDto> searchPopular() throws SQLException {
      List<InterestAreaDto> list = interestAreaMapper.searchPopular();
      // 가나다 정렬(퀵 정렬(왼쪽 pivot 사용)) 후 리턴
      return list;
   }
   
   @Override
   public List<InterestAreaDto> searchPopular2(int num) throws SQLException {
      List<InterestAreaDto> list = interestAreaMapper.searchPopular2(num);
      return list;
   }
   
   @Override
   public void deleteArea(Map<String, String> map) throws SQLException {
      interestAreaMapper.deleteArea(map);
   }

   /**
    * 정렬 관련 메서드 - pivot을 바꿔주고 정렬하는 것을 반복
    * @param list   정렬할 배열
    * @param lo   현재 부분배열의 왼쪽
    * @param hi   현재 부분배열의 오른쪽
    */
   private static List<InterestAreaDto> sort(List<InterestAreaDto> list, int lo, int hi) {
      if (lo >= hi) { // 정렬할 원소가 1개 이하일 때 정렬 끝
         return list;
      }
      int pivot = partition(list, lo, hi); 
      List<InterestAreaDto> result = new LinkedList<>();
      result = sort(list, lo, pivot - 1); // pivot 기준 왼쪽
      result = sort(result, pivot + 1, hi); // pivot 기준 오른쪽
      return result; // 정렬한 배열 리턴
   }
   /**
    * 정렬 관련 메서드 - pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
    * @param list   정렬 할 배열 
    * @param left   현재 배열의 가장 왼쪽 노드(처음 피봇 pivot)
    * @param right   현재 배열의 가장 오른쪽 노드
    * @return      최종적으로 위치한 피벗의 위치(lo)를 반환
    */
   private static int partition(List<InterestAreaDto> list, int left, int right) {
      int lo = left;
      int hi = right;
      InterestAreaDto pivot = list.get(left); // pivot 설정
      while (lo < hi) {
         //hi 값이 pivot 값보다 큰 값일 경우 반복(작은 값 만날 때까지)
         while (list.get(hi).getJuso().compareTo(pivot.getJuso()) > 0 && lo < hi) { 
            hi--;
         }
         //lo 값이 pivot 값보다 작은 값일 경우 반복(큰 값 만날 때까지)
         while (list.get(lo).getJuso().compareTo(pivot.getJuso()) <= 0 && lo < hi) {
            lo++;
         }
         swap(list, lo, hi); // lo와 hi 위치가 정해지면 교환
      }
      swap(list, left, lo); // pivot 재설정
      return lo; // pivot 위치 반환
   }
   // 정렬 관련 메서드 - node 2개 위치 변경
   private static List<InterestAreaDto> swap(List<InterestAreaDto> list, int i, int j) {
      InterestAreaDto temp1 = list.get(i);
      InterestAreaDto temp2 = list.get(j);
      list.set(i, temp2);
      list.set(j, temp1);
      return list;
   }
   
   
}