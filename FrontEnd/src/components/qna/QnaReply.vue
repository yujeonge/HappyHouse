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
                    QnA 답변 작성
                  </h1>
                  <!-- <h3 class="text-center" style="font-family: Verdana">
                    What can we help you with?
                  </h3> -->
                </md-card-header>

                <md-card-content>
                  <div class="AddWrap">
                    <b-table-simple
                      class="tbAdd"
                      style="
                        margin-top: 50px;
                        font-size: 12px;
                        font-family: 'IBMPlexSansKR-Regular';
                      "
                    >
                      <colgroup>
                        <col width="15%" />
                        <col width="*" />
                      </colgroup>
                      <tr style="font-size: 15px">
                        <th>제목</th>
                        <td style="font-weight: bold">{{ article.subject }}</td>
                      </tr>
                      <tr>
                        <th>작성자</th>
                        <td>{{ article.username }}</td>
                      </tr>
                      <tr>
                        <th>내용</th>
                        <td class="txt_cont" v-html="article.content"></td>
                      </tr>
                    </b-table-simple>
                  </div>

                  <div
                    class="container"
                    style="
                      margin-top: 50px;
                      font-size: 12px;
                      font-family: 'IBMPlexSansKR-Regular';
                    "
                  >
                    <md-field>
                      <label style="color: red">답글</label>
                      <md-textarea v-model="article.reply"></md-textarea>
                    </md-field>
                  </div>

                  <div class="btnWrap">
                    <span class="clearAllBtn" @click="setReply"
                      ><md-button class="md-default">완료</md-button></span
                    >&nbsp;&nbsp;
                    <span class="clearAllBtn" @click="back"
                      ><md-button class="md-default">돌아가기</md-button></span
                    >&nbsp;&nbsp;
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
  name: "QnaReply",
  data() {
    return {
      article: {
        articleno: 0,
        username: "",
        subject: "",
        content: "",
        reply: "",
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
    setReply() {
      console.log("답글달기 버튼");
      console.log(this.article.reply);
      if (this.article.reply == "") alert("입력해주세요");
      else {
        http
          .post(`/qna/ansreg/${this.$route.params.articleno}`, null, {
            params: {
              articleno: this.article.articleno,
              content: this.article.reply,
            },
          })
          .then(({ data }) => {
            this.article = data;
            // console.log(this.article);
          });
        alert("답변 작성 완료.");
        this.$router.push({
          name: "QnaDetail",
          params: { articleno: this.article.articleno },
        });
      }
    },

    back() {
      this.$router.push({
        name: "QnaDetail",
        params: { articleno: this.article.articleno },
      });
    },
  },
};
</script>

<style>
.tbAdd td textarea {
  width: 100%;
  min-height: 300px;
  padding: 10px;
  box-sizing: border-box;
}

div.text {
  font-family: Verdana;
  font-size: 12px;
}

div.title {
  font-family: Verdana;
  font-size: 15px;
}
</style>
