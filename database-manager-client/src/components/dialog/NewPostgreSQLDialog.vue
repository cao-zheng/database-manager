<!-- 新建PostgreSQL连接对话框 -->
<template>
    <Modal v-model="showPostgreSQLDialog" width="460"
        :cancel="cancel" :closable="false"
        :mask-closable="false"
        title="PostgreSQL-新建连接">
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
                <FormItem label="初始数据库:">
                    <Input type="text" v-model="connectionInfo.db" placeholder="初始数据库" style="width: 300px" />
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
    name: 'NewPostgreSQLDialog',
    data() {
        return {
            connectionInfo: {
                platform: this.params.SqlPlatform.postgresql,
                name: '',
                oldName: '',
                host: '',
                port: 5432,
                userName: 'postgres',
                password: '',
                savePwd: false,
                db: 'postgres'
            }
        }
    },
    props: [
        'showPostgreSQLDialog'
    ],
    methods: {
        cancel: function() {
            this.$emit('transferDialogShow', transferShowData.transferDialogShowData(this.params.SqlPlatform.postgresql, false))
        },
        save: function() {
            this.$axios({
                method: 'post',
                url: `${this.params.MainHost}/connection/connection`,
                data: this.connectionInfo
            }).then(res => {
                if(res.data) {
                    this.$Message.success('操作成功');
                    this.$emit('transferDialogShow', transferShowData.transferDialogShowData(this.params.SqlPlatform.postgresql, false))
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
            this.$emit('transferNewConnection', this.connectionInfo)
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
