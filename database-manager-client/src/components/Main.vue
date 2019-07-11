<!-- 主界面布局 -->
<template>
    <div class="layout">
        <Header :style="{background: '#fff', 'border-bottom': '1px solid #DEDEDE', height: '72px'}">
          <HeaderMenu></HeaderMenu>
        </Header>
        <Layout :style="{height: 'calc(100vh - 72px)'}">
            <Sider collapsible :collapsed-width="20"  v-model="isCollapsed" 
              :style="{background: '#fff', 'border-right': '1px solid #DEDEDE'}">
                <TreeList :isCollapsed="isCollapsed" @transferCollapsed="selectListIcon"></TreeList>
            </Sider>
            <Layout :style="{height: 'calc(100vh - 72px)'}">
                <Content>
                    主题内容
                </Content>
                <Footer :style="{'border-top': '1px solid #DEDEDE', height: '20px'}">
                  状态栏
                </Footer>
            </Layout>
        </Layout>
    </div>
</template>
<script>
import HeaderMenu from './menu/HeaderMenu'
import TreeList from './sider/TreeList'

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
  methods: {
    selectListIcon: function(result) {
      this.isCollapsed = result
    }
  },
  components: {
    HeaderMenu,
    TreeList
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
