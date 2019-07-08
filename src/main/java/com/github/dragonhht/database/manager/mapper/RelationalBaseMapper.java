package com.github.dragonhht.database.manager.mapper;

import com.github.dragonhht.database.manager.model.ResultData;
import com.github.dragonhht.database.manager.model.SqlStatement;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 关系型数据库基础操作.
 *
 * @author: huang
 * @Date: 2019-7-7
 */
@Repository
public interface RelationalBaseMapper {

    @Select("${sql}")
    List<ResultData> selectList(SqlStatement statement);

}
