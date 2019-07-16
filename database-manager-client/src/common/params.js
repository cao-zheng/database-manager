// 关系型数据库类型
let SqlPlatform = {
    mysql: 'MYSQL',
    oracle: 'ORACLE',
    postgresql: 'POSTGRESQL',
    sqlite: 'SQLITE',
    sqlserver: 'SQLSERVER',
    mariadb: 'MARIADB'
}

let ShowTarget = {
    // 视图
    view: 'view',
    // 表
    table: 'table',
    // 查询
    select: 'select'
}

let MainHost = 'http://localhost:8888/database'

export default {
    SqlPlatform,
    MainHost,
    ShowTarget
}