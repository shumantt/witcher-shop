<template>
  <div class="page-container">
    <md-app>
      <md-app-drawer md-permanent="full">
        <md-toolbar class="md-large md-primary" md-elevation="0">
          <div>
            <md-avatar class="md-avatar-icon md-large md-accent">
              <img :src="img" alt="Avatar">
            </md-avatar>
          </div>
          <div class="user-name">{{userName}}</div>
        </md-toolbar>

        <router-view name="menu"></router-view>
      </md-app-drawer>

      <md-app-content>
        <router-view></router-view>
      </md-app-content>
    </md-app>
  </div>
</template>

<script>
/* eslint-disable */
import img from '@/assets/witcher-face.jpg';
import Receipts from '@/components/resources/Receipts.vue';
import Consumption from '@/components/resources/Consumption.vue';
import Order from '@/components/client/Order.vue';
import Report from '@/components/managing/Report.vue';
import Employees from '@/components/managing/Employees.vue'
export default {
  name: 'Home',
  components: {
    Receipts,
    Consumption,
    Order,
    Report,
    Employees
  },

  data() {
    return {
    };
  },
  computed: {
    userName() {
      return this.$store.state.user.name;
    },

    img() {
      return this.$store.state.user.pictureUrl;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  .md-app {
    border: 1px solid rgba(#000, .12);
    min-height: 100vh;
  }

   // Demo purposes only
  .md-drawer {
    width: 230px;
    max-width: calc(100vw - 125px);
  }

  .user-name {
    padding-left: 15px;
  }
</style>
