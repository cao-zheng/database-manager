<!-- 表与视图的列表展示 -->
<template>
    <div height="calc(100vh - 137px)"
        v-loading="loading"
        element-loading-text="拼命加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(255, 255, 255, 0.8)">
        <div v-for="(item, index) in contentData" :key="index" class="list-item" @click="select(index)">
            <i class="iconfont icon-biaoge" v-if="dataInfo.target == params.ShowTarget.table"></i>
            <i class="iconfont icon-shitu" v-if="dataInfo.target == params.ShowTarget.view"></i>
            <span class="name-label">{{item.name}}</span>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Tables',
    props: ['dataInfo'],
    data() {
        return {
            contentData: [],
            loading: true
        }
    },
    methods: {
        select: function(index) {
            let obj = new Object()
            obj.target = 'select-' + this.dataInfo.target
            obj.title = this.contentData[index].name
            obj.info = this.dataInfo.info
            this.$emit('transferSelectData', obj)
        },
        // 获取数据库下的所有表
        getShowTables: function(info) {
            this.$axios({
                method: 'post',
                url: `${this.params.MainHost}/db/tables`,
                data: info
            }).then(res => {
               let data = this.getTransferContentData(res.data, this.params.ShowTarget.table, info)
               this.contentData = data.data
               this.loading = false
            }).catch(err => {
                console.log(err)
                this.loading = false
            })
        },
        // 获取数据库下的所有视图
        getShowViews: function(info) {
            this.$axios({
                method: 'post',
                url: `${this.params.MainHost}/db/views`,
                data: info
            }).then(res => {
                let data = this.getTransferContentData(res.data, this.params.ShowTarget.view, info)
                this.contentData = data.data
                this.loading = false
            }).catch(err => {
                console.log(err)
                this.loading = false
            })
        },
        // 构造主区域数据结构
        getTransferContentData: function(data, target, info) {
            let obj = new Object()
            obj.target = target
            obj.data = data
            obj.info = info
            return obj
        }
    },
    watch: {
        dataInfo: {
            handler(newValue, oldValue) {
                this.loading = true
                this.dataInfo = newValue
                switch(this.dataInfo.target) {
                    case this.params.ShowTarget.table:
                        this.getShowTables(this.dataInfo.info)
                        break
                    case this.params.ShowTarget.view:
                        this.getShowViews(this.dataInfo.info)
                        break
                }
            },
            deep: true,
            immediate: true
        }
    }
}
</script>

<style lang="less" scoped>
@import '../../assets/ali_icon/iconfont.css';

.main-content {
    overflow-y: auto; 
}
.list-item {
    width: 320px;
    float: left;
    height: 20px;
    line-height: 20px;
    cursor: pointer;
}

.name-label {
    padding: 2px 5px;

    &:hover {
        background: #99CCFF;
    }
}
</style>
