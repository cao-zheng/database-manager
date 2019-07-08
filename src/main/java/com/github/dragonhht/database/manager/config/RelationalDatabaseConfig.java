package com.github.dragonhht.database.manager.config;

import com.github.dragonhht.database.manager.interceptor.handleResultSetsPlugin;
import com.github.dragonhht.database.manager.model.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-7-7
 */
@Configuration
@Slf4j
public class RelationalDatabaseConfig {

    /**
     * 数据源
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        // 自定义数据源
        builder.type(DynamicDataSource.class);
        return builder.build();
    }

    /**
     * 会话工厂
     * @param dataSource
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPlugins(new handleResultSetsPlugin[]{new handleResultSetsPlugin()});
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            log.error("初始化SqlSessionFactory失败", e);
            return null;
        }
    }

}
