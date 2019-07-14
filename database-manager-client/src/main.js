import Vue from 'vue'
import App from './App.vue'
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import params from './common/params'
import axios from 'axios'

Vue.use(iView);

Vue.prototype.params = params
Vue.prototype.$axios= axios

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
