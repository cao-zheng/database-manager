<!-- 左侧树 -->
<template>
    <div >
        <Tree :data="listData" :class="listTreeClasses"></Tree>
        <Icon v-show="isCollapsed" type="ios-list-box" size="18" class="list-small-icon" @click="selectListIcon" />
    </div>
</template>

<script>
export default {
    name: 'TreeList',
    props: ['isCollapsed'],
    data() {
        return {
            listData: [
                {
                    title: '我的连接',
                    expand: true,
                    index: 0,
                    render: (h, { data }) => {
                        return h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        cursor: 'pointer'
                                    },
                                    on: {
                                        click: () => {
                                            this.changeExpand(data)
                                        }
                                    }
                                },
                                [
                                    h('span', [
                                        h('Icon', {
                                            props: {
                                                type: 'ios-folder-outline'
                                            },
                                            style: {
                                                marginRight: '8px'
                                            }
                                        }),
                                        h('span', data.title)
                                    ])
                                ]
                        )
                    },
                    children: this.children
                }
            ],
            children: []
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
        selectListIcon: function() {
            this.$emit('transferCollapsed', false)
        },
        // 获取树中展示的连接元素
        getListItem: function(index, info) {
            let obj = new Object()
            obj.title = info.name
            obj.index = index
            obj.platform = info.platform
            obj.info = info
            obj.render = (h, { data }) => {
                        return h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        cursor: 'pointer'
                                    },
                                    on: {
                                        click: () => {
                                            this.changeExpand(data)
                                        },
                                        dblclick: () => {
                                            this.openConnection(data)
                                        }
                                    }
                                },
                                [
                                    h('span', [
                                        h('i', {
                                            class: `iconfont ${this.getPlatformIcon(info.platform)}`,
                                            style: {
                                                marginRight: '8px'
                                            }
                                        }),
                                        h('span', data.title)
                                    ])
                                ]
                        )
                    }
            return obj
        },
        getListThirdItem: function(name, index, info) {
            let obj = new Object()
            obj.title = name
            obj.index = index
            obj.platform = info.platform
            obj.info = info
            obj.render = (h, { data }) => {
                        return h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        cursor: 'pointer',
                                        'line-height': '10px'
                                    },
                                    on: {
                                        click: () => {
                                            this.changeExpand(data)
                                        },
                                        dblclick: () => {
                                            this.getListForthItems(data)
                                        }
                                    }
                                },
                                [
                                    h('span', [
                                        h('i', {
                                            class: `iconfont icon-shujuku`,
                                            style: {
                                                marginRight: '8px'
                                            }
                                        }),
                                        h('span', data.title)
                                    ])
                                ]
                        )
                    }
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
        // 获取第四层级的节点图标
        getForthItemIcon: function(target) {
            switch(target) {
                case this.params.ShowTarget.view:
                    return 'icon-shitu'
                case this.params.ShowTarget.table:
                    return 'icon-biaoge'
                case this.params.ShowTarget.select:
                    return 'icon-0303'
            }
        },
        // 获取展示的数据
        getShowDataList: function(data, target) {
            switch(target) {
                case this.params.ShowTarget.table:
                    this.getShowTables(data.info)
                    break
                case this.params.ShowTarget.view:
                    this.getShowViews(data.info)
                    break
            }
        },
        // 获取数据库下的所有表
        getShowTables: function(info) {
            this.$axios({
                method: 'post',
                url: `${this.params.MainHost}/db/tables`,
                data: info
            }).then(res => {
                this.$emit('transferContentData', this.getTransferContentData(res.data, this.params.ShowTarget.table))
            }).catch(err => {
                console.log(err)
            })
        },
        // 获取数据库下的所有视图
        getShowViews: function(info) {
            this.$axios({
                method: 'post',
                url: `${this.params.MainHost}/db/views`,
                data: info
            }).then(res => {
                this.$emit('transferContentData', this.getTransferContentData(res.data, this.params.ShowTarget.view))
            }).catch(err => {
                console.log(err)
            })
        },
        // 构造主区域数据结构
        getTransferContentData: function(data, target) {
            let obj = new Object()
            obj.target = target
            obj.data = data
            return obj
        },
        // 获取第四层级的单个节点
        getListForthItem: function(info, target) {
            let obj = new Object()
            obj.info = info
            obj.target = target
            obj.title = this.getForthItemTitle(target)
            obj.render = (h, { data }) => {
                        return h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        cursor: 'pointer',
                                        'line-height': '10px'
                                    },
                                    on: {
                                        click: () => {
                                            this.getShowDataList(data, target)
                                        }
                                    }
                                },
                                [
                                    h('span', [
                                        h('i', {
                                            class: `iconfont ${this.getForthItemIcon(target)}`,
                                            style: {
                                                marginRight: '8px'
                                            }
                                        }),
                                        h('span', data.title)
                                    ])
                                ]
                        )
                    }
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
        // 节点获取数据库类型图标
        getPlatformIcon: function(platform) {
            switch(platform) {
                case this.params.SqlPlatform.mysql:
                    return 'icon-mysql'
                case this.params.SqlPlatform.postgresql:
                    return 'icon-postgresql'
                case this.params.SqlPlatform.oracle:
                    return 'icon-oracle'
                case this.params.SqlPlatform.sqlserver:
                    return 'icon-shujukuleixingtubiao-kuozhan-2'
                case this.params.SqlPlatform.mariadb:
                    return 'icon-MariaDB'
                default:
                    return ''
            }
        },
        // 打开连接
        openConnection: function(data) {
            if(!data.opened) {
               this.getDBs(data)
            }
            this.$set(data, 'expand', true)
            this.$set(data, 'opened', true)
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
                    let connectionInfo = this.createConnectionInfo(item.name, info, true)
                    let node = this.getListThirdItem(item.name, index++, connectionInfo)
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
</style>

