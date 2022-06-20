<template>
  <div class="wrapper">
    <div id="javascriptComponents">
      <div class="md-layout">
        <div class="md-layout-item md-small-size-100">
          <div class="md-layout">
            <div class="md-layout-item md-size-33">
              <div class="md-success md-block" @click="classicModal = true">
                SIGN IN
              </div>
              <modal v-if="classicModal" @close="classicModalHide">
                <login-card header-color="green" slot="body">
                  <h1 slot="title" class="card-title">Sign In</h1>

                  <p slot="description" class="description">
                    Whelcome to Happy House
                  </p>

                  <md-field class="md-form-group" slot="inputs">
                    <md-icon>face</md-icon>
                    <label>UserID</label>
                    <md-input v-model="user.id"></md-input>
                  </md-field>
                  <md-field class="md-form-group" slot="inputs">
                    <md-icon>lock_outline</md-icon>
                    <label>Password</label>
                    <md-input
                      v-model="user.password"
                      type="password"
                      @keyup.enter="confirm"
                    ></md-input>
                  </md-field>
                  <!-- <md-button
                           slot="footer"
                         class="md-simple md-success md-lg"
                         @click="confirm"
                        >
                       Sign In
                       </md-button> -->

                  <md-button
                    slot="footer"
                    class="md-simple md-success md-lg"
                    @click="confirm"
                    >Sign In</md-button
                  >
                  <p
                    slot="add"
                    class="description"
                    @click="movePage"
                    style="cursor: pointer"
                  >
                    <modal-join>Sign Up</modal-join>
                  </p>
                  <p slot="add" class="description">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  </p>
                  <p
                    slot="add"
                    class="description"
                    @click="moveFind"
                    style="cursor: pointer"
                  >
                    Forgot your password?
                  </p>
                </login-card>
              </modal>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Modal } from "@/components";
import { mapState, mapActions } from "vuex";
import { LoginCard } from "@/components";
import ModalJoin from "@/components/modal/ModalJoin";

const memberStore = "memberStore";

export default {
  components: {
    LoginCard,
    Modal,
    ModalJoin,
  },
  data() {
    return {
      classicModal: false,
      user: {
        id: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },

  methods: {
    classicModalHide() {
      this.classicModal = false;
    },
    ...mapActions(memberStore, ["LOGIN"]),
    async confirm() {
      if (this.user.id == null) alert("아이디를 입력해주세요.");
      else if (this.user.password == null) alert("비밀번호를 입력해주세요.");
      else {
        await this.LOGIN(this.user);
        this.classicModal = false;
        if (this.isLogin) {
          this.$router.reload();
        } else alert("아이디 또는 비밀번호를 확인해주세요.");
      }
    },

    removeNotify(e, notifyClass) {
      var target = e.target;
      while (target.className.indexOf(notifyClass) === -1) {
        target = target.parentNode;
      }
      return target.parentNode.removeChild(target);
    },
    moveFind() {
      this.$router.push({ name: "Find" });
    },
  },
};
</script>
