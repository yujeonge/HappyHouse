import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(params, success, fail) {
  await api
    .post(`/user/login`, JSON.stringify(params))
    .then(success)
    .catch(fail);
}

// async function findById(userid, success, fail) {
//   // api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
//   await api.get(`/user/info/${userid}`).then(success).catch(fail);
// }

// API 에 맞게 부르는 값 변경 필요
async function register(params, success, fail) {
  await api
    .post("/user/signup", null, { params: params })
    .then(success)
    .catch(fail);
}

async function update(params, success, fail) {
  await api
    .put("/user/edit", null, { params: params })
    .then(success)
    .catch(fail);
}

async function selectone(params, success, fail) {
  await api
    .get("/user/find" + params)
    .then(success)
    .catch(fail);
}

async function exist(params, success, fail) {
  await api
    .post(`/user/exist`, JSON.stringify(params))
    .then(success)
    .catch(fail);
}

async function deleteone(id, success, fail) {
  await api.delete(`/user/delete/${id}`).then(success).catch(fail);
}

async function searchall(params, success, fail) {
  await api
    .post("/user/signup", null, { params: params })
    .then(success)
    .catch(fail);
}
// function logout(success, fail)

export { login, register, update, selectone, deleteone, exist ,searchall };
