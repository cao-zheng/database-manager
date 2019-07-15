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
                    <Checkbox v-model="connectionInfo.isSavePwd">记住密码</Checkbox>
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
                platform: '',
                name: '',
                oldName: '',
                host: '',
                port: 3306,
                userName: 'root',
                password: '',
                isSavePwd: false
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
            console.log('保存')
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
