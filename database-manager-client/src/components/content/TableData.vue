<!-- 表格数据展示 -->
<template>
    <el-table :data="tableData" height="calc(100vh - 137px)" 
        size="mini" 
        border fit
        highlight-current-row 
        @current-change="changeSelectRow" 
        v-loading="loading"
        element-loading-text="拼命加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(255, 255, 255, 0.8)">
        <el-table-column v-for="(item, index) in tableColumns" 
            :key="index" :prop="item" :label="item" width="180" show-overflow-tooltip>
        </el-table-column>
    </el-table>
</template>

<script>
import Qs from 'qs'

export default {
    name: 'TableData',
    props: ['dataInfo'],
    data() {
        return {
            tableData: [],
            tableColumns: [],
            loading: true 
        }
    },
    methods: {
        // 选择的行发生切换
        changeSelectRow: function(currentRow, oldCurrentRow) {
        }
    },
    created() {
        this.loading = true
        let data = {
                    dataSourceName: this.dataInfo.info.name,
                    platform: this.dataInfo.info.platform,
                    tableName: this.dataInfo.title
                }
        this.$axios({
            method: 'post',
            url: `${this.params.MainHost}/table/all/data`,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: Qs.stringify(data)
        }).then(res => {
            this.tableData = res.data.list
            this.tableColumns = res.data.columns
            this.loading = false
        }).catch(err => {
            console.log(err)
            this.loading = false
        })
    }
}
</script>

<style lang="less" scoped>

</style>
