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
              <h1 class="title text-center">Member List</h1>
            </div>
          </div>
          <!-- <b-row class="mt-4 mb-4 text-center">
            <b-col></b-col>
            <b-col class="sm-3">
              <md-field>
                <md-icon>search</md-icon>
                <md-input
                  v-model="keyword"
                  style="font-family: Verdana"
                  placeholder="Search"
                  @keyup.enter="search"
                ></md-input>
              </md-field>
            </b-col>
            <b-col class="sm-3">
              <md-button style="float: left" class="md-info" @click="search"
                >검색</md-button
              >
            </b-col>
          </b-row> -->
          <br />
          <br />

          <section>
            <b-table
              id="newstable"
              head-variant="light"
              :items="users"
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
              <template #cell(아이디)="data">
                {{ data.item.id }}
              </template>
              <template #cell(비밀번호)="data">
                {{ data.item.password }}
              </template>
              <template #cell(이름)="data">
                {{ data.item.name }}
              </template>
              <template #cell(전화번호)="data">
                {{ data.item.tel }}
              </template>
              <template #cell(이메일)="data">
                {{ data.item.email }}
              </template>
              <template #cell(비밀번호찾기질문)="data">
                {{ data.item.que }}
              </template>
              <template #cell(비밀번호찾기답변)="data">
                {{ data.item.ans }}
              </template>

              <template #cell(삭제)="data">
                <input
                  v-if="data.item.id != 'admin'"
                  type="button"
                  class="md-simple md-lg"
                  @click="deleteone(data.item.id)"
                  value="회원삭제"
                />
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
              :total-rows="users.length"
              :per-page="perPage"
              aria-controls="newstable"
            ></b-pagination>
          </section>

          <!--    <div>
            <b-col v-if="users.length">
              <b-table-simple hover responsive>
                <b-thead head-variant="light">
                  <b-tr>
                    <b-th>id</b-th>
                    <b-th>password</b-th>
                    <b-th>name</b-th>
                    <b-th>tel</b-th>
                    <b-th>email</b-th>
                    <b-th>비밀번호 찾기 질문</b-th>
                    <b-th>답변</b-th>
                    <b-th></b-th>
                  </b-tr>
                </b-thead>

                <tbody v-for="user in users" :key="user.userid" v-bind="user">
                  <b-tr>
                    <b-td>{{ user.id }}</b-td>
                    <b-td>{{ user.password }}</b-td>
                    <b-td>{{ user.name }}</b-td>
                    <b-td>{{ user.email }}</b-td>
                    <b-td>{{ user.tel }}</b-td>
                    <b-td>{{ user.que }}</b-td>
                    <b-td>{{ user.ans }}</b-td>
                    <b-td>
                      <input
                        type="button"
                        class="md-simple md-success md-lg checkBtn"
                        @click="deleteone"
                        value="회원삭제"
                      />
                    </b-td>
                  </b-tr>
                </tbody>



              </b-table-simple>
            </b-col>
            <div>
              <b-pagination
                v-model="currentPage"
                :total-rows="users.length"
                :per-page="perPage"
                aria-controls="qnatable"
              ></b-pagination>
            </div>
          </div> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import http from "@/api/http";
import moment from "moment";

const memberStore = "memberStore";

export default {
  name: "UserList",
  props: {
    header: {
      type: String,
      default: require("@/assets/images/admin_bg.jpg"),
    },
  },

  data() {
    return {
      fields: [
        "아이디",
        "비밀번호",
        "이름",
        "전화번호",
        "이메일",
        "비밀번호찾기질문",
        "비밀번호찾기답변",
        "삭제",
      ],
      currentPage: 1,
      perPage: 10,
      users: [],
      keyword: "",
      deleteId: "",
    };
  },
  created() {
    http.get(`/user/list`).then(({ data }) => {
      this.users = data;
      console.log(this.users);
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
