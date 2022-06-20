import Vue from "vue";
import VueRouter from "vue-router";

import Main from "@/views/main/Main.vue";

import houseDetail from "@/components/houseDetail/houseDetail.vue";

import MemberLogin from "@/components/user/MemberLogin.vue";
import MemberJoin from "@/components/user/MemberJoin.vue";
import MemberMyPage from "@/components/user/MemberMyPage.vue";
import MemberFind from "@/components/user/MemberFind.vue";

import QnaInput from "@/components/qna/QnaInput.vue";
import QnaList from "@/components/qna/QnaList.vue";
import QnaReply from "@/components/qna/QnaReply.vue";
import QnaDetail from "@/components/qna/QnaDetail.vue";
import QnaModify from "@/components/qna/QnaModify.vue";

import NewsList from "@/views/news/NewsList.vue";

import NoticeList from "@/views/main/NoticeList.vue";
import NoticeCreate from "@/components/notice/NoticeCreate.vue";
import NoticeDetail from "@/components/notice/NoticeDetail.vue";

import InterestArea from "@/views/main/InterestArea.vue";

import store from "@/store/index.js";

import MainNavbar from "@/components/layout/MainNavbar.vue";
import MainFooter from "@/components/layout/MainFooter.vue";

import DealSearch from "@/views/main/DealSearch.vue";

import AdminDailyReg from "@/components/admin/AdminDailyReg.vue"
import AdminMemberSearchAll from "@/components/admin/AdminMemberSearchAll.vue"
import AdminSearchPopular from "@/components/admin/AdminSearchPopular.vue"

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "SignIn" });
    router.push({ name: "SignIn" });
  } else {
    console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Main",
    components: { default: Main, header: MainNavbar, footer: MainFooter },
    props: {
      header: { colorOnScroll: 400 },
    },
  },
  {
    path: "/dealsearch/:aptName?",
    name: "DealSearch",
    components: { default: DealSearch, header: MainNavbar, footer: MainFooter },
    props: {
      header: { colorOnScroll: 400 },
    },
  },
  {
    path: "/house",
    name: "houseDetail",
    component: houseDetail,
  },

  {
    path: "/user/signin",
    name: "SignIn",
    components: { default: MemberLogin, header: MainNavbar },
    props: {
      header: { colorOnScroll: 400 },
    },
  },
  {
    path: "/user/signup",
    name: "SignUp",
    components: { default: MemberJoin, header: MainNavbar },
    props: {
      header: { colorOnScroll: 400 },
    },
  },
  {
    path: "/user/mypage",
    name: "MyPage",
    components: { default: MemberMyPage, header: MainNavbar },
    props: {
      header: { colorOnScroll: 400 },
    },
  },
  {
    path: "/user/find",
    name: "Find",
    components: { default: MemberFind, header: MainNavbar },
    props: {
      header: { colorOnScroll: 400 },
    },
  },
  {
    path: "/admin/searchall",
    name: "All",
    components: {
      default: AdminMemberSearchAll,
      header: MainNavbar,
      footer: MainFooter,
    },
    props: {
      header: { colorOnScroll: 400 },
    },
  },
  {
    path: "/admin/dailyreg",
    name: "Daily",
    components: {
      default: AdminDailyReg,
      header: MainNavbar,
      footer: MainFooter,
    },
    props: {
      header: { colorOnScroll: 400 },
    },
  },
  {
    path: "/admin/searchpopular",
    name: "SearchPopular",
    components: {
      default: AdminSearchPopular,
      header: MainNavbar,
      footer: MainFooter,
    },
    props: {
      header: { colorOnScroll: 400 },
    },
  },

  {
    path: "/list",
    name: "QnaList",
    components: { default: QnaList, header: MainNavbar, footer: MainFooter },
    props: {
      header: { colorOnScroll: 150 },
    },
  },
  {
    path: "/input",
    name: "QnaInput",
    components: { default: QnaInput, header: MainNavbar, footer: MainFooter },
    props: {
      header: { colorOnScroll: 150 },
    },
  },
  {
    path: "/reply",
    name: "QnaReply",
    components: { default: QnaReply, header: MainNavbar, footer: MainFooter },
    props: {
      header: { colorOnScroll: 150 },
    },
  },
  {
    path: "/detail/:num",
    name: "QnaDetail",
    components: { default: QnaDetail, header: MainNavbar, footer: MainFooter },
    props: {
      header: { colorOnScroll: 150 },
    },
  },
  {
    path: "/modify/",
    name: "QnaModify",
    components: { default: QnaModify, header: MainNavbar, footer: MainFooter },
    props: {
      header: { colorOnScroll: 150 },
    },
  },
  {
    path: "/news",
    name: "NewsList",
    components: { default: NewsList, header: MainNavbar, footer: MainFooter },
    props: {
      header: { colorOnScroll: 150 },
    },
  },
  {
    path: "/notice",
    name: "NoticeList",
    components: { default: NoticeList, header: MainNavbar, footer: MainFooter },
    props: {
      header: { colorOnScroll: 150 },
    },
  },
  {
    path: "/notice/create",
    name: "NoticeCreate",
    components: {
      default: NoticeCreate,
      header: MainNavbar,
      footer: MainFooter,
    },
    props: {
      header: { colorOnScroll: 150 },
    },
  },
  {
    path: "/notice/detail/:no",
    name: "NoticeDetail",
    components: {
      default: NoticeDetail,
      header: MainNavbar,
      footer: MainFooter,
    },
    props: {
      header: { colorOnScroll: 150 },
    },
  },
  {
    path: "/intrestarea",
    name: "InterestArea",
    components: {
      default: InterestArea,
      header: MainNavbar,
      footer: MainFooter,
    },
    props: {
      header: { colorOnScroll: 300 },
    },
  },
  {
    //나중에 리포지터리 만들면 변경
    path: "/github",
    name: "GitHubRepo",
    beforeEnter() {
      location.href = "http://github.com";
    },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
