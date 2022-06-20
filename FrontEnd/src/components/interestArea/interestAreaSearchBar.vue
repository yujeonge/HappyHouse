<template>
  <div id="searchbar">
    <b-row class="mb-2 text-center">
      <b-col class="sm-3">
        <md-field>
          <b-form-select
            v-model="sidoCode"
            :options="sidos"
            @change="selectSido"
            style="
              height: 35px;
              font-size: 11px;
              font-family: 'IBMPlexSansKR-Regular';
            "
          ></b-form-select>
        </md-field>
      </b-col>
      <b-col class="sm-3">
        <md-field>
          <b-form-select
            v-model="gugunCode"
            :options="guguns"
            @change="selectGugun"
            ref="id"
            style="
              height: 35px;
              font-size: 11px;
              font-family: 'IBMPlexSansKR-Regular';
            "
          ></b-form-select>
        </md-field>
      </b-col>
      <b-col class="sm-3">
        <md-field>
          <b-form-select
            v-model="dongCode"
            :options="dongs"
            @change="selectDong"
            style="
              height: 35px;
              font-size: 11px;
              font-family: 'IBMPlexSansKR-Regular';
            "
          ></b-form-select>
        </md-field>
      </b-col>
      <md-button
        class="md-info md-sm"
        @click="save"
        style="
          height: 35px;
          margin-top: 12px;
          font-size: 12px;
          font-family: 'IBMPlexSansKR-Regular';
        "
        >저장</md-button
      >
      <b-col class="sm-3">
        <interest-Area-Popular />
      </b-col>
    </b-row>
  </div>
</template>

<script>
//시군구를 선택한 결과를 저장하는 컴포넌트
import { mapState, mapActions, mapMutations } from "vuex";
import interestAreaPopular from "@/components/interestArea/interestAreaPopular";

const houseStore = "houseStore";
const memberStore = "memberStore";
const interestAreaStore = "interestAreaStore";

export default {
  name: "interestAreaSearchBar",
  components: {
    interestAreaPopular,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      juso: "",
      currentPage: 1,
      perPage: 10,
      searchAll: -1,
      popular: "",
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(interestAreaStore, [
      "sidoName",
      "gugunName",
      "dongName",
      "popularAreas",
    ]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  created() {
    console.log(this.userInfo);
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDongCode"]),
    ...mapActions(interestAreaStore, ["registInterstArea"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
      "CLEAR_HOUSE2_LIST",
      "SET_HOUSE",
    ]),
    ...mapMutations(interestAreaStore, [
      "SET_SIDO_NAME",
      "SET_GUGUN_NAME",
      "SET_DONG_NAME",
    ]),

    selectSido() {
      this.SET_SIDO_NAME({ sidoCode: this.sidoCode, sidos: this.sidos });
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.gugunCode = null;

      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    selectGugun() {
      this.SET_GUGUN_NAME({ gugunCode: this.gugunCode, guguns: this.guguns });
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDongCode(this.gugunCode);
    },
    selectDong() {
      this.SET_DONG_NAME({ dongCode: this.dongCode, dongs: this.dongs });
    },
    save() {
      //select할때마다 얻은 text 값을 store에 저장한걸로
      //axios를 이용해서 현재 유저 id + NAME으로 선호지역 저장
      const juso = `${this.sidoName} ${this.gugunName} ${this.dongName}`;
      const dongCode = this.dongCode;

      // console.log("InterestAreasearchBar - save()");
      // console.log("juso : " + juso);
      // console.log("dongCode : " + dongCode);
      const params = {
        id: this.userInfo.id,
        juso,
        dongCode,
      };
      this.registInterstArea(params);
    },
  },
};
</script>

<style></style>
