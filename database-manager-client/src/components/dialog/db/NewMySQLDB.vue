<!-- 创建MySQL数据库 -->
<template>
  <Modal v-model="showNewMySQLDBDialog" width="460"
    :cancel="cancel" :closable="false" 
    :mask-closable="false"
    title="MySQL-新建数据库">
    <div>
      <Form :mode="dbInfo" label-position="left" :label-width="100">
        <FormItem label="数据库名称:">
            <Input type="text" v-model="dbInfo.dbName" placeholder="数据库名称" style="width: 300px" />
        </FormItem>
        <FormItem label="字符集:">
            <Select v-model="dbInfo.charset" style="width:300px">
              <Option v-for="item in charsets" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
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
import Qs from 'qs'

export default {
  name: 'NewMySQLDialog',
  props: ['showNewMySQLDBDialog', 'info'],
  data() {
    return {
      dbInfo: {
        dbName: '',
        platform: this.params.SqlPlatform.mysql,
        charset: ''
      },
      // 部分编码
      charsets: [
        {
          value: 'default',
          label: 'Default character set'
        },
        {
          value: 'ascii',
          label: 'ascii -- US ASCII'
        },
        {
          value: 'gb18030',
          label: 'gb18030 -- China National Standard GB18030'
        },
        {
          value: 'gb2312',
          label: 'gb2312 -- GB2312 Simplified Chinese'
        },
        {
          value: 'gbk',
          label: 'gbk -- GBK Simplified Chinese'
        },
        {
          value: 'utf16',
          label: 'utf16 -- UTF-16 Unicode'
        },
        {
          value: 'utf16le',
          label: 'utf16le -- UTF-16LE Unicode'
        },
        {
          value: 'utf32',
          label: 'utf32 -- UTF-32 Unicode'
        },
        {
          value: 'utf8',
          label: 'utf8 -- UTF-8 Unicode'
        },
        {
          value: 'utf8mb4',
          label: 'utf8mb4 -- UTF-8 Unicode'
        }
      ]
    }
  },
  methods: {
    cancel: function() {
      this.$emit('transferDBDialogShow', transferShowData.transferDialogShowData(this.params.SqlPlatform.mysql, false))
    },
    save: function() {
      let data = JSON.parse((JSON.stringify(this.dbInfo)+JSON.stringify(this.info)).replace(/}{/,','))
      this.$axios({
            method: 'post',
            url: `${this.params.MainHost}/db/db`,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: Qs.stringify(data)
        }).then(res => {
            if(res.data) {
              this.$Message.success('操作成功');
              this.cancel()
            } else {
              this.$Message.error('操作失败');
            }
        }).catch(err => {
            console.log(err)
            this.$Message.error('操作失败');
        })
    }
  },
  watch: {
    showNewMySQLDBDialog: function(){
      this.dbInfo.name = ''
      this.dbInfo.charset = ''
    }
  }
}
</script>

<style lang="less" scoped>

</style>
