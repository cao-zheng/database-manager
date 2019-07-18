<!-- 快捷菜单 -->
<template>
    <div>
        <div class="menu-icon">
            <Dropdown trigger="click">
                <Icon class="icon" type="ios-add-circle-outline" size="45" />
                <Icon type="ios-arrow-down" />
                <DropdownMenu slot="list">
                    <a @click="newMySQLConnection">
                        <DropdownItem >MySQL</DropdownItem>
                    </a>
                    <a @click="NewPostgreSQLDialog">
                        <DropdownItem>PostgreSQL</DropdownItem>
                    </a>
                    <a @click="NewOracleDialog">
                        <DropdownItem>Oracle</DropdownItem>
                    </a>
                    <!-- <DropdownItem>SQLite</DropdownItem> -->
                    <a @click="NewSQLServerDialog">
                        <DropdownItem>SQL Server</DropdownItem>
                    </a>
                    <a @click="NewMariaDBDialog">
                        <DropdownItem>MariaDB</DropdownItem>
                    </a>
                </DropdownMenu>
            </Dropdown>
        </div>
        <Divider class="menu-divider" type="vertical" />
        <div class="menu-icon" :style="{}">
            <i class="iconfont icon-biaoge icon"></i>
        </div>
        <div class="menu-icon">
            <i class="iconfont icon-shitu icon"></i>
        </div>
        <div class="menu-icon">
            <i class="iconfont icon-0303 icon"></i>
        </div>

        <new-mysql-dialog :showMySQLDialog="showMySQLDialog" @transferDialogShow="setShowNewDialog"></new-mysql-dialog>
        <new-postgresql-dialog :showPostgreSQLDialog="showPostgreSQLDialog" @transferDialogShow="setShowNewDialog"></new-postgresql-dialog>
        <new-oracle-dialog :showOracleDialog="showOracleDialog" @transferDialogShow="setShowNewDialog"></new-oracle-dialog>
        <new-slqserver-dialog :showSQLServerDialog="showSQLServerDialog" @transferDialogShow="setShowNewDialog"></new-slqserver-dialog>
        <new-mariadb-dialog :showMariaDBDialog="showMariaDBDialog" @transferDialogShow="setShowNewDialog"></new-mariadb-dialog>
    </div>
</template>

<script>
import NewMySQLDialog from '../dialog/NewMySQLDialog'
import NewPostgreSQLDialog from '../dialog/NewPostgreSQLDialog'
import NewOracleDialog from '../dialog/NewOracleDialog'
import NewSQLServerDialog from '../dialog/NewSQLServerDialog'
import NewMariaDBDialog from '../dialog/NewMariaDBDialog'

export default {
    name: 'ShortcutMenu',
    data() {
        return {
            showMySQLDialog: false,
            showPostgreSQLDialog: false,
            showOracleDialog: false,
            showSQLServerDialog: false,
            showMariaDBDialog: false
        }
    },
    methods: {
        newMySQLConnection: function() {
            this.showMySQLDialog = true
        },
        NewPostgreSQLDialog: function() {
            this.showPostgreSQLDialog = true
        },
        NewOracleDialog: function() {
            this.showOracleDialog = true
        },
        NewSQLServerDialog: function() {
            this.showSQLServerDialog = true
        },
        NewMariaDBDialog: function() {
            this.showMariaDBDialog = true
        },
        setShowNewDialog: function(result) {
            switch(result.platform) {
                case this.params.SqlPlatform.mysql:
                    this.showMySQLDialog = result.isShow
                    break
                case this.params.SqlPlatform.postgresql:
                    this.showPostgreSQLDialog = result.isShow
                    break
                case this.params.SqlPlatform.oracle:
                    this.showOracleDialog = result.isShow
                    break
                case this.params.SqlPlatform.sqlserver:
                    this.showSQLServerDialog = result.isShow
                    break
                case this.params.SqlPlatform.mariadb:
                    this.showMariaDBDialog = result.isShow
                    break
                default:
                    break
            }
        }
    },
    components: {
        'new-mysql-dialog': NewMySQLDialog,
        'new-postgresql-dialog': NewPostgreSQLDialog,
        'new-oracle-dialog': NewOracleDialog,
        'new-slqserver-dialog': NewSQLServerDialog,
        'new-mariadb-dialog': NewMariaDBDialog
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
