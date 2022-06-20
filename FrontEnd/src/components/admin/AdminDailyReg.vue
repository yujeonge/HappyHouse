<template>
  <div>
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
      style="height: 300px"
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
          <div class="md-layout">
            <div
              class="
                md-layout-item md-size-66 md-xsmall-size-100
                mx-auto
                text-center
              "
            >
              <h1 class="title text-center">Statistics</h1>
            </div>
          </div>

          <br />
          <br />

          <b-row>
            <b-col cols="6">
              <section>
                <b-table
                  id="newstable"
                  head-variant="light"
                  :items="dailyList"
                  :fields="fields"
                  :per-page="perPage"
                  :current-page="currentPage"
                  responsive="sm"
                  style="
                    font-size: 14px;
                    font-family: 'IBMPlexSansKR-Regular';
                    margin: auto;
                  "
                >
                  <template #cell(일자)="data">
                    {{ data.item.regdate }}
                  </template>
                  <template #cell(가입회원수)="data">
                    {{ data.item.cnt }}
                  </template>
                </b-table>
              </section>
            </b-col>
            <b-col cols="6">
              <line-Chart
                :chartData="{ labels: labels, datasets: datasets }"
              ></line-Chart>
            </b-col>
          </b-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import http from "@/api/http";
import moment from "moment";
import LineChart from "@/components/chart/Line";
const memberStore = "memberStore";

export default {
  name: "UserList",
  components: {
    LineChart,
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/admin_bg.jpg"),
    },
  },

  data() {
    return {
      fields: ["일자", "가입회원수"],
      currentPage: 1,
      perPage: 10,
      users: [],
      keyword: "",
      deleteId: "",
      dailyList: [],
      regdate: [],
      labels: [],
      datasets: [
        {
          label: "지난 일주일간 가입한 회원 수입니다.",
          backgroundColor: "#f87979",
          data: [],
        },
      ],
    };
  },
  created() {
    http.get(`/user/dailyreg`).then(({ data }) => {
      this.dailyList = data;
      // console.log(this.dailyList);
      // dailyList의 regdate를 x, cnt를 y축으로하는 data 생성
      this.dailyList.forEach((el) => {
        this.labels.push(el.regdate);
        this.datasets[0].data.push(el.cnt);
      });
      this.labels.reverse();
      this.datasets[0].data.reverse();
    });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"], ["users"], ["DELETEUSER"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  methods: {
    ...mapActions({}),
    async deleteone(id) {
      if (confirm(id + " 을(를) 삭제하시겠습니까?")) {
        http.delete(`/user/delete/${id}`).then(({ data }) => {});
        alert("삭제가 완료 되었습니다."); //나중에는 에러났을때 다르게 뜨게 수정하기...
        http.get(`/user/list`).then(({ data }) => {
          this.users = data;
          console.log(this.users);
        });
      }
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
</style>
