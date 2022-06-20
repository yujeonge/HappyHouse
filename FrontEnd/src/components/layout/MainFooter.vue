<template>
  <footer
    class="footer"
    :class="{ [`footer-${type}`]: type }"
    :data-background-color="backgroundColor"
  >
    <div class="container left">
      <nav>
        <ul>
          <li>
            <router-link :to="{ name: 'Main' }" class="link hh">
              <img
                src="@/assets/images/home_dark.png"
                style="width: 20px; min-width: 20px"
              />
              HappyHouse
            </router-link>
          </li>
          <li>
            <router-link :to="{ name: 'NoticeList' }" class="link">
              Notice
            </router-link>
          </li>
          <li>
            <router-link :to="{ name: 'NewsList' }" class="link">
              News
            </router-link>
          </li>
          <li>
            <router-link :to="{ name: 'QnaList' }" class="link">
              Help
            </router-link>
          </li>
        </ul>
      </nav>
      <div class="copyright">
        &copy;
        {{ year }}, made by Kim Yoo Jeong, Lee Sang Jin
        <router-link :to="{ name: 'GitHubRepo' }" class="link">
          <img
            src="@/assets/images/github.png"
            style="width: 35px; min-width: 32px; padding: 2px"
          />
        </router-link>
      </div>
    </div>
  </footer>
</template>
<script>
export default {
  props: {
    backgroundColor: String,
    type: String,
  },
  data() {
    return {
      year: "2022",
    };
  },
};
</script>
<style>
/* nav {
  -webkit-padding-start: 0px;
} */
.hh {
  font-weight: bold;
  font-size: 18px;
  font-family: "Lucida Console", "Courier New", monospace;
  margin: 0px 20px;
}
</style>
