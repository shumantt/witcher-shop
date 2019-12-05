import Vue from 'vue'
import App from '@/App'
import router from '@/router.js'
import store from '@/store/store.js'
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default.css'
import 'material-icons/iconfont/material-icons.css'

Vue.use(VueMaterial)
Vue.config.productionTip = false

new Vue({
  router: router,
  store: store,
  render: h => h(App),
}).$mount('#app')
