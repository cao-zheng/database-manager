<!-- 表与视图的列表展示 -->
<template>
    <div class="main-content">
        <div v-for="(item, index) in contentData" :key="index" class="list-item" @click="select(index)">
            <i class="iconfont icon-biaoge" v-if="target == params.ShowTarget.table"></i>
            <i class="iconfont icon-shitu" v-if="target == params.ShowTarget.view"></i>
            <span :style="{'margin-left': '2px'}">{{item.name}}</span>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Tables',
    props: ['contentData', 'target', 'info'],
    methods: {
        select: function(index) {
            let obj = new Object()
            obj.target = 'select-' + this.target
            obj.title = this.contentData[index].name
            obj.info = this.info
            this.$emit('transferSelectData', obj)
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
</style>
