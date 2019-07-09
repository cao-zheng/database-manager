package com.github.dragonhht.database.manager.interceptor;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.dto.Page;
import com.github.dragonhht.database.manager.utils.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * 通过Mybatis拦截器实现分页.
 *
 * @author: huang
 * @Date: 2019-7-9
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PagePlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        // 获取delegate
        StatementHandler delegate = (StatementHandler)ReflectionUtil.getFieldValue(handler, "delegate");

        // 获取boundSql
        BoundSql boundSql = delegate.getBoundSql();
        // 获取SQL参数对象
        Object obj = boundSql.getParameterObject();
        if (obj instanceof Page) {
            Page<?> page = (Page<?>) obj;
            MappedStatement mappedStatement = (MappedStatement) ReflectionUtil.getFieldValue(delegate, "mappedStatement");
            Connection connection = (Connection) invocation.getArgs()[0];
            String sql = boundSql.getSql();
            // 设置总记录数
            this.setTotalRecord(page, mappedStatement, connection);
            System.out.println(page);
            // 获取分页SQL语句
            String pageSql = this.getPageSql(page, sql);
            ReflectionUtil.setFieldValue(boundSql, "sql", pageSql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 设置总数
     * @param page
     * @param mappedStatement
     * @param connection
     */
    private void setTotalRecord(Page<?> page, MappedStatement mappedStatement, Connection connection) {
        BoundSql boundSql = mappedStatement.getBoundSql(page);
        String  sql = boundSql.getSql();
        // 查询总数的SQL
        String countSql = this.getCountSql(sql);
        // 获取参数映射
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        // 创建查询总数的BoundSql
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);

        // 执行查询总数的SQL
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(countSql);
            parameterHandler.setParameters(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int total = resultSet.getInt(1);
                // 给当前的Page设置总数
                page.setTotalCount(total);
            }
        } catch (SQLException e) {
            log.error("查询总数失败", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("关闭资源失败", e);
            }
        }
    }

    /**
     * 通过查询SQL获取查询总数的SQL
     * @param selectSql
     * @return
     */
    private String getCountSql(String selectSql) {
        int index = selectSql.indexOf("from");
        return "select count(*) " + selectSql.substring(index);
    }

    /**
     * 获取分页SQL
     * @param page
     * @param sql
     * @return
     */
    private String getPageSql(Page<?> page, String sql) {
        StringBuffer sb = new StringBuffer(sql);
        RelationalPlatform platform = page.getSqlStatement().getPlatform();
        if (platform == RelationalPlatform.MYSQL) {
            return this.getMysqlPageSql(page, sb);
        }
        if (platform == RelationalPlatform.ORACLE) {
            return this.getOraclePageSql(page, sb);
        }
        return sb.toString();
    }

    /**
     * 获取Oracle的分页查询你语句.
     * @param page
     * @param sql
     * @return
     */
    private String getOraclePageSql(Page<?> page, StringBuffer sql) {
        int start = (page.getPageNo() - 1) * page.getPageSize() + 1;
        String tableName = UUID.randomUUID().toString().replaceAll("-", "_")
                + System.currentTimeMillis();
        sql.insert(0, "select " + tableName + ".*, rownum r from (").append(") " + tableName + " where rownum < ")
                .append(start + page.getPageSize());
        sql.insert(0, "select * from (").append(") where r >= ")
                .append(start);
        System.out.println(sql.toString());
        return sql.toString();
    }

    /**
     * 获取MySQL的分页查询你语句.
     * @param page
     * @param sql
     * @return
     */
    private String getMysqlPageSql(Page<?> page, StringBuffer sql) {
        int start = (page.getPageNo() - 1) * page.getPageSize();
        sql.append(" limit ")
                .append(start)
                .append(",")
                .append(page.getPageSize());
        return sql.toString();
    }
}
