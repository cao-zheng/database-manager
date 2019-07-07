package com.github.dragonhht.database.manager.service.impl;

import com.github.dragonhht.database.manager.mapper.RelationalBaseMapper;
import com.github.dragonhht.database.manager.model.SqlStatement;
import com.github.dragonhht.database.manager.service.RelationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public List<List<Object>> select(String sql) {
        SqlStatement sqlStatement = new SqlStatement();
        sqlStatement.setSql(sql);
        List<Map<String, Object>> result = relationalBaseMapper.selectList(sqlStatement);
        // TODO 当第一条为空时字段名无法获取
        List<List<Object>> all = new LinkedList<>();
        int index = 0;
        for (Map<String, Object> map : result) {
            List<Object> keys = new LinkedList<>();
            List<Object> values = new LinkedList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (index == 0) {
                    keys.add(entry.getKey());
                }
                values.add(entry.getValue());
            }
            if (index == 0) {
                all.add(keys);
            }
            all.add(values);
            index++;
        }
        return all;
    }
}
