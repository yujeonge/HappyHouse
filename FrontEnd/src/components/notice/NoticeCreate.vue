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
            ></div>
          </div>

          <md-card>
            <md-card-header data-background-color="blue">
              <h1 class="text-center" style="font: Verdana">공지사항</h1>
              <!-- <h3 class="text-center" style="font-family: Verdana"> -->
              <!-- 공지사항을 작성하세요. -->
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
                  <md-input v-model="notice.title"></md-input>
                </md-field>
                <md-field>
                  <label for="type">종류</label>
                  <md-select v-model="notice.type" name="type" id="type">
                    <md-option value="정보">정보</md-option>
                    <md-option value="사이트">사이트</md-option>
                    <md-option value="기타">기타</md-option>
                  </md-select>
                </md-field>

                <md-field>
                  <label>내용</label>
                  <md-textarea
                    style="height: 500px"
                    v-model="notice.content"
                  ></md-textarea>
                </md-field>
              </div>

              <div class="btnWrap">
                <span class="addContainer" @click="create">
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
</template>

<script>
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";
const noticeStore = "noticeStore";

export default {
  props: {
    header: {
      type: String,
      default: require("@/assets/images/hero_bg_4.jpg"),
    },
  },
  data() {
    return {
      notice: {
        title: "",
        content: "",
        regtime: "",
        type: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    ...mapActions(noticeStore, ["createNotice"]),
    create() {
      // 타입도 나중에 추가하기
      if (this.notice.title != "" && this.notice.content != "") {
        this.createNotice(this.notice);
        alert("등록되었습니다.");
        this.$router.go(-1);
      } else {
        alert("빈 칸 없이 입력해주세요.");
      }
    },
    back() {
      this.$router.go(-1);
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
