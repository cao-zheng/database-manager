<!-- 创建MySQL数据库 -->
<template>
  <Modal v-model="showNewMySQLDBDialog" width="460"
    :cancel="cancel" :closable="false" 
    :mask-closable="false"
    title="MySQL-新建数据库">
    <div>
      <Form :mode="dbInfo" label-position="left" :label-width="100">
        <FormItem label="数据库名称:">
            <Input type="text" v-model="dbInfo.name" placeholder="数据库名称" style="width: 300px" />
        </FormItem>
        <FormItem label="字符集:">
            <Input type="text" v-model="dbInfo.charset" placeholder="字符集" style="width: 300px" />
        </FormItem>
      </Form>
    </div>
    <div slot="footer">
      <Button type="info" @click="save">保存</Button>
      <Button @click="cancel">取消</Button>
    </div>
  </Modal>
</template>

<script>
import transferShowData from '../../../common/js/newDialog'

export default {
  name: 'NewMySQLDialog',
  props: ['showNewMySQLDBDialog', 'info'],
  data() {
    return {
      dbInfo: {
        name: '',
        platform: this.params.SqlPlatform.mysql,
        charset: ''
      }
    }
  },
  methods: {
    cancel: function() {
      this.$emit('transferDBDialogShow', transferShowData.transferDialogShowData(this.params.SqlPlatform.mysql, false))
    },
    save: function() {
      console.log('保存')
      console.log(this.dbInfo)
      console.log(this.info)
    }
  }
}
</script>

<style lang="less" scoped>

</style>
