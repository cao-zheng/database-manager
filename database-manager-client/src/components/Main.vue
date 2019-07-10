<!-- 主界面布局 -->
<template>
    <div class="layout">
        <Header :style="{background: '#fff', 'border-bottom': '1px solid #DEDEDE', height: '7vh'}">
          <HeaderMenu></HeaderMenu>
        </Header>
        <Layout :style="{height: '89vh'}">
            <Sider collapsible :collapsed-width="20"  v-model="isCollapsed" :style="{background: '#fff', 'border-right': '1px solid #DEDEDE'}">
                左侧菜单
            </Sider>
            <Layout :style="{height: '93vh'}">
                <Content>
                    主题内容
                </Content>
                <Footer :style="{'border-top': '1px solid #DEDEDE', height: '3vh'}">
                  状态栏
                </Footer>
            </Layout>
        </Layout>
    </div>
</template>
<script>
import HeaderMenu from './menu/HeaderMenu.vue'

export default {
  name: 'Main',
  data () {
      return {
          isCollapsed: false
      };
  },
  computed: {
      menuitemClasses: function () {
          return [
              'menu-item',
              this.isCollapsed ? 'collapsed-menu' : ''
          ]
      }
  },
  components: {
    HeaderMenu
  }
}
</script>

<style>
    .ivu-layout-footer{
      padding: 0 0 !important;
    }
    .ivu-layout-sider-trigger {
      height: 20px !important;
      line-height: 20px !important;
    }
    .ivu-layout-header {
      padding: 0 0 !important;
    }
</style>
