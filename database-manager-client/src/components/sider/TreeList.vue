<!-- 左侧树 -->
<template>
    <div >
        <Tree :data="listData" :class="listTreeClasses"></Tree>
        <Icon v-show="isCollapsed" type="ios-list-box" size="18" class="list-small-icon" @click="selectListIcon" />

        <!-- 二级树右击事件 -->
        <Dropdown transfer ref="contentMenu" style="display: none;" trigger="click" placement="right-start">
            <DropdownMenu slot="list" style="min-width: 80px;">
                <a @click="openConnection">
                    <DropdownItem :disabled="itemDataInfo.opened">打开连接</DropdownItem>
                </a>
                <a @click="closeConnection">
                    <DropdownItem :disabled="!itemDataInfo.opened">关闭连接</DropdownItem>
                </a>
                <a @click="showDelConnection">
                    <DropdownItem >删除连接</DropdownItem>
                </a>
                <a @click="showCreateDbDialog">
                    <DropdownItem divided :disabled="!itemDataInfo.opened">新建数据库</DropdownItem>
                </a>
            </DropdownMenu>
        </Dropdown>

        <!-- 三级树右击事件 -->
        <Dropdown transfer ref="thirdContentMenu" style="display: none;" trigger="click" placement="right-start">
            <DropdownMenu slot="list" style="min-width: 80px;">
                <a>
                    <DropdownItem >创建表</DropdownItem>
                </a>
                <a >
                    <DropdownItem >创建视图</DropdownItem>
                </a>
                <a >
                    <DropdownItem >新增查询</DropdownItem>
                </a>
                <a @click="showDelDB">
                    <DropdownItem divided>删除数据库</DropdownItem>
                </a>
            </DropdownMenu>
        </Dropdown>

        <!-- 四级树表右击事件 -->
        <Dropdown transfer ref="forthTableContentMenu" style="display: none;" trigger="click" placement="right-start">
            <DropdownMenu slot="list" style="min-width: 80px;">
                <a >
                    <DropdownItem >创建表</DropdownItem>
                </a>
            </DropdownMenu>
        </Dropdown>
        <!-- 四级视图表右击事件 -->
        <Dropdown transfer ref="forthViewContentMenu" style="display: none;" trigger="click" placement="right-start">
            <DropdownMenu slot="list" style="min-width: 80px;">
                <a >
                    <DropdownItem >创建视图</DropdownItem>
                </a>
            </DropdownMenu>
        </Dropdown>

        <!-- 删除确认框 -->
        <Modal
            v-model="showDelDialog"
            title="删除确认"
            @on-ok="delOk"
            @on-cancel="delCancel">
            <p>{{delMessage}}</p>
        </Modal>

        <!-- 新建数据库弹框 -->
        <new-mysql-db :showNewMySQLDBDialog="showNewMySQLDBDialog"
            :info="itemDataInfo.info"
            @transferDBDialogShow="setShowNewDBDialog">
        </new-mysql-db>
    </div>
</template>

<script>
import Qs from 'qs'
import NewMySQLDB from '../dialog/db/NewMySQLDB'
import TreeItemRender from './js/TreeItemRender'

