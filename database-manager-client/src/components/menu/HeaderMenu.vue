<!-- 顶部菜单栏 -->
<template>
    <div>
        <all-menu></all-menu>
        <shortcut-menu></shortcut-menu>
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
    }
}
</script>

<style scoped>

</style>
