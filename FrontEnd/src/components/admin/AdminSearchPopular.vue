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
                md-layout-item md-size-66 md-xsmall-size-100
                mx-auto
                text-center
              "
            >
              <h1 class="title text-center">Popularity Ranking</h1>
            </div>
          </div>

          <br />
          <br />

          <b-row>
            <b-col cols="7">
              <section>
                <b-table
                  id="newstable"
                  head-variant="light"
                  :items="searchPopular"
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
                  <template #cell(지역)="data">
                    {{ data.item.juso }}
                  </template>
                  <template #cell(회원수)="data">
                    {{ data.item.count }}
                  </template>
                </b-table>
              </section>
            </b-col>
            <b-col cols="5">
              <doughnut-Chart
                :chartData="{ labels: labels, datasets: datasets }"
              ></doughnut-Chart>
            </b-col>
          </b-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/api/http";
import moment from "moment";
import DoughnutChart from "@/components/chart/Doughnut";

const memberStore = "memberStore";

export default {
  name: "UserList",
  components: {
    DoughnutChart,
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/admin_bg.jpg"),
    },
  },

  data() {
    return {
      fields: ["지역", "회원수"],
      currentPage: 1,
      perPage: 10,
      keyword: "",
      deleteId: "",
      searchPopular: [],
      // labels: [],
      labels: [],
      datasets: [
        {
          // label: "지난 일주일간 가입한 회원 수입니다.",
          backgroundColor: [
            "#41B883",
            "#E46651",
            "#00D8FF",
            "#DD1B16",
            "#C2DED1",
            "#ECE5C7",
          ],
          // data: [],
          data: [],
        },
      ],
    };
  },
  created() {
    //시+구 까지 인기 지역 6개를 받아오기

    //첫번째는 전체를 받아옴, 1~5위의 인기지역을 빼서 others 데이터 만들기
    http.get(`/interest/searchPopular2/6`).then(({ data }) => {
      let total = data[0].count;
      let sum = 0;
      this.searchPopular = data;
      console.log(this.searchPopular);
      this.searchPopular.forEach((el) => {
        this.labels.push(el.juso);
        this.datasets[0].data.push(el.count);
        sum += el.count;
      });
      this.labels.push("Others");
      this.datasets[0].data.push(total - sum);
      this.labels.splice(0, 0);
      this.datasets[0].data.splice(0, 0);
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
  methods: {},
};
</script>

<style scoped>
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
