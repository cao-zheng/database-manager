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

        <new-mysql-dialog
            :showMySQLDialog="showMySQLDialog"
            @transferDialogShow="setShowNewDialog"
            @transferNewConnection="addNewConnection">
        </new-mysql-dialog>
        <new-postgresql-dialog
            :showPostgreSQLDialog="showPostgreSQLDialog"
            @transferDialogShow="setShowNewDialog"
            @transferNewConnection="addNewConnection">
        </new-postgresql-dialog>
        <new-oracle-dialog
            :showOracleDialog="showOracleDialog"
            @transferDialogShow="setShowNewDialog"
            @transferNewConnection="addNewConnection">
        </new-oracle-dialog>
        <new-slqserver-dialog
            :showSQLServerDialog="showSQLServerDialog"
            @transferDialogShow="setShowNewDialog"
            @transferNewConnection="addNewConnection">
        </new-slqserver-dialog>
        <new-mariadb-dialog
            :showMariaDBDialog="showMariaDBDialog"
            @transferDialogShow="setShowNewDialog"
            @transferNewConnection="addNewConnection">
        </new-mariadb-dialog>
    </div>
</template>

<script>
import NewMySQLDialog from '../dialog/NewMySQLDialog'
import NewPostgreSQLDialog from '../dialog/NewPostgreSQLDialog'
import NewOracleDialog from '../dialog/NewOracleDialog'
import NewSQLServerDialog from '../dialog/NewSQLServerDialog'
import NewMariaDBDialog from '../dialog/NewMariaDBDialog'

export default {
    name: 'AllMenu',
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
        },
        // 用于添加新的连接信息节点
        addNewConnection: function(data) {
            this.$emit('transferNewConnection', data)
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

