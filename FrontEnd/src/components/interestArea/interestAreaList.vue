<template>
  <div>
    <div class="overflow-auto" v-if="areas.length != 0">
      <b-table
        hover
        id="housetable"
        head-variant="light"
        v-b-toggle.sidebar-1
        :items="areas"
        :fields="fields"
        @row-clicked="selectArea"
        style="font-size: 11px; font-family: 'IBMPlexSansKR-Regular'"
      >
        <template #head(juso)="data">
          <span class="text-info">{{ data.label.toUpperCase() | juso }}</span>
        </template>
        <template #cell(juso)="data">
          {{ data.item.juso }}
          <b-icon
            class="ml-4"
            icon="x-circle"
            scale="2"
            variant="danger"
            @click="deleteCurrentArea(data.item)"
          ></b-icon>
        </template>
      </b-table>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const interestAreaStore = "interestAreaStore";
const memberStore = "memberStore";

export default {
  name: "interestAreaList",
  data() {
    return {
      fields: ["juso"],
    };
  },
  created() {
    this.getAreas(this.userInfo.id);
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(interestAreaStore, ["areas", "area"]),
  },
  methods: {
    ...mapActions(interestAreaStore, ["getAreas", "deleteArea"]),
    ...mapMutations(interestAreaStore, ["SET_AREA"]),

    // 클릭한 아이템 삭제 이후 다시 갱신하기
    deleteCurrentArea(item) {
      console.log("intersetAreaSearchBar - deleteArea");
      console.log("item", item);
      this.deleteArea(item);
    },
    //필요하면 현재 누른 area에 대해서 지도 이동하는거 호출하는 함수 구현하기
    selectArea(item) {
      // console.log("intersetAreaSearchBar - selectArea");
      // console.log("juso  : ");
      console.log(item.juso);
      this.SET_AREA(item);
    },
  },
  filters: {
    juso() {
      return "주소";
    },
  },
};
</script>

<style></style>
