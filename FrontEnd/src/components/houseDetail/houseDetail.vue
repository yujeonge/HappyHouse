<template>
  <div>
    
    <table class="tbAdd">
      <colgroup>
        <col width="15%" />
        <col width="*" />
      </colgroup>
      <tr>
        <th>거래금액</th>
        <td>{{ house.거래금액 }}</td>
      </tr>
      <tr>
        <th>건축년도</th>
        <td>{{ house.건축년도 }}</td>
      </tr>
      
    </table>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  data() {
    return {
      items: [this.house],
      fields: [
        {
          key: "거래금액",
          label: "거래금액",
        },
        {
          key: "건축년도",
          label: "건축년도",
        },
        {
          key: "년",
          label: "년",
        },
        {
          key: "월",
          label: "월",
        },
        {
          key: "일",
          label: "일",
        },
        {
          key: "도로명",
          label: "도로명",
        },
        {
          key: "도로명건물본번호코드",
          label: "건물번호",
        },
        {
          key: "법정동",
          label: "법정동",
        },
        {
          key: "전용면적",
          label: "전용면적",
        },
        {
          key: "지번",
          label: "지번",
        },
        {
          key: "도로명",
          label: "도로명",
        },
      ],
    };
  },
};
</script>

<style></style>
