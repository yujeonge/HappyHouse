import { wordList } from "@/api/word.js";

const wordStore = {
  namespaced: true,
  state: {
    words: [],
    wordArray: [],
  },
  mutations: {
    SETWORDS: (state, payload) => {
      state.words = payload;

      payload.forEach((currentElement, index) => {
        state.wordArray.push(`${index + 1}. ${currentElement.word}`);
      });
    },
  },
  actions: {
    getWords: ({ commit }) => {
      wordList(
        ({ data }) => {
          commit("SETWORDS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default wordStore;
