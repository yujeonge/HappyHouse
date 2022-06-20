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
                  <h1 class="text-center" style="font: Verdana">QnA 게시판</h1>
                </md-card-header>

                <md-card-content>
                  <section>
                    <b-table
                      id="qnatable"
                      head-variant="light"
                      :items="articles"
                      :fields="fields"
                      :per-page="perPage"
                      :current-page="currentPage"
                      responsive="sm"
                      style="
                        margin-top: 50px;
                        font-size: 12px;
                        font-family: 'IBMPlexSansKR-Regular';
                      "
                    >
                      <template #cell(번호)="data">
                        <div class="text">
                          {{ data.item.articleno }}
                        </div>
                      </template>
                      <template #cell(제목)="data">
                        <router-link
                          :to="{
                            name: 'QnaDetail',
                            params: { articleno: data.item.articleno },
                          }"
                          ><div class="title">
                            {{ data.item.subject }}
                          </div></router-link
                        >
                      </template>
                      <template #cell(작성자)="data">
                        <div class="text">{{ data.item.username }}</div>
                      </template>
                      <template #cell(시간)="data">
                        <div class="text">
                          {{ data.item.regtime | dateFormat }}
                        </div>
                      </template>
                      <template #cell(답변)="data">
                        <div class="text">
                          {{
                            data.item.reply == null ? "답변대기" : "답변완료"
                          }}
                        </div>
                      </template>
                    </b-table>
                    <b-pagination
                      v-model="currentPage"
                      :total-rows="articles.length"
                      :per-page="perPage"
                      aria-controls="newstable"
                    ></b-pagination>
                  </section>

                  <div class="btnWrap">
                    <span
                      v-if="userInfo != null && userInfo.id != 'admin'"
                      class="addContainer"
                      @click="write"
                      ><md-button class="md-default">작성</md-button></span
                    >&nbsp;&nbsp;
                  </div>

                  <b-row class="mt-4 mb-4 text-center">
                    <b-col></b-col>
                    <b-col class="sm-3">
                      <md-field>
                        <md-icon>search</md-icon>
                        <md-input
                          v-model="keyword"
                          style="font-family: Verdana"
                          placeholder="제목, 내용"
                          @keyup.enter="search"
                        ></md-input>
                      </md-field>
                    </b-col>
                    <b-col class="sm-3 mt-2">
                      <md-button
                        style="float: left"
                        class="md-info"
                        @click="search"
                        >검색</md-button
                      >
                    </b-col>
                  </b-row>
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
import moment from "moment";
import http from "@/api/http";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnaList",
  components: {},
  props: {
    header: {
      type: String,
      default: require("@/assets/images/news.jpg"),
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  data() {
    return {
      word: "",
      fields: ["번호", "작성자", "제목", "시간", "답변"],
      currentPage: 1,
      perPage: 10,
      articles: [],
      reply: [],
    };
  },
  created() {
    http.get(`/qna/list`).then(({ data }) => {
      this.articles = data;
    });
  },
  computed: {
    ...mapState(["Qnas"]),
    ...mapState(memberStore, ["userInfo"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
    ans(reply) {
      if (reply == null) {
        return "답변대기";
      } else return "답변완료";
    },
  },
  methods: {
    ...mapActions({}),
    search() {
      if (this.keyword == "") {
        http.get(`/qna/list`).then(({ data }) => {
          this.articles = data;
        });
      } else {
        http.get(`/qna/search/${this.keyword}`).then(({ data }) => {
          this.articles = data;
        });
      }
    },
    write() {
      this.$router.push("/input");
    },
  },
};
</script>

<style scoped>
ul {
  list-style-type: none;
  padding-left: 0px;
  margin-top: 0;
  text-align: left;
}
li {
  display: flex;
  min-height: 50px;
  height: 50px;
  line-height: 50px;
  margin: 0.5rem 0;
  padding: 0 0.9rem;
  background: white;
  border-radius: 5px;
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
