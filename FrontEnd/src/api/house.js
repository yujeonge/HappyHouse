import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  console.log("api- houseList 호출");
  // console.log(params);
  api.get(`/map/apt`, { params: params }).then(success).catch(fail);
}

function houseSearchList(params, success, fail) {
  console.log("api- houseSearchList 호출");
  // console.log(params);
  api.get(`/map/search`, { params: params }).then(success).catch(fail);
  // api.get(`/map/search/` + params).then(success).catch(fail);
}

function housename(params, success, fail) {
  api.get(`/map/searchaptName`, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, houseSearchList, dongList, houseList, housename };
