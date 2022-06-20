<template>
  <div>
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
      style="height: 900px"
    >
      <div class="container">
        <div class="md-layout">
          <div
            class="
              md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100
            "
          >
            <h1 class="title title_font" style="color: white">
              Your Story Starts With Us.
            </h1>
            <!-- 메인페이지 input검색 창 시작-->
            <div class="name-input">
              <input
                type="text"
                v-model="aptName"
                @keyup.enter="search"
                class="aptName-input"
                placeholder="  Search by APT name"
              />
            </div>
            <!-- 메인페이지 input검색 창 끝-->
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section">
        <div class="container">
          <div class="md-layout">
            <div
              class="
                md-layout-item md-size-100 md-xsmall-size-100
                mx-auto
                text-center
              "
            >
              <!--admin 전용-->

              <div id="boxes" v-if="isAdmin">
                <div class="box" @mouseover="mouseover" @click="moveUserList">
                  <img
                    src="@/assets/images/member_list.png"
                    alt="news"
                    class="main_img"
                  />
                  <h1 class="main_h1">Member List</h1>
                  <h3>회원목록을 확인하고 싶으면 클릭하세요.</h3>
                </div>
                <div class="box" @mouseover="mouseover" @click="moveDailyReg">
                  <img
                    src="@/assets/images/statistics.png"
                    alt="news"
                    class="main_img"
                  />
                  <h1 class="main_h1">Statistics</h1>
                  <h3>회원가입 통계를 확인하려면 클릭하세요.</h3>
                </div>
                <div
                  class="box"
                  @mouseover="mouseover"
                  @click="movesearchPopular"
                >
                  <img
                    src="@/assets/images/ranking.png"
                    alt="news"
                    class="main_img"
                  />
                  <h1 class="main_h1">Popularity Ranking</h1>
                  <h3>
                    회원들에게 인기있는 지역의 통계를<br />
                    확인하려면 클릭하세요.
                  </h3>
                </div>
              </div>
              <!--admin 전용 끝-->
              <!---->
              <div id="boxes">
                <div class="box" @mouseover="mouseover" @click="moveNews">
                  <img
                    src="@/assets/images/news.png"
                    alt="news"
                    class="main_img"
                  />
                  <h1 class="main_h1">News</h1>
                  <h3>새로운 정보를 알고싶다면 클릭하세요.</h3>
                </div>
                <div
                  class="box"
                  v-on:mouseover="mouseover"
                  @click="moveDealSearch"
                >
                  <img
                    src="@/assets/images/find_house.png"
                    alt="find_house"
                    class="main_img"
                  />
                  <h1 class="main_h1">Find</h1>
                  <h3>
                    당신에게 딱 맞는 집을 원한다면<br />
                    클릭하세요.
                  </h3>
                </div>
                <div class="box" v-on:mouseover="mouseover" @click="moveNotice">
                  <img
                    src="@/assets/images/notice.png"
                    alt="notice"
                    class="main_img"
                  />
                  <h1 class="main_h1">Notice</h1>
                  <h3>공지사항을 알고 싶으면 클릭하세요.</h3>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  components: {},
  props: {
    header: {
      type: String,
      default: require("@/assets/images/main_house8.jpg"),
    },
  },
  data() {
    return {
      user: {
        id: "",
        name: "",
        password: "",
        address: "",
        email: "",
        tel: "",
        que: "",
        ans: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo", "isAdmin"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    //메인에서 검색어 입력 후 엔터 시
    search() {
      if (this.aptName == "") alert("검색어를 입력해주세요.");
      else {
        this.$router.push({
          name: "DealSearch",
          params: { aptName: this.aptName },
        });
        // this.$router.go(0); //가지 않아서 reload를 넣엇음, 안넣어도 동작하면 빼기..
      }
    },

    //admin전용
    moveUserList() {
      this.$router.push("/admin/searchall");
    },
    moveDailyReg() {
      this.$router.push("/admin/dailyreg");
    },

    movesearchPopular() {
      this.$router.push("/admin/searchpopular");
    },
    //User전용
    moveNotice() {
      this.$router.push("/notice");
    },
    moveNews() {
      this.$router.push("/news");
    },
    moveDealSearch() {
      this.$router.push("/dealsearch");
    },
  },
};
</script>

<style>
#boxes {
  width: auto;
  display: flex;
}
.main_img {
  padding: 20px 10px;
}
#boxes .box {
  height: 450px;
  margin: 20px;
  width: 400px;
  line-height: 90px;
  text-align: center;
  border: 2px solid rgba(220, 220, 220, 0.5);
  border-radius: 1em;
}
.box:hover {
  box-shadow: 3px 3px 3px 3px grey;
  cursor: pointer;
}
.aptName-input {
  width: 700px;
  height: 50px;
  font-size: 20px;
  color: black;
  border: 2px solid rgba(220, 220, 220, 0.5);
  border-radius: 0.5em;
}
.input-name input:focus {
  outline: 2px;
  border-color: #0982f0;
}
.main_h1 {
  margin: 0px 0px 10px;
}
.title_font {
  color: darkolivegreen;
  font-size: 60px;
  text-shadow: -1px 0 beige, 0 1px whbeigeite, 1px 0 beige, 0 -1px beige;
}
</style>
