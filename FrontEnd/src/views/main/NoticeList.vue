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
                  <h1 class="text-center" style="font: Verdana">공지사항</h1>
                  <!-- <h3 class="text-center" style="font-family: Verdana">
                    공지사항 목록 보기
                  </h3> -->
                </md-card-header>
                <md-card-content>
                  <b-row class="mt-2 mb-2 pb-2 sm-3 border-bottom">
                    <b-col
                      cols="2"
                      @click="load(0)"
                      style="cursor: pointer"
                      v-bind:class="{ active: cssActives[0] }"
                      class="navibar"
                    >
                      전체({{ this.list0.length }})
                    </b-col>
                    <b-col
                      cols="2"
                      @click="load(1)"
                      style="cursor: pointer"
                      v-bind:class="{ active: cssActives[1] }"
                      class="navibar"
                    >
                      정보({{ this.list1.length }})
                    </b-col>
                    <b-col
                      cols="2"
                      @click="load(2)"
                      style="cursor: pointer"
                      v-bind:class="{ active: cssActives[2] }"
                      class="navibar"
                    >
                      사이트({{ this.list2.length }})
                    </b-col>
                    <b-col
                      cols="2"
                      @click="load(3)"
                      style="cursor: pointer; text: "
                      v-bind:class="{ active: cssActives[3] }"
                      class="navibar"
                    >
                      기타({{ this.list3.length }})
                    </b-col>
                  </b-row>

                  <section>
                    <b-table
                      id="noticetable"
                      head-variant="light"
                      :items="curList"
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
                          {{
                            curList.length -
                            perPage * (currentPage - 1) -
                            data.index
                          }}
                        </div>
                      </template>
                      <template #cell(제목)="data">
                        <router-link
                          :to="{
                            name: 'NoticeDetail',
                            params: { no: data.item.no },
                          }"
                          class="link"
                        >
                          <div class="title">
                            [{{ data.item.type }}] {{ data.item.title }}
                          </div></router-link
                        >
                      </template>
                      <template #cell(등록일)="data">
                        <div class="text">
                          {{ data.item.regtime | dateFormat }}
                        </div>
                      </template>
                    </b-table>
                    <b-pagination
                      v-model="currentPage"
                      :total-rows="NoticeList.length"
                      :per-page="perPage"
                      aria-controls="noticetable"
                    ></b-pagination>
                  </section>

                  <b-row class="mt-4 mb-2 text-center">
                    <b-col></b-col>
                    <b-col class="sm-3">
                      <md-field>
                        <md-icon>search</md-icon>
                        <md-input
                          v-model="word"
                          style="font-family: Verdana"
                          placeholder="제목, 내용"
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
                      <md-button
                        style="
                          float: left;
                          font-family: 'IBMPlexSansKR-Regular';
                        "
                        class="md-danger"
                        @click="regist"
                        v-if="userInfo != null && userInfo.id == 'admin'"
                        >등록</md-button
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
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
const noticeStore = "noticeStore";

export default {
  components: {},
  props: {
    header: {
      type: String,
      default: require("@/assets/images/news.jpg"),
    },
  },
  data() {
    return {
      curList: [], //현재 선택한 종류에 맞는 공지사항 목록,
      list0: [], //전체
      list1: [], //정보
      list2: [], //사이트
      list3: [], //기타
      fields: ["번호", "제목", "등록일"],
      currentPage: 1,
      perPage: 10,
      word: "",
      cssActives: [true, false, false, false],
    };
  },
  created() {
    this.getAllNotice();
    this.curList = this.NoticeList;
    // console.log("curList: ", this.curList);
    this.length_Notice = this.NoticeList.length;
  },
  computed: {
    ...mapState(noticeStore, ["NoticeList"]),
    ...mapState(memberStore, ["userInfo"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  watch: {
    NoticeList: function () {
      this.curList = this.NoticeList;
      this.list0 = [];
      this.list1 = [];
      this.list2 = [];
      this.list3 = [];
      this.curList.forEach((notice) => {
        this.list0.push(notice);
        if (notice.type == "정보") {
          this.list1.push(notice);
        } else if (notice.type == "사이트") {
          this.list2.push(notice);
        } else if (notice.type == "기타") {
          this.list3.push(notice);
        }
      });
    },
  },
  methods: {
    ...mapActions(noticeStore, ["getAllNotice"]),
    searchN() {
      if (this.word == "") this.getAllNotice();
      else this.$store.dispatch("noticeStore/searchNotice", this.word);
    },
    regist() {
      this.$router.push({
        name: "NoticeCreate",
      });
    },
    load(val) {
      console.log("load 결과 ");
      console.log(val);
      if (val == 0) {
        this.curList = this.list0;
      } else if (val == 1) {
        this.curList = this.list1;
      } else if (val == 2) {
        this.curList = this.list2;
      } else {
        this.curList = this.list3;
      }
      this.cssActives = [false, false, false, false];
      this.cssActives[val] = !this.cssActives[val];
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
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

.active {
  color: rgb(106, 148, 238);
}

div.text {
  font-family: Verdana;
  font-size: 12px;
}

div.title {
  font-family: Verdana;
  font-size: 15px;
}

.navibar {
  font-family: Verdana;
  font-size: 15px;
}
</style>
