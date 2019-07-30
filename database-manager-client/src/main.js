import Vue from 'vue'
import App from './App.vue'
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import params from './common/params'
import axios from 'axios'

import store from './store'

Vue.use(iView);
Vue.use(ElementUI);

Vue.prototype.params = params
Vue.prototype.$axios= axios

Vue.config.productionTip = false

new Vue({
  store,
  render: h => h(App),
}).$mount('#app')
