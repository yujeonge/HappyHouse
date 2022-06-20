<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="
              md-layout-item
              md-size-33
              md-small-size-66
              md-xsmall-size-100
              md-medium-size-40
              mx-auto
            "
          >
            <login-card header-color="info">
              <h1 slot="title" class="card-title">Find PW</h1>

              <!-- 아이디 찾기 같이 할꺼면
                   <v-tabs v-model="tabs" slot="title">
                <v-tab v-for="item in items" :key="item"
                  ><h1 class="card-title">
                    {{ item }}
                  </h1>
                </v-tab>
              </v-tabs> -->
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-facebook-square"></i>
              </md-button>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-twitter"></i>
              </md-button>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-google-plus-g"></i>
              </md-button>
              <p slot="description" class="description">
                Please enter your information
              </p>

              <md-field class="md-form-group" slot="inputs">
                <md-icon>face</md-icon>
                <label>UserID</label>
                <md-input v-model="finduser.id"></md-input>
              </md-field>

              <!--비밀번호 찾기 질문-->
              <md-field
                class="md-form-group"
                slot="inputs"
                style="text-align: center; padding-top: 20px"
              >
                <md-icon><b-icon icon="blank"></b-icon></md-icon>
                회원가입시 등록한 질문과 답변을 입력하세요.
              </md-field>

              <md-field class="md-form-group" slot="inputs">
                <!-- 질문 아이콘 -->
                <md-icon
                  ><b-icon
                    icon="question-circle"
                    font-scale="1"
                    variant="dark"
                  ></b-icon
                ></md-icon>
                <!-- ------------------------------------ -->
                <label for="que">Question</label>
                <md-select v-model="finduser.que" name="que" id="que">
                  <md-option value="자신의 반려동물 이름은?"
                    >자신의 반려동물 이름은?</md-option
                  >
                  <md-option value="가장 기억에 남는 선생님 성함은?"
                    >가장 기억에 남는 선생님 성함은?</md-option
                  >
                  <md-option value="인상 깊게 읽은 책 이름은?"
                    >인상 깊게 읽은 책 이름은?</md-option
                  >
                  <md-option value="자신이 가장 살고싶은 주거구역은?"
                    >자신이 가장 살고싶은 주거구역은?</md-option
                  >
                </md-select>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <!-- 답변 아이콘 -->
                <md-icon
                  ><b-icon
                    icon="check2-circle"
                    font-scale="1"
                    variant="dark"
                  ></b-icon
                ></md-icon>
                <!-- ------------------------------------ -->
                <label>Anwser</label>
                <md-input v-model="finduser.ans"></md-input>
              </md-field>
              <md-button
                slot="footer"
                class="md-simple md-info md-lg"
                @click="confirm"
              >
                find
              </md-button>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { LoginCard } from "@/components";

const memberStore = "memberStore";

export default {
  name: "MemberJoin",
  components: {
    LoginCard,
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/profile_city.jpg"),
    },
  },
  computed: {
    ...mapState(memberStore, ["isExist", "Password"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  data() {
    return {
      tabs: 0,
      items: ["아이디", "비밀번호"],
      finduser: {
        id: null,
        que: null,
        ans: null,
      },
    };
  },
  methods: {
    ...mapActions(memberStore, ["EXIST"]),

    async confirm() {
      console.log(this.finduser.que);
      //
      if (this.finduser.id == null) alert("아이디를 입력해주세요.");
      else if (this.finduser.que == null) alert("질문을 선택해 주세요");
      else if (this.finduser.ans == null) alert("답변을 입력해 주세요");
      else {
        await this.EXIST(this.finduser);

        if (!this.isExist) {
          alert("입력한 정보를 확인해 주세요.");
        } else {
          alert("비밀번호 : " + this.Password);
          this.movePage();
        }
      }
    },
    movePage() {
      this.$router.push({ name: "SignIn" });
    },
  },
};
</script>

<style></style>
