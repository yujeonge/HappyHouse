import jwt_decode from "jwt-decode";
import axios from "@/store/axios-common.js";

import {
  login,
  register,
  update,
  deleteone,
  exist,
  searchall,
} from "@/api/member.js";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isAdmin: false,
    isLoginError: false,
    registStatus: true,
    userInfo: null,
    isExist: false,
    Password: "",
    users: {
      id: "",
      password: "",
      name: "",
      email: "",
      tel: "",
      que: "",
      ans: "",

    },
    dailyList: {
      regdate: "",
      cnt:"",
    },
    searchPopular: {
      juso: "",
      count:"",
    }
  },
  getters: {
    users(state) {
      return state.users;
    },
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    dailyList(state) { 
     return state.state;
    }
  },
  mutations: {
    SET_IS_LOGIN: (state, flag) => {
      state.isLogin = flag;
    },
    SET_IS_LOGIN_ERROR: (state, flag) => {
      state.isLoginError = flag;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    USER_INFO: (state, userInfo) => {
      state.userInfo = userInfo;
    },
    SET_REGIST_STATUS: (state, flag) => {
      state.registStatus = flag;
    },
    CLEAR_USER_INFO: (state) => {
      state.isLogin = false;
      state.userInfo = null;
    },
    SET_IS_Exist: (state, flag) => {
      state.isExist = flag;
    },
    userPassword: (state, password) => {
      state.Password = password;
      console.log(state.Password);
    },
    USER_LIST: (state, payload) => {
      state.NoticeList = payload;
    },
    SET_IS_ADMIN: (state, flag) => {
      state.isAdmin = flag;
    },
  },
  actions: {
    async LOGIN({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          console.log("login");
          console.log(data.userinfo);
          if (data.message == "success") {
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_USER_INFO", data.userinfo); //JWT 토큰으로 인증 후 searchbyId로 다시 userinfo를 불러오는걸로 구현
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("CLEAR_USER_INFO");
          }
          console.log(data.userinfo.id);
          if (data.userinfo.id === 'admin') {
            commit("SET_IS_ADMIN", true);

          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async EXIST({ commit }, user) {
      await exist(
        user,
        ({ data }) => {
          console.log("Exist 호출");
          commit("USER_INFO", data.userinfo);
          if (data.message == "success") {
            commit("SET_IS_Exist", true);
            commit("userPassword", data.userinfo.password);
          } else {
            commit("SET_IS_Exist", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async REGISTER({ commit }, user) {
      await register(
        user,
        ({ data }) => {
          console.log("memberStore - REGISTER");
          console.log(data);
          if (data == "success") {
            commit("SET_IS_LOGIN", true);
            commit("SET_REGIST_STATUS", true);
            // commit("SET_USER_INFO", user); //가입했으면 가입창에서 SET_USER_INFO 호출하게 하기
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_REGIST_STATUS", false);
            commit("CLEAR_USER_INFO");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async UPDATE({ commit }, user) {
      await update(
        user,
        (response) => {
          console.log("update 호출");
          console.log(response);
          if (response.data == "success") {
            commit("SET_USER_INFO", user);
          } else {
            console.log();
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async DELETEONE({ commit }, id) {
      await deleteone(
        id,
        () => {
          commit("CLEAR_USER_INFO");
          commit("SET_IS_LOGIN", false);
        },
        () => { }
      );
    },
    // async SEARCHALL({ commit },) {
    //   await searchall(
    //     ({ data }) => {
    //       commit("USER_LIST", data);
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // },

    SEARCHALL: (store, payload) => {
      axios.get("/user/searchall/" + payload).then((response) => {
        store.commit("USER_LIST", { users: response.data });
      });
    },
  },
};

export default memberStore;
