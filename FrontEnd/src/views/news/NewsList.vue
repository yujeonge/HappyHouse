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
                md-layout-item md-size-80 md-size-80 md-xsmall-size-100
                mx-auto
              "
            >
              <md-card>
                <md-card-header data-background-color="blue">
                  <h1 class="text-center" style="font: Verdana">News</h1>
                </md-card-header>

                <md-card-content>
                  <section>
                    <b-table
                      id="newstable"
                      head-variant="light"
                      :items="news"
                      :fields="fields"
                      :per-page="perPage"
                      :current-page="currentPage"
                      responsive="sm"
                      style="
                        font-size: 14px;
                        font-family: 'IBMPlexSansKR-Regular';
                        width: 85%;
                        margin: auto;
                      "
                      thead-class="d-none"
                    >
                      <template #cell(사진)="data">
                        <a :href="`${data.item.url}`" target="_blank">
                          <img
                            :src="`${data.item.imgUrl}`"
                            alt="Image"
                            class="rounded img-fluid"
                            width="150"
                          />
                        </a>
                      </template>

                      <template #cell(제목)="data">
                        <!-- <br /> -->
                        <a
                          :href="`${data.item.url}`"
                          target="_blank"
                          style="
                            color: darkgreen;
                            font-size: 18px;
                            line-height: 200%;
                            font-weight: bold;
                          "
                          >{{ data.item.title }}</a
                        >
                        <br />
                        {{ data.item.contents }}
                        <br />
                        <div class="small" style="line-height: 300%">
                          {{ data.item.writing }} &nbsp;|&nbsp;
                          <div
                            v-if="
                              data.item.time.indexOf('분전') !== -1 ||
                              data.item.time.indexOf('1시') !== -1
                            "
                            class="recently"
                            style="line-height: 300%"
                          >
                            {{ data.item.time }}
                          </div>
                          <div v-else class="past" style="line-height: 300%">
                            {{ data.item.time }}
                          </div>
                        </div>
                      </template>
                      <!-- <template #cell(내용)="data">
                {{ data.item.contents }}
              </template>
              <template #cell(시간)="data">
                <b-icon icon="clock" font-scale="1" variant="dark"></b-icon>
                {{ data.item.time }}
              </template> -->
                    </b-table>
                    <b-pagination
                      v-model="currentPage"
                      :total-rows="news.length"
                      :per-page="perPage"
                      aria-controls="newstable"
                    ></b-pagination>
                  </section>
                  <b-row class="mt-4 mb-4 text-center">
                    <b-col></b-col>
                    <b-col class="sm-3">
                      <md-field>
                        <md-icon>search</md-icon>
                        <md-input
                          v-model="word"
                          style="font-family: Verdana"
                          placeholder="제목"
                          @keyup.enter="searchN"
                        ></md-input>
                      </md-field>
                    </b-col>
                    <b-col class="sm-3 mt-2">
                      <md-button
                        style="
                          float: left;
                          font-family: 'IBMPlexSansKR-Regular';
                        "
                        class="md-info"
                        @click="searchN"
                        >검색</md-button
                      >
                    </b-col>
                  </b-row>
                </md-card-content>
              </md-card>
            </div>
          </div>
          <!-- <div class="btnWrap">
            <md-button class="md-default" @click="all">목록</md-button>
          </div> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { mapState, mapActions } from "vuex";
import http from "@/api/http";
const newsStore = "newsStore";

export default {
  components: {
    //  NewsListItem,
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/news.jpg"),
    },
  },
  data() {
    return {
      fields: ["사진", "제목"],
      currentPage: 1,
      perPage: 8,
      word: "",
      news: [],
    };
  },
  created() {
    http.get(`/news`).then(({ data }) => {
      this.news = data;
      //this.ctime = Number(this.news.time.substr(0, 1));
      console.log(this.news);
    });
  },
  computed: {
    ...mapState(newsStore, ["NewsList"]),
    //...mapState(["NewsList"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    ...mapActions(newsStore, ["ALLNEWS"]),
    // searchN() {
    //   console.log("searchN() 호출");
    //   console.log(this.word);
    //   if (this.word == "") alert("검색어를 입력해주세요.");
    //   else this.$store.dispatch("SEARCHNEWS", this.word);
    // },
    searchN() {
      if (this.word == "") {
        http.get(`/news`).then(({ data }) => {
          this.news = data;
          console.log(this.news);
        });
      }
      //else this.$store.dispatch("SEARCHNEWS", this.word);
      else {
        http.get(`/news/search/${this.word}`).then(({ data }) => {
          this.news = data;
          console.log(this.news);
        });
      }
    },
    all() {
      console.log("NewsList", this.NewsList);
      //  console.log("!!!!!!!!!!");
      //  console.log(this.NewsList.length);
      //  console.log(this.news.length);
      // // this.word = "";
      // this.$store.dispatch("ALLNEWS");
      // console.log("News", this.news);
    },
  },
};
</script>

<style scoped>
body {
  width: 100%;
}
.div-size {
  width: 80%;
  margin-right: 0px;
}
.past {
  font-size: 12px;
  display: inline-block;
}
.recently {
  color: green;
  font-weight: bold;
  font-size: 12px;
  display: inline-block;
}
.small {
  font-size: 12px;
}
.del {
  text-decoration: line-through;
}
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
.checkBtn {
  line-height: 45px;
  color: #62acde;
  margin-right: 5px;
}
.removeBtn {
  margin-left: auto;
  color: #de4343;
}

.list-enter-active,
.list-leave-active {
  transition: all 1s;
}
.list-enter,
.list-leave-to {
  opacity: 0;
  transform: translateY(30px);
}
</style>
