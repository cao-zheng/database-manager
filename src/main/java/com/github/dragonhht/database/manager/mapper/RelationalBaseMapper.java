package com.github.dragonhht.database.manager.mapper;

import com.github.dragonhht.database.manager.dto.Page;
import com.github.dragonhht.database.manager.dto.ResultData;
import com.github.dragonhht.database.manager.dto.SqlStatement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("${sqlStatement.sql}")
    List<ResultData> selectPage(Page page);

    /**
     * 查询
     * @param sqlStatement
     * @return
     */
    @Select("${sql}")
    List<ResultData> selectList(SqlStatement sqlStatement);

    /**
     * 更新操作
     * @param sqlStatement
     * @return
     */
    @Update("${sql}")
    int update(SqlStatement sqlStatement);

    /**
     * 删除操作.
     * @param sqlStatement
     * @return
     */
    @Delete("${sql}")
    int delete(SqlStatement sqlStatement);

    /**
     * 插入数据.
     * @param sqlStatement
     * @return
     */
    @Insert("${sql}")
    int insert(SqlStatement sqlStatement);

}
