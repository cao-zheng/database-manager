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
            <tables v-if="index ==0 && (item.target === params.ShowTarget.table || item.target === params.ShowTarget.view)" 
                :dataInfo="item"
                @transferSelectData="getSelectData">
            </tables>
            <table-data v-if="index != 0 && (item.target == 'select-' + params.ShowTarget.table || item.target == 'select-' + params.ShowTarget.view) "
                :dataInfo="item">
            </table-data>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import Tables from './Tables'
import TableData from './TableData'
import { mapState } from 'vuex'

export default {
    name: 'MainTabs',
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
        getTabItemInfo: function(title, disabled, target, data, info) {
            let obj = new Object()
            obj.title = title
            obj.disabled = disabled
            obj.target = target
            obj.data = data
            obj.info = info
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
        },
        // 获取选择的数据
        getSelectData: function(data) {
            let item = this.getTabItemInfo(data.title, false, data.target, {}, data.info)
            // TODO 目前只添加
            let len = this.tabs.push(item)
            this.activeTabName = this.convertNumberToString(len - 1)
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
        },
        ...mapState({
            contentData: state => state.nowConnectionInfo
        })
    },
    watch: {
        // 侦听contentData的变化
        contentData: {
            handler(newValue, oldValue) {
                if(newValue.target === this.params.ShowTarget.table || newValue.target === this.params.ShowTarget.view) {
                    let tab = new Object()
                    tab.title = this.tabs[0].title
                    tab.target = newValue.target
                    tab.info = newValue.info
                    tab.disabled = false
                    this.$set(this.tabs, 0, tab)
                }
            },
            deep: true,
            immediate: true
        }
        
    },
    components: {
        'tables': Tables,
        'table-data': TableData
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
