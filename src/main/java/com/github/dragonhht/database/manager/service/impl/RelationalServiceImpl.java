package com.github.dragonhht.database.manager.service.impl;

import com.github.dragonhht.database.manager.dto.Page;
import com.github.dragonhht.database.manager.vo.PageInfo;
import com.github.dragonhht.database.manager.mapper.RelationalBaseMapper;
import com.github.dragonhht.database.manager.dto.ResultData;
import com.github.dragonhht.database.manager.dto.SqlStatement;
import com.github.dragonhht.database.manager.service.RelationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关系型数据库基本操作服务实现类.
 *
 * @author: huang
 * @Date: 2019-7-7
 */
@Service
public class RelationalServiceImpl implements RelationalService {

    @Autowired
    private RelationalBaseMapper relationalBaseMapper;

    private SqlStatement initSqlStatement(String sql) {
        SqlStatement sqlStatement = new SqlStatement();
        sqlStatement.setSql(sql);
        return sqlStatement;
    }

    @Override
    public PageInfo select(String sql) {
        Page page = new Page();
        page.setSqlStatement(initSqlStatement(sql));
        List<ResultData> list = relationalBaseMapper.selectPage(page);
        return new PageInfo(page, list);
    }

    @Override
    public List<ResultData> selectList(String sql) throws Exception {
        return relationalBaseMapper.selectList(initSqlStatement(sql));
    }

    @Override
    public int update(String sql) throws Exception {
        return relationalBaseMapper.update(initSqlStatement(sql));
    }

    @Override
    public int delete(String sql) throws Exception {
        return relationalBaseMapper.delete(initSqlStatement(sql));
    }

    @Override
    public int insert(String sql) throws Exception {
        return relationalBaseMapper.insert(initSqlStatement(sql));
    }

    @Override
    public int ddl(String sql) throws Exception {
        return relationalBaseMapper.update(initSqlStatement(sql));
    }
}
