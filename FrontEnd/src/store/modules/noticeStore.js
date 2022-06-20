import { select, selectAll, selectByKeyword, create } from "@/api/notice.js";

const noticeStore = {
  namespaced: true,
  state: {
    NoticeList: [],
  },
  getters: {
    NoticeList(state) {
      return state.NoticeList;
    },
  },
  mutations: {
    SET_NOTICE_LIST: (state, payload) => {
      state.NoticeList = payload;
    },
  },
  actions: {
    getAllNotice: ({ commit }) => {
      selectAll(
        ({ data }) => {
          commit("SET_NOTICE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getNotice: ({ commit }, no) => {
      // console.log(commit);
      select(
        no,
        ({ data }) => {
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    searchNotice: ({ commit }, keyword) => {
      // console.log(commit);
      selectByKeyword(
        keyword,
        ({ data }) => {
          console.log("noticeStore - searchNotice");
          console.log(data);
          commit("SET_NOTICE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    createNotice: ({ commit }, notice) => {
      console.log(commit);
      create(
        notice,
        ({ data }) => {
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default noticeStore;
