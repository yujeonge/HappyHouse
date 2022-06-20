<template>
  <div>
    <div>
      <b-row align-h="center">
        <interest-Area-Search-Bar />
      </b-row>

      <!-- <div class="row"> -->
      <!-- <div class="col-xl-7 mb-5 mb-xl-0"> -->
      <!-- <div class="card bg-default"> -->
      <!-- <ka-kao-map ref="KaKaoMap" /> -->
      <!-- </div> -->
      <!-- </div> -->
      <!-- <div class="col-xl-5"><interest-Area-List /></div> -->
      <!-- </div> -->
      <b-row align-h="center">
        <b-col cols="8"><ka-kao-map ref="KaKaoMap" /></b-col>
        <b-col cols="4"><interest-Area-List /> </b-col>
      </b-row>
      <b-row align-h="center">
        <b-col cols="8"><interest-Area-Business /> </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import interestAreaSearchBar from "@/components/interestArea/interestAreaSearchBar";
import KaKaoMap from "@/views/main/KaKaoMap.vue";
import interestAreaList from "@/components/interestArea/interestAreaList";
import interestAreaBusiness from "@/components/interestArea/interestAreaBusiness";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  components: {
    interestAreaSearchBar,
    interestAreaList,
    KaKaoMap,
    interestAreaBusiness,
  },
  created() {
    if (this.userInfo == null) {
      alert("로그인 후 확인가능합니다.");
      this.$router.push("/user/signin");
    }
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style></style>
