package com.github.dragonhht.database.manager;

import com.github.dragonhht.database.manager.mapper.RelationalBaseMapper;
import com.github.dragonhht.database.manager.model.JdbcConnectionData;
import com.github.dragonhht.database.manager.model.ResultData;
import com.github.dragonhht.database.manager.model.SqlStatement;
import com.github.dragonhht.database.manager.service.RelationalService;
import com.github.dragonhht.database.manager.utils.DataSourceUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseManagerApplicationTests {

    @Autowired
    private RelationalBaseMapper relationalBaseMapper;
    @Autowired
    private RelationalService relationalService;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void contextLoads() {
        JdbcConnectionData data = new JdbcConnectionData();
        //data.setUrl("jdbc:mysql://my.dragon.com:3307/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        data.setDataBaseName("mysql");
        data.setHost("my.dragon.com");
        data.setPlatform("mysql");
        data.setPort(3307);
        data.setUserName("root");
        data.setPassword("123");
        data.setDriverClassName("com.mysql.jdbc.Driver");
        DataSourceUtil.INSTANCE.addDataSource("now", data);
        DataSourceUtil.setNowDataSource("now");
        SqlStatement sqlStatement = new SqlStatement("select * from db");
//        SqlSession session = sqlSessionFactory.openSession();
//        List list = session.selectList("com.github.dragonhht.database.manager.mapper.BaseMapper.selectList", sqlStatement);
        SqlStatement sqlStatement1 = new SqlStatement("select * from db");
        List<ResultData> list = relationalService.select("select * from db");

        list.forEach(item -> {
            if (item.getColumnNames() != null && item.getColumnNames().size() > 0) {
                for (String key : item.getColumnNames()) {
                    System.out.printf("%-33s", key);
                }
            }
            if (item.getValues() != null && item.getValues().size() > 0) {
                for (Object value : item.getValues()) {
                    System.out.printf("%-33s", value);
                }
            }
            System.out.println();
        });
    }

}
