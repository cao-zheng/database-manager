<!-- 顶部小菜单 -->
<template>
    <div class="menu-nav">
        <Dropdown trigger="click" class="menu-btn">
            <div class="title">文件</div>
            <DropdownMenu slot="list">
                <DropdownItem>
                    <Dropdown trigger="hover">
                        <a>新建连接</a>
                        <Icon type="ios-arrow-forward"></Icon>
                        <DropdownMenu slot="list">
                            <DropdownItem>打开连接</DropdownItem>
                        </DropdownMenu>
                    </Dropdown>
                </DropdownItem>
                <DropdownItem>打开连接</DropdownItem>
                <DropdownItem>关闭连接</DropdownItem>
                <DropdownItem divided='true'>导出连接</DropdownItem>
                <DropdownItem>导入连接</DropdownItem>
                <DropdownItem divided='true'>关闭</DropdownItem>
                <DropdownItem>退出</DropdownItem>
            </DropdownMenu>
        </Dropdown>
    </div>
</template>

<script>
export default {
    
}
</script>

<style lang="less" scoped>
    .menu-nav {
        height: 2.5vh;
        line-height: 2.5vh;
        width: 50px;

        a {
            color: #515a6e;
        }

        .title {
            cursor: pointer;
        }

        &:hover {
            background: #99CCFF;
        }
    }
    .menu-btn {
        padding: 0 10px;
    }
</style>

