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
                  <h1 class="text-center" style="font: Verdana">QnA</h1>
                  <!-- <h3 class="text-center" style="font-family: Verdana"> -->
                  <!-- 순차적으로 답변이 진행됩니다. -->
                  <!-- </h3> -->
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
                      <tr style="font-size: 14px">
                        <th style="color: darkolivegreen">
                          답변<br /><b-button
                            v-if="
                              article2.reply != null && userInfo.id == 'admin'
                            "
                            class="mt-3"
                            @click="replydel"
                            >답변삭제</b-button
                          >
                        </th>
                        <td
                          v-if="article2.reply == null"
                          class="txt_rep"
                          style="color: darkolivegreen"
                        >
                          답변이 달리지 않았습니다.
                        </td>
                        <td
                          v-else
                          class="txt_rep"
                          v-html="article2.reply"
                          style="color: darkolivegreen"
                        ></td>
                      </tr>
                    </b-table-simple>
                  </div>

                  <div class="btnWrap">
                    <b-row>
                      <b-col></b-col>
                      <b-col cols="7">
                        <span class="addContainer m-2" @click="back"
                          ><md-button class="md-default">목록</md-button></span
                        >
                        <span
                          v-if="userInfo.id == article.username"
                          class="addContainer m-2"
                          @click="mod"
                          ><md-button class="md-default">수정</md-button></span
                        >
                        <span
                          v-if="
                            userInfo.id == article.username ||
                            userInfo.id == 'admin'
                          "
                          class="addContainer m-2"
                          @click="del"
                          ><md-button class="md-default">삭제</md-button></span
                        >
                        <span
                          v-if="userInfo.id == 'admin'"
                          class="addContainer m-2"
                          @click="rp"
                          ><md-button class="md-default">답글</md-button></span
                        ></b-col
                      >
                      <b-col></b-col>
                    </b-row>
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

const memberStore = "memberStore";

export default {
  name: "QnaDetail",
  data() {
    return {
      article: {
        articleno: 0,
        username: "",
        subject: "",
        content: "",
      },
      article2: {
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
    //로그인했는지 확인
    if (this.userInfo == null) {
      alert("로그인 후 확인가능합니다.");
      this.$router.push({
        name: "SignIn",
      });
    }

    // 받아오기
    http.get(`/qna/detail/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
      // console.log(data);
    });
    //답글
    http.get(`/qna/ans/${this.$route.params.articleno}`).then(({ data }) => {
      this.article2.reply = data.content;
    });
  },

  computed: {
    ...mapState(["Qna"]),
    ...mapState(memberStore, ["userInfo"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },

  methods: {
    //답변삭제
    replydel() {
      http
        .delete(`/qna/ansdel/${this.$route.params.articleno}`)
        .then(({ data }) => {});
      alert("답변 삭제 완료.");
      this.$router.push("/list");
    },
    back() {
      this.$router.push("/list");
    },
    rp() {
      console.log("답글달기");

      this.$router.replace({
        name: "QnaReply",
        params: { articleno: this.article.articleno },
      });
    },
    mod() {
      this.$router.replace({
        name: "QnaModify",
        params: { articleno: this.article.articleno },
      });
      console.log("수정버튼누름");
    },
    del() {
      http
        .delete(`/qna/delete/${this.$route.params.articleno}`)
        .then(({ data }) => {
          this.article = data;
        });
      alert("삭제되었습니다.");
      this.$router.push("/list");
    },
  },
};
</script>

<style>
.tbAdd {
  border-top: 1px solid #888;
}
.tbAdd th,
.tbAdd td {
  border-bottom: 1px solid #eee;
  padding: 5px 0;
}
.tbAdd td {
  padding: 10px 10px;
  box-sizing: border-box;
  text-align: left;
}
.tbAdd td.txt_cont {
  height: 300px;
  vertical-align: top;
}
.tbAdd td.txt_rep {
  height: 100px;
  vertical-align: top;
}
.btnWrap {
  text-align: center;
  margin: 20px 0 0 0;
}
.btnWrap a {
  margin: 0 10px;
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
