<!-- 顶部菜单栏 -->
<template>
    <div>
        <all-menu @transferNewConnection="addNewConnection"></all-menu>
        <shortcut-menu @transferNewConnection="addNewConnection"></shortcut-menu>
    </div>
</template>

<script>
import AllMenu from './AllMenu'
import ShortcutMenu from './ShortcutMenu'

export default {
    name: 'HeaderMenu',
    components: {
        'all-menu': AllMenu,
        'shortcut-menu': ShortcutMenu
    },
    methods: {
        // 用于添加新的连接信息节点
        addNewConnection: function(data) {
            this.$emit('transferNewConnection', data)
        }
    }
}
</script>

<style scoped>

</style>
