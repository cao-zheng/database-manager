package com.github.dragonhht.database.manager.service.impl;

import com.github.dragonhht.database.manager.dto.Page;
import com.github.dragonhht.database.manager.dto.PageInfo;
import com.github.dragonhht.database.manager.mapper.RelationalBaseMapper;
import com.github.dragonhht.database.manager.dto.ResultData;
import com.github.dragonhht.database.manager.dto.SqlStatement;
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
    public PageInfo select(String sql) {
        SqlStatement sqlStatement = new SqlStatement();
        sqlStatement.setSql(sql);
        Page<ResultData> page = new Page();
        page.setSqlStatement(sqlStatement);
        List<ResultData> list = relationalBaseMapper.selectList(page);
        return new PageInfo(page, list);
    }
}
