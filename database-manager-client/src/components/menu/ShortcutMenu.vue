<!-- 快捷菜单 -->
<template>
    <div>
        <div class="menu-icon">
            <Dropdown trigger="click">
                <Icon class="icon" type="ios-add-circle-outline" size="45" />
                <Icon type="ios-arrow-down" />
                <DropdownMenu slot="list">
                    <DropdownItem >
                        <a @click="newMySQLConnection">MySQL</a>
                    </DropdownItem>
                    <DropdownItem>PostgreSQL</DropdownItem>
                    <DropdownItem>Oracle</DropdownItem>
                    <DropdownItem>SQLite</DropdownItem>
                    <DropdownItem>SQL Server</DropdownItem>
                    <DropdownItem>MariaDB</DropdownItem>
                </DropdownMenu>
            </Dropdown>
        </div>
        <Divider class="menu-divider" type="vertical" />
        <div class="menu-icon">
            <i class="iconfont icon-biaoge icon"></i>
        </div>
        <div class="menu-icon">
            <i class="iconfont icon-shitu icon"></i>
        </div>
        <div class="menu-icon">
            <i class="iconfont icon-0303 icon"></i>
        </div>

        <new-mysql-dialog :showMySQLDialog="showMySQLDialog"></new-mysql-dialog>
    </div>
</template>

<script>
import NewMySQLDialog from '../dialog/NewMySQLDialog'

export default {
    name: 'ShortcutMenu',
    data() {
        return {
            showMySQLDialog: false
        }
    },
    methods: {
        newMySQLConnection: function() {
            this.showMySQLDialog = true
        }
    },
    components: {
        'new-mysql-dialog': NewMySQLDialog
    }
}
</script>

<style lang="less" scoped>
    @import '../../assets/ali_icon/iconfont.css';

    .menu-icon {
        cursor: pointer;
        width: 60px;
        float: left;
        line-height: 50px;
        height: 50px;
        text-align: center;
        
        &:hover {
            background: #99CCFF;
        }

        .icon {
            line-height: 50px;
            font-size: 30px;
        }
    }

    .menu-divider {
        float: left;
        line-height: 50px;
        height: 50px;
    }

    a {
      color: #515a6e !important;
    }
</style>
