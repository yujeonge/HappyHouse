import { apiInstance } from "./index.js";

const api = apiInstance();

function wordList(success, fail) {
  api.get(`/word`).then(success).catch(fail);
}


export { wordList };