export default {
    name: 'TreeList',
    props: ['isCollapsed', 'newConnectionInfo'],
    data() {
        return {
            showNewMySQLDBDialog: false,
            listData: [
                {
                    title: '我的连接',
                    expand: true,
                    index: 0,
                    render: (h, { data }) => TreeItemRender.rootItemRender(h, data, {
                            click: () => {
                                this.changeExpand(data)
                            }
                        }),
                    children: this.children
                }
            ],
            children: [],
            itemDataInfo: {},
            itemRootInfo: {},
            itemNodeInfo: {},
            showDelDialog: false,
            delMessage: ''
        }
    },
    computed: {
        listTreeClasses: function() {
            return [
                this.isCollapsed ? 'collapsed-menu' : ''
            ]
        }
    },
    methods: {
        setShowNewDBDialog: function(result) {
            switch(result.platform) {
                case this.params.SqlPlatform.mysql:
                    this.showNewMySQLDBDialog = result.isShow
                    break
                case this.params.SqlPlatform.postgresql:
                    break
                case this.params.SqlPlatform.oracle:
                    break
                case this.params.SqlPlatform.sqlserver:
                    break
                case this.params.SqlPlatform.mariadb:
                    break
                default:
                    break
            }
            this.addTreeItem(result.target, result.title)
        },
        // 给当前选择的节点添加节点
        addTreeItem: function(target, title) {
            switch(target) {
                case 'newDB':
                    const children = this.itemDataInfo.children || [];
                    children.push(
                        this.getListThirdItem(title, children.length, this.createConnectionInfo(title, this.itemDataInfo.info, true))
                    );
                    this.$set(this.itemDataInfo, 'children', children);
            }
        },
        selectListIcon: function() {
            this.$emit('transferCollapsed', false)
        },
        // 重置树的右键菜单显示
        resetContentMenuShow: function() {
            this.$refs.contentMenu.currentVisible = false
            this.$refs.thirdContentMenu.currentVisible = false
            this.$refs.forthTableContentMenu.currentVisible = false
        },
        // 获取树中展示的连接元素
        getListItem: function(index, info) {
            let obj = new Object()
            obj.title = info.name
            obj.index = index
            obj.platform = info.platform
            obj.info = info
            obj.render = (h, { data }) => TreeItemRender.connectionItemRender(h, data, info.platform, {
                click: () => {
                    this.changeExpand(data)
                },
                dblclick: () => {
                    this.itemDataInfo = data
                    this.openConnection()
                },
                //右键点击事件
                contextmenu: (e) => {
                    this.resetContentMenuShow()
                    e.preventDefault()
                    this.$refs.contentMenu.$refs.reference = event.target
                    this.$refs.contentMenu.currentVisible = !this.$refs.contentMenu.currentVisible
                    this.itemDataInfo = data
                }
                })
            return obj
        },
        getListThirdItem: function(name, index, info) {
            let obj = new Object()
            obj.title = name
            obj.index = index
            obj.platform = info.platform
            obj.info = info
            obj.render = (h, { root, node, data }) => TreeItemRender.thirdItemRender(h, root, node, data, {
                click: () => {
                    this.changeExpand(data)
                },
                dblclick: () => {
                    this.getListForthItems(data)
                },
                //右键点击事件
                contextmenu: (e) => {
                    this.resetContentMenuShow()
                    e.preventDefault()
                    this.$refs.thirdContentMenu.$refs.reference = event.target
                    this.$refs.thirdContentMenu.currentVisible = !this.$refs.thirdContentMenu.currentVisible
                    this.itemDataInfo = data
                    this.itemRootInfo = root
                    this.itemNodeInfo = node
                }
            })
            return obj
        },
        // 获取第四层级的节点名称
        getForthItemTitle: function(target) {
            switch(target) {
                case this.params.ShowTarget.view:
                    return '视图'
                case this.params.ShowTarget.table:
                    return '表'
                case this.params.ShowTarget.select:
                    return '查询'
            }
        },
        
        // 获取展示的数据
        getShowDataList: function(data, target) {
            let obj = new Object()
            obj.target = target
            obj.info = data.info
            this.$emit('transferContentData', obj)
        },
        // 获取第四层级的单个节点
        getListForthItem: function(info, target) {
            let obj = new Object()
            obj.info = info
            obj.target = target
            obj.title = this.getForthItemTitle(target)
            obj.render = (h, { data }) => TreeItemRender.forthItemRender(h, data, target, {
                click: () => {
                    this.getShowDataList(data, target)
                },
                //右键点击事件
                contextmenu: (e) => {
                    e.preventDefault()
                    this.resetContentMenuShow()
                    if(data.target === this.params.ShowTarget.table) {
                        this.$refs.forthTableContentMenu.$refs.reference = event.target
                        this.$refs.forthTableContentMenu.currentVisible = !this.$refs.forthTableContentMenu.currentVisible
                    }
                    if(data.target === this.params.ShowTarget.view) {
                    this.$refs.forthViewContentMenu.$refs.reference = event.target
                    this.$refs.forthViewContentMenu.currentVisible = !this.$refs.forthViewContentMenu.currentVisible
                    }
                }
            })
            return obj
        },
        // 获取第四层级的所有节点
        getListForthItems: function(data) {
            if(!data.opened) {
                const targets = [this.params.ShowTarget.table, this.params.ShowTarget.view, this.params.ShowTarget.select]
                let children = []
                let info = data.info
                targets.forEach(target => {
                    children.push(this.getListForthItem(info, target))
                })
                this.$set(data, 'children', children)
                this.$set(data, 'expand', true)
                this.$set(data, 'opened', true)
            }
        },
        // 改变节点的展开状态
        changeExpand: function(data) {
            this.$set(data, 'expand', !data.expand)
        },
        // 打开连接
        openConnection: function() {
            if(!this.itemDataInfo.opened) {
               this.getDBs(this.itemDataInfo)
            }
            this.$set(this.itemDataInfo, 'expand', true)
            this.$set(this.itemDataInfo, 'opened', true)
        },
        // 关闭连接
        closeConnection: function() {
            this.$set(this.itemDataInfo, 'expand', false)
            this.$set(this.itemDataInfo, 'opened', false)
            this.$set(this.itemDataInfo, 'children', [])
        },
        // 删除连接
        showDelConnection: function() {
            this.showDelDialog = true
            this.delMessage = '是否删除该连接信息'
            this.$set(this.itemDataInfo, 'target', 'connection')
        },
        // 创建新数据库
        showCreateDbDialog: function() {
            switch(this.itemDataInfo.info.platform) {
                case this.params.SqlPlatform.mysql:
                    this.showNewMySQLDBDialog = true
                    break
                case this.params.SqlPlatform.postgresql:
                    break
                case this.params.SqlPlatform.oracle:
                    break
                case this.params.SqlPlatform.sqlserver:
                    break
                case this.params.SqlPlatform.mariadb:
                    break
                default:
                    break
            }
        },
        // 关闭删除确认框
        delCancel: function() {
            this.showDelDialog = false
        },
        // 确认删除
        delOk: function() {
            switch(this.itemDataInfo.target) {
                case 'connection':
                    this.delConnection()
                    break;
                case 'db':
                    this.delDB()
                    break;
            }
        },
        // 删除连接
        delConnection: function() {
            this.$axios({
                method: 'delete',
                url: `${this.params.MainHost}/connection/connection`,
                data: {name: this.itemDataInfo.info.name}
            }).then(res => {
                if(res.data) {
                    this.$delete(this.listData[0].children, this.itemDataInfo.index)
                    this.$Message.success('删除成功');
                }
            }).catch(err => {
                console.log(err)
                this.$Message.error('删除失败');
            })
        },
        //显示删除数据库的对话框
        showDelDB: function() {
            this.showDelDialog = true
            this.delMessage = '是否删除该数据库'
            this.$set(this.itemDataInfo, 'target', 'db')
        },
        // 删除数据库
        delDB: function() {
            let data = {
                info: {
                    dbName: this.itemDataInfo.title
                },
                connectionInfo: this.itemDataInfo.info
            }
            this.$axios({
                method: 'delete',
                url: `${this.params.MainHost}/db/db`,
                data: data
            }).then(res => {
                if(res.data) {
                    this.$Message.success('删除成功');
                    this.delTreeItem(this.itemRootInfo, this.itemNodeInfo, this.itemDataInfo)
                } else {
                    this.$Message.error('删除失败');
                }
            }).catch(err => {
                console.log(err)
                this.$Message.error('删除失败');
            })
        },
        // 删除树形结构的节点
        delTreeItem: function(root, node, data) {
            const parentKey = root.find(el => el === node).parent;
            const parent = root.find(el => el.nodeKey === parentKey).node;
            const index = parent.children.indexOf(data);
            parent.children.splice(index, 1);
        },
        // 获取连接下的数据库信息
        getDBs: function(data) {
            let info = data.info
            let list = []
            this.$axios({
                method: 'post',
                url: `${this.params.MainHost}/db/dbs`,
                data: info,
            }).then(res => {
                let index = 0
                res.data.forEach(item => {
                    let connectionInfo = this.createConnectionInfo(item.dbName, info, true)
                    let node = this.getListThirdItem(item.dbName, index++, connectionInfo)
                    list.push(node)
                })
                this.$set(data, 'children', list)
            }).catch(err => {
                console.log(err)
            })
        },
        // 创建数据库节点的连接数据信息
        createConnectionInfo: function(suffix, info, isDB) {
            let obj = new Object()
            obj.platform = info.platform
            obj.name = info.name + '.' + suffix
            obj.host = info.host
            obj.port = info.port
            obj.userName = info.userName
            obj.password = info.password
            if(isDB) {
                obj.db = suffix
            } else {
                obj.db = info.db
            }
            obj.serviceName = info.serviceName
            return obj
        }
    },
    created() {
        this.$axios({
            method: 'get',
            url: `${this.params.MainHost}/connection/connections`
        }).then(res => {
            this.children = []
            let index = 0
            res.data.forEach(item => {
                this.children.push(this.getListItem(index++, item))
            });
            this.listData[0].children = this.children
        }).catch(err => {
            console.log(err)
        })
    },
    watch: {
        newConnectionInfo: {
            handler(newValue, oldValue) {
                // 新增连接信息
                if(newValue.target === 'new') {
                    console.log(this.children.length)
                    this.children.push(this.getListItem(this.children.length, newValue.info))
                    this.$set(this.listData[0], 'children', this.children)
                }
            },
            deep: true
        }
    },
    components: {
        'new-mysql-db': NewMySQLDB
    }
}
</script>

<style lang="less" scoped>
@import '../../assets/ali_icon/iconfont.css';

.collapsed-menu {
    display: none;
}
.list-small-icon {
    margin-top: 5px;
    cursor: pointer;
}
</style>

<style lang="less">
.ivu-tree ul li {
    margin: 0 !important;
}

.tree-item {
    border-radius: 5px;
    padding: 2px 5px;
    min-width: 100px;

    &:hover {
        background: #99CCFF;
    }
}
</style>

