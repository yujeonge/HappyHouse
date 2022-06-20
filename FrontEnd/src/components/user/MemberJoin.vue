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
              <h1 slot="title" class="card-title">Sign Up</h1>
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
                Welcome to HappyHouse!
              </p>

              <md-field class="md-form-group" slot="inputs">
                <md-icon>face</md-icon>
                <label>UserID</label>
                <md-input v-model="user.id"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Password</label>
                <md-input v-model="user.password" type="password"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>person</md-icon>
                <label>Your Name</label>
                <md-input v-model="user.name"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>Your email</label>
                <md-input v-model="user.email"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>phone</md-icon>
                <label>Your Phone</label>
                <md-input v-model="user.tel"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon
                  ><b-icon
                    icon="question-circle"
                    font-scale="1"
                    variant="dark"
                  ></b-icon
                ></md-icon>
                <label for="que">Question</label>
                <md-select v-model="user.que" name="que" id="que">
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
                <md-icon
                  ><b-icon
                    icon="check2-circle"
                    font-scale="1"
                    variant="dark"
                  ></b-icon
                ></md-icon>
                <label>Anwser</label>
                <md-input v-model="user.ans"></md-input>
              </md-field>

              <!-- <md-field>
              <label for="type">종류</label>
              <md-select v-model="notice.type" name="type" id="type">
                <md-option value="정보">정보</md-option>
                <md-option value="사이트">사이트</md-option>
                <md-option value="기타">기타</md-option>
              </md-select>
                        </md-field> -->

              <md-button
                slot="footer"
                class="md-simple md-info md-lg"
                @click="confirm"
              >
                Get Started
              </md-button>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
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
    ...mapState(memberStore, ["registStatus"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  data() {
    return {
      user: {
        id: null,
        password: null,
        name: null,
        email: null,
        tel: null,
        que: null,
        ans: null,
      },
    };
  },
  methods: {
    ...mapActions(memberStore, ["REGISTER"]),
    async confirm() {
      if (this.user.id == null) alert("아이디를 입력해주세요.");
      else if (this.user.password == null) alert("비밀번호를 입력해주세요.");
      else if (this.user.name == null) alert("이름을 입력해주세요.");
      else if (this.user.email == null) alert("이메일을 입력해 주세요");
      else if (this.user.tel == null) alert("전화번호를 입력해 주세요");
      else if (this.user.que == null)
        alert("비밀번호찾기 질문을 입력해 주세요");
      else if (this.user.ans == null)
        alert("비밀번호찾기 답변을 입력해 주세요");
      else {
        await this.REGISTER(this.user);
        console.log(this.user);

        if (this.registStatus == true) {
          alert("가입이 완료 되었습니다.");
          this.movePage();
        } else {
          alert("중복된 아이디 입니다.");
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
