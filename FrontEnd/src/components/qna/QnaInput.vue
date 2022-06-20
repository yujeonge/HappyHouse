<template>
  <div>
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
      style="height: 300px"
    >
      <div class="container">
        <div class="md-layout"></div>
      </div>
    </parallax>

    <div class="main main-raised">
      <div class="section">
        <div class="container">
          <div class="md-layout">
            <div
              class="
                md-layout-item md-size-80 md-xsmall-size-100
                mx-auto
                text-center
              "
            >
              <md-card>
                <md-card-header data-background-color="blue">
                  <h1 class="text-center" style="font: Verdana">질문하기</h1>
                  <!-- <h3 class="text-center" style="font-family: Verdana">
                    What can we help you with?
                  </h3> -->
                </md-card-header>

                <md-card-content>
                  <div
                    class="container"
                    style="
                      margin-top: 50px;
                      font-size: 12px;
                      font-family: 'IBMPlexSansKR-Regular';
                    "
                  >
                    <md-field>
                      <label>작성자</label>
                      <!-- <md-input ref="username" v-model="article.username"></md-input> -->
                      <md-input
                        v-model="userInfo.id"
                        readonly="readonly"
                      ></md-input>
                    </md-field>
                    <md-field>
                      <label>제목</label>
                      <md-input
                        ref="subject"
                        v-model="article.subject"
                      ></md-input>
                    </md-field>
                    <md-field>
                      <label>내용</label>
                      <md-textarea
                        ref="content"
                        style="height: 500px"
                        v-model="article.content"
                      ></md-textarea>
                    </md-field>
                  </div>

                  <div class="btnWrap">
                    <span class="addContainer" @click="addQna">
                      <md-button class="md-default">등록</md-button> </span
                    >&nbsp;&nbsp;
                    <span class="addContainer" @click="back">
                      <md-button class="md-default">뒤로</md-button>
                    </span>
                  </div>
                </md-card-content>
              </md-card>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//import QnaInputItem from "@/components/qna/item/QnaInputItem";
import http from "@/api/http";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnaInput",
  data() {
    return {
      article: {
        articleno: 0,
        username: "",
        subject: "",
        content: "",
      },
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/hero_bg_4.jpg"),
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(["Qna"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    //등록 눌렀을때
    addQna() {
      console.log("addQna()호출");
      this.article.username = this.userInfo.id;
      if (!this.article.subject) {
        console.log("제목 입력");
        alert("제목을 입력해주세요.");
        this.$refs.subject.$el.focus();
      } else if (!this.article.content) {
        console.log("내용 입력");
        alert("내용을 입력해주세요.");
        this.$refs.content.$el.focus();
      } else {
        http
          .post(`/qna/regist`, null, {
            params: {
              articleno: this.article.articleno,
              subject: this.article.subject,
              username: this.article.username,
              content: this.article.content,
            },
          })
          .then(({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "등록이 완료되었습니다.";
              this.article = data;
            }
            alert(msg);
            this.$router.push("/list");
          });
      }
      console.log("등록 끝");
    },
    //뒤로 눌렀을때
    back() {
      console.log("back()호출");
      this.$router.push("/list");
    },
  },
};
</script>

<style>
div.text {
  font-family: Verdana;
  font-size: 12px;
}

div.title {
  font-family: Verdana;
  font-size: 15px;
}
</style>
