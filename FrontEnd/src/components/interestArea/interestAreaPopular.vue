<template>
  <div>
    <div
      style="
        margin-left: 10%;
        float: left;
        font-size: 15px;
        font-weight: bold;
        color: steelblue;
        font-family: 'IBMPlexSansKR-Regular';
      "
    >
      인기 >
    </div>

    <vue-swimlane
      :words="populars"
      :circular="false"
      :rows="1"
      :scale="1"
      :transitionDuration="750"
      :transitionDelay="1200"
      :pauseOnHover="true"
      @click="clickApt"
      style="
        width: 400px;
        margin-top: 15px;
        color: steelblue;
        font-weight: 450;
        font-family: 'IBMPlexSansKR-Regular';
      "
    ></vue-swimlane>
  </div>
</template>
<script>
import { mapState, mapActions, mapMutations } from "vuex";

const interestAreaStore = "interestAreaStore";

export default {
  name: "interestAreaPopular",
  created() {
    // console.log("interestAreapopular - created()");
    this.populars = [];
    this.getPopularAreas();
    this.popularAreas.forEach((el, index) => {
      this.populars.push(`${index + 1}. ${el.juso}`);
    });
  },
  computed: {
    ...mapState(interestAreaStore, ["area", "popularAreas"]),
  },
  data() {
    return {
      populars: [],
      fields: [
        {
          key: "area",
          label: "인기 지역",
        },
      ],
    };
  },
  methods: {
    ...mapActions(interestAreaStore, ["getPopularAreas"]),
    ...mapMutations(interestAreaStore, ["SET_AREA"]),
    clickApt(area) {
      console.log("clickApt 작동!!!");
    },
  },
};
</script>

<style></style>
