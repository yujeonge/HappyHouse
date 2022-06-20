<template>
  <div>
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
      style="height: 0px"
    >
      <div class="container">
        <div class="md-layout">
          <!-- <div class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100">
            <h1
              class="title"
              style="
                color: darkolivegreen;
                font-size: 65px;
                text-shadow: -1px 0 beige, 0 1px beige, 1px 0 beige, 0 -1px beige;
              "
            >
              Your Story Starts With Us.
            </h1>
          </div> -->
        </div>
      </div>
    </parallax>

    <div class="main main-raised">
      <div class="section">
        <div class="container">
          <b-row class="mb-1">
            <b-col style="text-align: left">
              <b-form @submit="onSubmit" @reset="onReset">
                <b-form-group
                  id="username-group"
                  label="작성자:"
                  label-for="username"
                  description="작성자를 입력하세요."
                >
                  <b-form-input
                    id="username"
                    :disabled="isUsername"
                    v-model="qna.username"
                    type="text"
                    required
                    placeholder="작성자 입력..."
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  id="subject-group"
                  label="제목:"
                  label-for="subject"
                  description="제목을 입력하세요."
                >
                  <b-form-input
                    id="subject"
                    v-model="qna.subject"
                    type="text"
                    required
                    placeholder="제목 입력..."
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  id="content-group"
                  label="내용:"
                  label-for="content"
                >
                  <b-form-textarea
                    id="content"
                    v-model="qna.content"
                    placeholder="내용 입력..."
                    rows="10"
                    max-rows="15"
                  ></b-form-textarea>
                </b-form-group>
                <div class="btnWrap">
                  <span class="addContainer" @click="addQna">
                    <md-button class="md-default">등록</md-button> </span
                  >&nbsp;&nbsp;
                  <span class="addContainer" @click="back">
                    <md-button class="md-default">뒤로</md-button>
                  </span>
                </div>
              </b-form>
            </b-col>
          </b-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "QnAInputItem",
  data() {
    return {
      qna: {
        articleno: 0,
        username: "", //userid처럼 사용할것
        subject: "",
        content: "",
      },
      isUsername: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      http.get(`/qna/detail/${this.$route.params.qnano}`).then(({ data }) => {
        // this.article.articleno = data.article.articleno;
        // this.article.username = data.article.username;
        // this.article.subject = data.article.subject;
        // this.article.content = data.article.content;
        this.qna = data;
      });
      this.isUsername = true;
    }
  },
  methods: {
    addQna() {
      console.log("addQna()호출");
      //   let err = true;
      //   let msg = "";
      if (
        this.qna.username == "" &&
        this.qna.subject == "" &&
        this.qna.content == ""
      ) {
        console.log("빈칸없이 입력");
        alert("빈 칸 없이 입력해주세요.");
      }
      //   !this.qna.username &&
      //     ((msg = "작성자 입력해주세요"),
      //     (err = false),
      //     this.$refs.username.focus());
      //   err &&
      //     !this.qna.subject &&
      //     ((msg = "제목 입력해주세요"),
      //     (err = false),
      //     this.$refs.subject.focus());
      //   err &&
      //     !this.qna.content &&
      //     ((msg = "내용 입력해주세요"),
      //     (err = false),
      //     this.$refs.content.focus());

      //   if (!err) alert(msg);
      else {
        this.type === "regist" ? this.registqna() : this.modifyqna();
      }
    },
    back() {
      console.log("back()호출");
      this.$router.push("/list");
    },
    // onSubmit(event) {
    //   event.preventDefault();
    //   let err = true;
    //   let msg = "";
    //   !this.qna.username &&
    //     ((msg = "작성자 입력해주세요"),
    //     (err = false),
    //     this.$refs.username.focus());
    //   err &&
    //     !this.qna.subject &&
    //     ((msg = "제목 입력해주세요"),
    //     (err = false),
    //     this.$refs.subject.focus());
    //   err &&
    //     !this.qna.content &&
    //     ((msg = "내용 입력해주세요"),
    //     (err = false),
    //     this.$refs.content.focus());

    //   if (!err) alert(msg);
    //   else this.type === "regist" ? this.registqna() : this.modifyqna();
    // },
    // onReset(event) {
    //   event.preventDefault();
    //   this.qna.qnano = 0;
    //   this.qna.subject = "";
    //   this.qna.content = "";
    //   this.$router.push("/list");
    // },
    registqna() {
      console.log("registqna()호출");
      console.log("this.qna.content" + this.qna.content);
      console.log(this.qna);
      http
        .post(`/qna/regist`, null, {
          params: {
            subject: this.qna.subject,
            username: this.qna.username,
            content: this.qna.content,
          },
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyqna() {
      console.log("modifyqna");
      console.log(this.qna.articleno);
      http
        .put(`/qna/modify/${this.qna.articleno}`, null, {
          params: {
            subject: this.qna.subject,
            username: this.qna.username,
            content: this.qna.content,
          },
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "QnAList" });
        });
    },
    moveList() {
      console.log(" moveList()호출");

      this.$router.push("/list");
    },
  },
};
</script>

<style></style>
