import params from '../../../common/params'
// 树形结构节点的reader函数
export default {
  /**
   * 我的连接节点
   * @param {*} h 
   * @param {*} data 
   * @param {*} onEvent 
   */
  rootItemRender: function(h, data, onEvent) {
    return h('span', {
        style: {
            display: 'inline-block',
            'max-width': '100%',
            cursor: 'pointer'
        },
        on: onEvent
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

  /**
   * 连接节点的render
   * @param {*} h 
   * @param {*} data 
   * @param {*} platform 
   * @param {*} onEvent 
   */
  connectionItemRender: function(h, data, platform, onEvent) {
    return h('span', {
        style: {
          display: 'inline-block',
          'max-width': '100%',
          cursor: 'pointer'
        },
        on: onEvent,
      },
      [
          h('span', [
              h('i', {
                  class: `iconfont ${this.getPlatformIcon(platform)}`,
                  style: {
                      marginRight: '8px'
                  }
              }),
              h('span', {
                  class: ['tree-item']
              }, data.title)
          ])
      ]
    )
  },

  /**
   * 第三层级节点
   * @param {*} h 
   * @param {*} data 
   * @param {*} onEvent 
   */
  thirdItemRender: function(h, data, onEvent) {
    return h('span', {
        style: {
            display: 'inline-block',
            'max-width': '100%',
            cursor: 'pointer',
            'line-height': '10px'
        },
        on: onEvent
      },
      [
        h('span', [ 
            h('i', {
                class: `iconfont icon-shujuku`,
                style: {
                    marginRight: '8px'
                }
            }),
            h('span', {
                class: ['tree-item']
            }, data.title)
        ])
      ]
    )
  },

  /**
   * 第四层节点
   * @param {*} h 
   * @param {*} data 
   * @param {*} target 
   * @param {*} onEvent 
   */
  forthItemRender: function(h, data, target, onEvent) {
    return h('span', {
        style: {
            display: 'inline-block',
            'max-width': '100%',
            cursor: 'pointer',
            'line-height': '10px'
        },
        on: onEvent
      },
      [
        h('span', [
            h('i', {
                class: `iconfont ${this.getForthItemIcon(target)}`,
                style: {
                    marginRight: '8px'
                }
            }),
            h('span', {
                class: ['tree-item']
            }, data.title)
        ])
      ]
    )
  },

  /**
   * 获取第四层级的节点图标
   * @param {String} target 目标类型
   */
  getForthItemIcon: function(target) {
    switch(target) {
        case params.ShowTarget.view:
            return 'icon-shitu'
        case params.ShowTarget.table:
            return 'icon-biaoge'
        case params.ShowTarget.select:
            return 'icon-0303'
    }
  },
  /**
   * 节点获取数据库类型图标
   * @param {*} platform 
   */
  getPlatformIcon: function(platform) {
    switch(platform) {
        case params.SqlPlatform.mysql:
            return 'icon-mysql'
        case params.SqlPlatform.postgresql:
            return 'icon-postgresql'
        case params.SqlPlatform.oracle:
            return 'icon-oracle'
        case params.SqlPlatform.sqlserver:
            return 'icon-shujukuleixingtubiao-kuozhan-2'
        case params.SqlPlatform.mariadb:
            return 'icon-MariaDB'
        default:
            return ''
    }
  }
}