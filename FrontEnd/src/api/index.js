import axios from "axios";
import { API_BASE_URL, APT_DEAL_URL, INFO_URL } from "@/config";

// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function houseInstance() {
  const instance = axios.create({
    baseURL: APT_DEAL_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

//상권, 학군. 의약품 정보 인스턴스
function infoInstance() {
  const instance = axios.create({
    baseURL: INFO_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

export { apiInstance, houseInstance, infoInstance };
