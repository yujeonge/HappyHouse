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
                md-layout-item md-size-100 md-xsmall-size-100
                mx-auto
                text-center
              "
            >
              <h1 class="title text-center">Explore Your Areas of Interest</h1>
              <interest-area-main ref="interestAreaMain" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import interestAreaMain from "@/components/interestArea/interestAreaMain";

export default {
  name: "interestArea",
  components: {
    interestAreaMain,
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/background_img2.jpg"),
    },
  },
  data() {},
  created() {},
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
};
</script>

<style></style>
