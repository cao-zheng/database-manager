<!-- 新建MySQL连接对话框 -->
<template>
    <Modal v-model="showMySQLDialog" width="460"
        :cancel="cancel" :closable="false" 
        :mask-closable="false"
        title="MySQL-新建连接">
        <div>
            <Form :model="connectionInfo" label-position="left" :label-width="100">
                <FormItem label="连接名:">
                    <Input type="text" v-model="connectionInfo.name" placeholder="连接名" style="width: 300px" />
                </FormItem>
                <FormItem label="主机名或IP:">
                    <Input type="text" v-model="connectionInfo.host" placeholder="主机名或IP" style="width: 300px" />
                </FormItem>
                <FormItem label="端口:">
                    <Input type="text" v-model="connectionInfo.port" placeholder="端口" style="width: 300px" />
                </FormItem>
                <FormItem label="用户名:">
                    <Input type="text" v-model="connectionInfo.userName" placeholder="用户名" style="width: 300px" />
                </FormItem>
                <FormItem label="密码:">
                    <Input type="password" v-model="connectionInfo.password" placeholder="密码" style="width: 300px" />
                </FormItem>
                <FormItem label="">
                    <Checkbox v-model="connectionInfo.savePwd">记住密码</Checkbox>
                </FormItem>
            </Form>
        </div>
        <div slot="footer">
            <Button type="primary" @click="testLink" style="float: left;">测试连接</Button>
            <Button type="info" @click="save">保存</Button>
            <Button @click="cancel">取消</Button>
        </div>
    </Modal>
</template>

<script>
import transferShowData from '../../common/js/newDialog'

export default {
    name: 'NewMySQLDialog',
    data() {
        return {
            connectionInfo: {
                platform: this.params.SqlPlatform.mysql,
                name: '',
                oldName: '',
                host: '',
                port: 3306,
                userName: 'root',
                password: '',
                savePwd: false
            }
        }
    },
    props: [
        'showMySQLDialog'
    ],
    methods: {
        cancel: function() {
            this.$emit('transferDialogShow', transferShowData.transferDialogShowData(this.params.SqlPlatform.mysql, false))
        },
        save: function() {
            this.$axios({
                method: 'post',
                url: `${this.params.MainHost}/connection/connection`,
                data: this.connectionInfo
            }).then(res => {
                if(res.data) {
                    this.$Message.success('操作成功');
                    this.$emit('transferDialogShow', transferShowData.transferDialogShowData(this.params.SqlPlatform.mysql, false))
                    this.addNewConnectionItem()
                } else {
                    this.$Message.error('操作失败');
                }
            }).catch(err => {
                console.log(err)
            })
        },
        // 创建新的连接信息节点
        addNewConnectionItem: function() {
            let obj = new Object()
            // 添加新的连接信息
            obj.target = 'new'
            obj.info = this.connectionInfo
            this.$set(this.$store.state, 'newConnectionInfo', obj)
        },
        testLink: function() {
            console.log('测试连接')
        }
    }
}
</script>

<style lang="less" scoped>
.msg-title {
    width: 100px;
}
</style>
