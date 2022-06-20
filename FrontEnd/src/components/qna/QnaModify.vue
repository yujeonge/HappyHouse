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
                  <h1 class="text-center" style="font: Verdana">
                    질문 수정하기
                  </h1>
                  <!-- <h3 class="text-center" style="font-family: Verdana"> -->
                  <!-- 순차적으로 답변이 진행됩니다. -->
                  <!-- </h3> -->
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
                      <label>제목</label>
                      <md-input v-model="article.subject"></md-input>
                    </md-field>
                    <md-field>
                      <label>내용</label>
                      <md-textarea
                        style="height: 500px"
                        v-model="article.content"
                      ></md-textarea>
                    </md-field>
                  </div>

                  <div class="btnWrap">
                    <span class="clearAllBtn" @click="mod"
                      ><md-button class="md-default">완료</md-button></span
                    >
                    &nbsp; &nbsp;
                    <span class="clearAllBtn" @click="back"
                      ><md-button class="md-default">뒤로</md-button></span
                    >
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
import { mapState } from "vuex";
import http from "@/api/http";

export default {
  name: "QnaModify",
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
  created() {
    http.get(`/qna/detail/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
      console.log("modify의 created");
      console.log(this.article);
    });
  },
  computed: {
    ...mapState(["Qna"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    back() {
      this.$router.replace({
        name: "QnaDetail",
        params: { articleno: this.article.articleno },
      });
    },
    rp() {
      this.$router.push("/reply");
    },
    //완료버튼을 눌렀을때
    mod() {
      http
        .put(`/qna/modify/${this.$route.params.articleno}`, null, {
          params: {
            articleno: this.article.articleno,
            username: this.article.username,
            subject: this.article.subject,
            content: this.article.content,
          },
        })
        .then(({ data }) => {
          this.article = data;
          // console.log(this.article);
        });
      alert("수정되었습니다.");
      this.$router.push({
        name: "QnaDetail",
        params: { articleno: this.article.articleno },
      });
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
