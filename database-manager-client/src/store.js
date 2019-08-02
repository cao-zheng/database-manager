import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  // 当前的数据库连接信息
  nowConnectionInfo: {},
  // 创建新的连接信息
  newConnectionInfo:{},
  //第三层级信息
  treeThirdList:{}
}
//处理数据（状态）变化
const mutations={

}
//处理异步请求，判断，流程控制
const actions = {

}
const getters={

}

//导出store对象
export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters
});