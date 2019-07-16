<!-- 表格与视图展示 -->
<template>
    <el-tabs v-model="tabs.length" type="card" editable @edit="handleTabsEdit">
        <el-tab-pane v-for="(item, index) in tabs" closable
            :key="index"
            :label="item.title"
            :name="index">
            表格与视图展示-{{index}}
        </el-tab-pane>
    </el-tabs>
</template>

<script>
export default {
    name: 'Tables',
    data() {
        return {
            tabs: [
                {
                    key: 'tab0',
                    title: '表1'
                },
                {
                    key: 'tab1',
                    title: '表2'
                }
                ,
                {
                    key: 'tab1',
                    title: '表2'
                }
                ,
                {
                    key: 'tab1',
                    title: '表2'
                }
            ]
        }
        
    },
    methods: {
        handleTabsEdit(targetName, action) {
            if (action === 'add') {
            }
            if (action === 'remove') {
                this.$delete(this.tabs, targetName)
            }
            console.log(this.tabs)
        }
    }
}
</script>

<style lang="less" scoped>

</style>
