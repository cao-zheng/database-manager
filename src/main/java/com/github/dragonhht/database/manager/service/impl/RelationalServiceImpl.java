package com.github.dragonhht.database.manager.service.impl;

import com.github.dragonhht.database.manager.mapper.RelationalBaseMapper;
import com.github.dragonhht.database.manager.model.ResultData;
import com.github.dragonhht.database.manager.model.SqlStatement;
import com.github.dragonhht.database.manager.service.RelationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-7-7
 */
@Service
public class RelationalServiceImpl implements RelationalService {

    @Autowired
    private RelationalBaseMapper relationalBaseMapper;

    @Override
    public List<ResultData> select(String sql) {
        SqlStatement sqlStatement = new SqlStatement();
        sqlStatement.setSql(sql);
        List<ResultData> result = relationalBaseMapper.selectList(sqlStatement);
        return result;
    }
}
