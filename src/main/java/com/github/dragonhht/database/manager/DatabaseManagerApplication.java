package com.github.dragonhht.database.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.dragonhht.database.manager.mapper")
public class DatabaseManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseManagerApplication.class, args);
    }

}
