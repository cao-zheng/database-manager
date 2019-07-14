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
                    render: (h, { data }) => {
                        return h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%'
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
                    children: [
                        {
                            title: 'parent 1-1',
                            expand: true,
                            children: [
                                {
                                    title: 'leaf 1-1-1'
                                },
                                {
                                    title: 'leaf 1-1-2'
                                }
                            ]
                        },
                        {
                            title: 'parent 1-2',
                            expand: true,
                            children: [
                                {
                                    title: 'leaf 1-2-1'
                                },
                                {
                                    title: 'leaf 1-2-1'
                                }
                            ]
                        }
                    ]
                }
            ],
            listTempData: []
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
        }
    }
}
</script>

<style lang="less" scoped>
.collapsed-menu {
    display: none;
}
.list-small-icon {
    margin-top: 5px;
    cursor: pointer;
}
</style>

