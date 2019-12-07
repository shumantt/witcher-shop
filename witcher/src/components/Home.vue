<template>
  <div class="page-container">
    <md-app>
      <md-app-drawer md-permanent="full">
        <Menu/>
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
import Employees from '@/components/managing/Employees.vue';
import Menu from '@/components/menu/Menu.vue';
export default {
  name: 'Home',
  components: {
    Receipts,
    Consumption,
    Order,
    Report,
    Employees,
    Menu
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  .md-app {
    border: 1px solid rgba(#000, .12);
    min-height: 100vh;
  }
</style>
