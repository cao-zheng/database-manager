// 关系型数据库类型
let SqlPlatform = {
    mysql: 'MYSQL',
    oracle: 'ORACLE',
    postgresql: 'POSTGRESQL',
    sqlite: 'SQLITE',
    sqlserver: 'SQLSERVER',
    mariadb: 'MARIADB'
}

let MainHost = 'http://localhost:8888/database'

export default {
    SqlPlatform,
    MainHost
}