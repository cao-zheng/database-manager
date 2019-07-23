package com.github.dragonhht.database.manager.interceptor;

import com.github.dragonhht.database.manager.dto.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 通过Mybatis拦截器装配结果.
 *
 * @author: huang
 * @Date: 2019-7-8
 */
@Slf4j
@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class HandleResultSetsPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable{
        List<ResultData> result = new LinkedList<>();
        Object[] args = invocation.getArgs();
        Statement statement = (Statement) args[0];
        ResultSet rs = statement.getResultSet();
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        int count = resultSetMetaData.getColumnCount();
        ResultData resultData = new ResultData();
        // 获取字段名
        List<String> columnNames = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            columnNames.add(resultSetMetaData.getColumnLabel(i));
        }
        resultData.setColumnNames(columnNames);
        result.add(resultData);

        // 获取数据值
        while (rs.next()) {
            ResultData values = new ResultData();
            List<Object> valueList = new ArrayList<>(count);
            for (String key : columnNames) {
                valueList.add(rs.getObject(key));
            }
            values.setValues(valueList);
            result.add(values);
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}