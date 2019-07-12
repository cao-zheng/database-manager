<!-- 顶部小菜单 -->
<template>
    <div class="menu-nav">
        <Dropdown trigger="click" class="menu-btn">
            <a class="title">文件</a>
            <DropdownMenu slot="list">
                <DropdownItem>
                    <Dropdown trigger="hover" placement="right-start">
                        <a>新建连接</a>
                        <Icon type="ios-arrow-forward"></Icon>
                        <DropdownMenu slot="list">
                            <DropdownItem>MySQL</DropdownItem>
                            <DropdownItem>PostgreSQL</DropdownItem>
                            <DropdownItem>Oracle</DropdownItem>
                            <DropdownItem>SQLite</DropdownItem>
                            <DropdownItem>SQL Server</DropdownItem>
                            <DropdownItem>MariaDB</DropdownItem>
                        </DropdownMenu>
                    </Dropdown>
                </DropdownItem>
                <DropdownItem>打开连接</DropdownItem>
                <DropdownItem>关闭连接</DropdownItem>
                <DropdownItem divided>导出连接</DropdownItem>
                <DropdownItem>导入连接</DropdownItem>
                <DropdownItem divided>关闭</DropdownItem>
                <DropdownItem>退出</DropdownItem>
            </DropdownMenu>
        </Dropdown>

        <Dropdown trigger="click" class="menu-btn">
            <a class="title">查看</a>
            <DropdownMenu slot="list">
                <DropdownItem>表</DropdownItem>
                <DropdownItem>视图</DropdownItem>
                <DropdownItem>查询</DropdownItem>
            </DropdownMenu>
        </Dropdown>
    </div>
</template>

<script>
export default {
    name: 'AllMenu'
}
</script>

<style lang="less" scoped>
    .menu-nav {
        height: 18px;
        line-height: 18px;

        a {
            color: #515a6e;
        }

        .title {
            cursor: pointer;
        }
    }
    .menu-btn {
        padding: 0 10px;

        &:hover {
            background: #99CCFF;
        }
    }
</style>

