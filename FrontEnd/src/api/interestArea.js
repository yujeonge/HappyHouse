import { apiInstance, infoInstance } from "./index.js";

const api = apiInstance();
const info = infoInstance();

async function searchAll(id, success, fail) {
  await api.get(`/interest/searchAll/${id}`).then(success).catch(fail);
}

async function searchPopular(success, fail) {
  await api.get(`/interest/searchPopular`).then(success).catch(fail);
}

async function register(params, success, fail) {
  console.log("api/interestArea - register");
  console.log(params);
  await api
    .post(`/interest/register`, null, {
      params: {
        dongCode: params.dongCode,
        id: params.id,
        juso: params.juso,
      },
    })
    .then(success)
    .catch(fail);
}

async function deleteOne(params, success, fail) {
  console.log("api/interestArea - deleteOne");
  console.log(params);
  const userid = params.id;
  const dongCode = params.dongCode;
  await api
    .delete(`/interest/delete/${userid}/${dongCode}`)
    .then(success)
    .catch(fail);
}

async function searchInfos(params, success, fail) {
  // console.log("interestArea.js - searchInfos");
  // console.log(params);
  await info.get(``, { params: params }).then(success).catch(fail);
}

export { searchAll, searchPopular, register, deleteOne, searchInfos };
