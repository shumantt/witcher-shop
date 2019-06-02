<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
/* eslint-disable */
import Home from './components/Home.vue'
import Authorization from './components/Authorization.vue'

export default {
  name: 'app',
  components: {
    Home,
    Authorization
  }
}
</script>

<style>

</style>
