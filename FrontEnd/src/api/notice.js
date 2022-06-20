import { apiInstance } from "./index.js";
const api = apiInstance();

async function selectAll(success, fail) {
  await api.get("/notice").then(success).catch(fail);
}

async function select(params, success, fail) {
  await api.get(`/notice/${params}`).then(success).catch(fail);
}

async function create(params, success, fail) {
  console.log(params);
  await api
    .post("/notice", null, {
      params,
    })
    .then(success)
    .catch(fail);
}

async function selectByKeyword(keyword, success, fail) {
  await api.get(`/notice/search/${keyword}`).then(success).catch(fail);
}

export { select, selectAll, selectByKeyword, create };
