<!-- 标签页 -->
<template>
    <el-tabs v-model="activeTabName" type="card" @tab-remove="handleTabsRemove">
        <el-tab-pane v-for="(item, index) in tabs" 
            :closable="index != 0"
            :key="index"
            :label="item.title"
            :name="convertNumberToString(index)"
            :disabled="item.disabled">
            <!-- 表和视图 -->
            <tables v-if="index ==0 && (item.target == params.ShowTarget.table || item.target == params.ShowTarget.view)" 
                :contentData="item.data" :target="item.target"></tables>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import Tables from './Tables'

export default {
    name: 'MainTabs',
    props: ['contentData'],
    data() {
        return {
            tabs: [
                {
                    title: '对象',
                    disabled: true,
                    target: '*',
                    data: {}
                }
            ],
            activeTabName: '0'
        }
        
    },
    methods: {
        // 获取每个标签页对象信息
        getTabItemInfo: function(info) {
            let obj = new Object()
            obj.title = info.title
            return obj
        },
        // 移除标签页
        handleTabsRemove(targetName) {
            const index = parseInt(targetName)
            const len = this.tabs.len
            if(targetName == this.activeTabName) {
                if(index > 0) {
                    this.activeTabName = this.convertNumberToString(index - 1)
                } else if(len > 1) {
                    this.activeTabName = this.convertNumberToString(index + 1)
                }
            } else {
                const active = parseInt(this.activeTabName)
                if(active > index) {
                    this.activeTabName = this.convertNumberToString(active - 1)
                } 
            }
            this.$delete(this.tabs, index)
        },
        // 将number转为string
        convertNumberToString: function(num) {
            return String(num)
        }
    },
    computed: {
        // 获取标签页的数量
        getTabSize: {
            get: function() {
                this.tabSize = String(this.tabs.length)
                return this.tabSize
            },
            set: function(size) {
                this.tabSize = size
            }
        }
    },
    watch: {
        // 侦听contentData的变化
        contentData: function(newValue, oldValue) {
            if(newValue.target === this.params.ShowTarget.table || newValue.target === this.params.ShowTarget.view) {
                let tab = this.tabs[0]
                tab.target = newValue.target
                tab.data = newValue.data
                tab.disabled = false
                this.$set(this.tabs, 0, tab)
            }
        }
    },
    components: {
        'tables': Tables
    }
}
</script>

<style lang="less">
.el-tabs__nav-scroll {
    position: fixed;
    background: white;
    z-index: 10;
    width: 100%;
}
.el-tabs__header {
    margin-bottom: 45px !important;
}
</style>
