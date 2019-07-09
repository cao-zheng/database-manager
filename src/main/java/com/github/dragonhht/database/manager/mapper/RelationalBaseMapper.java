package com.github.dragonhht.database.manager.mapper;

import com.github.dragonhht.database.manager.dto.Page;
import com.github.dragonhht.database.manager.dto.ResultData;
import com.github.dragonhht.database.manager.dto.SqlStatement;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关系型数据库基础操作.
 *
 * @author: huang
 * @Date: 2019-7-7
 */
@Repository
public interface RelationalBaseMapper {

    @Select("${sqlStatement.sql}")
    List<ResultData> selectList(Page page);

}
