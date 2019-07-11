<!-- 新建MySQL连接对话框 -->
<template>
    <Modal v-model="show" width="360" :cancel="cancel">
        <p slot="header" style="color:#f60;text-align:center">
            <Icon type="ios-information-circle"></Icon>
            <span>Delete confirmation</span>
        </p>
        <div style="text-align:center">
            <p>After this task is deleted, the downstream 10 tasks will not be implemented.</p>
            <p>Will you delete it?</p>
        </div>
        <div slot="footer">
            <Button type="error" size="large" long >Delete</Button>
        </div>
    </Modal>
</template>

<script>
export default {
    name: 'NewMySQLDialog',
    data() {
        return {
            show: this.showMySQLDialog
        }
    },
    props: [
        'showMySQLDialog'
    ],
    methods: {
        cancel: function() {
            this.show = false
        }
    }
}
</script>

<style lang="less" scoped>

</style>
