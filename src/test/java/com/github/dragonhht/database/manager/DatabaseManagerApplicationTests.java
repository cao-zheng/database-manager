package com.github.dragonhht.database.manager;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.service.RelationalDBService;
import com.github.dragonhht.database.manager.utils.helper.ConfigHelper;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import com.github.dragonhht.database.manager.vo.DBInfo;
import com.github.dragonhht.database.manager.vo.PageInfo;
import com.github.dragonhht.database.manager.dto.ResultData;
import com.github.dragonhht.database.manager.model.JdbcConnectionData;
import com.github.dragonhht.database.manager.service.RelationalService;
import com.github.dragonhht.database.manager.utils.DataSourceUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseManagerApplicationTests {

    @Autowired
    private RelationalService relationalService;
    @Autowired
    private RelationalDBService relationalDBService;

    @Before
    public void init() {
        JdbcConnectionData data = new JdbcConnectionData();
        data.setDataBaseName("*");
        data.setHost("my.dragon.com");
        data.setPlatform(RelationalPlatform.MYSQL);
        data.setPort(3307);
        data.setUserName("root");
        data.setPassword("123");
        data.setDriverClassName("com.mysql.jdbc.Driver");
        DataSourceUtil.INSTANCE.addDataSource("now", data);
        DataSourceUtil.setNowDataSource("now");
    }

    @Test
    public void testPageSelect() throws Exception {
        PageInfo info = relationalService.select("select * from user");
        System.out.println(info);
    }

    @Test
    public void testSelectList() throws Exception {
        List<ResultData> list = relationalService.selectList("show databases");
        list.forEach(System.out::println);
    }

    @Test
    public void testUpdate() throws Exception {
        int n = relationalService.update("update user set age = 20 where id = 1");
        System.out.println(n);
    }

    @Test
    public void testDel() throws Exception {
        int n = relationalService.delete("delete from user where id = 1");
        System.out.println(n);
    }

    @Test
    public void testInsert() throws Exception {
        int n = relationalService.insert("insert into user(id, name, age, className) VALUE(2, 'huang', 18, 'A123')");
        System.out.println(n);
    }

    @Test
    public void testDDL() throws Exception {
        String createSql = "CREATE TABLE book (\n" +
                "  book_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '图书编号',\n" +
                "  book_name VARCHAR(50) NOT NULL COMMENT '图书名',\n" +
                "  author VARCHAR(30) NOT NULL COMMENT '作者',\n" +
                "  publish VARCHAR(50) NOT NULL COMMENT '出版社',\n" +
                "  introduction TEXT COMMENT '简介',\n" +
                "  book_date TIMESTAMP NOT NULL COMMENT '上传时间',\n" +
                "  book_start INT DEFAULT 0 COMMENT '评价星级' ,\n" +
                "  book_img TEXT NOT NULL COMMENT '图书图片'\n" +
                ") AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8;";
        int n = relationalService.ddl(createSql);
        System.out.println(n);
    }

    @Test
    public void testDB() throws Exception {
        ConnectionInfo info = new ConnectionInfo();
        info.setUserName("root");
        info.setDb("*");
        info.setHost("my.dragon.com");
        info.setPlatform(RelationalPlatform.MYSQL);
        info.setPort(3307);
        info.setUserName("root");
        info.setPassword("123");
        info.setName("link-1");
        List<DBInfo> infos = relationalDBService.getAllDB(info);
        infos.forEach(System.out::println);
    }

}
