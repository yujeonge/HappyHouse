<template>
  <b-tr>
    <b-td>{{ articleno }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'QnaDetail', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td>{{ username }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
    <b-td v-if="article2.reply == null">답변대기</b-td>
    <b-td v-else>답변완료</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";
import http from "@/api/http";

export default {
  name: "QnaListItem",
  data() {
    return {
      article2: {
        reply: "",
      },
    };
  },
  props: {
    articleno: Number,
    username: String,
    subject: String,
    content: String,
    regtime: String,
  },
  created() {
    http.get(`/qna/ans/${this.articleno}`).then(({ data }) => {
      // console.log(data);
      this.article2.reply = data.content;
      // console.log(this.article2.reply);
    });
    // http.get(`/qna/ans/${this.$route.params.articleno}`).then(({ data }) => {
    //   console.log(data);
    //   this.article2.reply = data.content;
    //   console.log(this.article2.reply);
    // });
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
