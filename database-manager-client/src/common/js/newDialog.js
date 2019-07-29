// 新建连接公共方法
export default {
    // 传递弹窗显示结果至父组件
    transferDialogShowData: function(platform, isShow, target, result, title) {
        let obj = new Object()
        obj.platform = platform
        obj.isShow = isShow
        obj.target = target
        obj.result = result
        obj.title = title
        return obj
    }
}