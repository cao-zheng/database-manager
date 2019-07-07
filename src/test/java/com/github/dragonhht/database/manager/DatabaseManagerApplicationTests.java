package com.github.dragonhht.database.manager;

import com.github.dragonhht.database.manager.mapper.RelationalBaseMapper;
import com.github.dragonhht.database.manager.model.JdbcConnectionData;
import com.github.dragonhht.database.manager.model.SqlStatement;
import com.github.dragonhht.database.manager.service.RelationalService;
import com.github.dragonhht.database.manager.utils.DataSourceUtil;
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

        //List list = session.selectList("com.github.dragonhht.database.manager.mapper.BaseMapper.selectList", sqlStatement);
        SqlStatement sqlStatement1 = new SqlStatement("select * from db");
        List<List<Object>> list = relationalService.select("select * from db");
        list.forEach(item -> {
            for (Object obj : item) {
                System.out.printf("%-33s", obj);
            }
            System.out.println();
        });
    }

}
