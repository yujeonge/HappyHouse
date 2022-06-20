import {
  searchAll,
  register,
  deleteOne,
  searchInfos,
  searchPopular,
} from "@/api/interestArea.js";
import store from "..";

const interestAreaStore = {
  namespaced: true,
  state: {
    areas: [{ dongCode: null, juso: null }],
    popularAreas: [{ count: null, dongCode: null, juso: null }],
    area: { dongCode: null, juso: null },
    latlng: { cx: null, cy: null }, //상권 정보 API 조회를 위한 area의 위도,경도 값
    infos: [], //laglng으로 구한 상권 정보
    sidoName: "",
    gugunName: "",
    dongName: "",
  },

  getters: {},
  mutations: {
    SET_AREA: (state, data) => {
      // console.log("intrestAreaStore - SET_AREA");
      // console.log("data : ", data);
      state.area = data;
    },
    SET_LATLNG: (state, data) => {
      console.log("intrestAreaStore - SET_LATLNG");
      console.log("data : ", data);
      state.latlng = data;
    },
    SET_AREAS: (state, data) => {
      state.areas = data;
    },
    SET_POPULAR_AREAS: (state, data) => {
      state.popularAreas = data;
    },
    SET_SIDO_NAME: (state, params) => {
      const sidoCode = params.sidoCode;
      const sidos = params.sidos;
      // console.log("interestAreaStore - SET_SIDO_NAME");
      const selected = sidos.find((option) => option.value === sidoCode);
      state.sidoName = selected.text;
      // console.log("sidoName : " + state.sidoName);
    },

    SET_GUGUN_NAME: (state, params) => {
      const gugunCode = params.gugunCode;
      const guguns = params.guguns;
      const selected = guguns.find((option) => option.value === gugunCode);
      state.gugunName = selected.text;
    },

    SET_DONG_NAME: (state, params) => {
      const dongCode = params.dongCode;
      const dongs = params.dongs;
      const selected = dongs.find((option) => option.value === dongCode);
      state.dongName = selected.text;
    },
    SET_INFOS: (state, infos) => {
      console.log("SET_INFOS");
      infos.forEach((info) => {
        // console.log(info);
        state.infos.push(info);
      });
      console.log(state.infos);
    },
    CLEAR_INFOS: (state) => {
      // console.log("CLEAR_INFOS");
      state.infos = [];
    },
    CLEAR_LATLNG: (state) => {
      // console.log("CLEAR_INFOS");
      state.latlng = { cx: null, cy: null };
    },
  },
  actions: {
    getAreas: ({ commit }, id) => {
      searchAll(
        id,
        ({ data }) => {
          console.log(data);
          commit("SET_AREAS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getPopularAreas: ({ commit }) => {
      searchPopular(
        ({ data }) => {
          // console.log(data);
          commit("SET_POPULAR_AREAS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    registInterstArea: ({ commit, dispatch }, params) => {
      register(
        params,
        ({ data }) => {
          console.log(data);
          dispatch("getAreas", ({ commit }, params.id));
        },
        (error) => {
          console.log(error);
          alert("중복입니다."); //중복이어서 에러 나는경우인지 확인 필요
        }
      );
    },
    deleteArea: ({ commit, dispatch }, params) => {
      deleteOne(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_AREA", { dongCode: null, juso: null });
          dispatch("getAreas", ({ commit }, params.id));
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getInfos: ({ commit }, options) => {
      commit("CLEAR_INFOS");
      console.log("interestAreasStore - getInfos");
      console.log(options);
      const latlng = options.latlng;
      const radius = options.radius;
      const cx = parseFloat(latlng.cx).toFixed(6);
      const cy = parseFloat(latlng.cy).toFixed(6);
      const SERVICE_KEY = process.env.VUE_APP_INFO_API_KEY;
      const selected = options.selected;
      // console.log(selected);
      // console.log(typeof selected);

      let indsLclsCd = "";
      let indsMclsCd = "";
      let indsSclsCd = "";

      selected.forEach((option) => {
        // console.log("interestAreasStore - getInfos");
        // console.log(option);
        if (option == "교육") {
          indsLclsCd = "R";
        } else if (option == "음식") {
          indsLclsCd = "Q";
        } else if (option == "생활") {
          indsLclsCd = "F";
        } else if (option == "여가") {
          indsLclsCd = "N";
        } else if (option == "스포츠") {
          indsLclsCd = "P";
        }

        let params = {
          serviceKey: decodeURIComponent(SERVICE_KEY),
          pageNo: encodeURIComponent("1"),
          numOfRows: encodeURIComponent("20"), //나중에 변경
          radius: encodeURIComponent(radius * 1000),
          cx,
          cy,
          indsLclsCd,
          indsMclsCd,
          indsSclsCd,
          type: "json",
        };

        searchInfos(
          params,
          (response) => {
            // console.log("interestAreaStore - getInfos");
            // console.log(response);
            // console.log(response.data.body.items);
            commit("SET_INFOS", response.data.body.items);
          },
          (error) => {
            console.log(error);
          }
        );
      });
    },
  },
};

export default interestAreaStore;
