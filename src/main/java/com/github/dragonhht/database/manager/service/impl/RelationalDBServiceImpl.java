package com.github.dragonhht.database.manager.service.impl;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.dto.ResultData;
import com.github.dragonhht.database.manager.service.RelationalDBService;
import com.github.dragonhht.database.manager.service.RelationalService;
import com.github.dragonhht.database.manager.utils.DataSourceUtil;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import com.github.dragonhht.database.manager.vo.DBInfo;
import com.github.dragonhht.database.manager.vo.TableInfo;
import com.github.dragonhht.database.manager.vo.ViewInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * 关系型数据库的数据库相关操作实现类.
 *
 * @author: huang
 * @Date: 2019-7-15
 */
@Service
@Slf4j
public class RelationalDBServiceImpl implements RelationalDBService {

    @Autowired
    private RelationalService relationalService;

    @Override
    public List<DBInfo> getAllDB(ConnectionInfo connectionInfo) throws Exception {
        DataSourceUtil.INSTANCE.setNowDataSource(connectionInfo);
        return getAllDB();
    }

    @Override
    public List<DBInfo> getAllDB() throws Exception {
        RelationalPlatform platform = DataSourceUtil.getNowPlatform();
        List<DBInfo> list = new LinkedList<>();
        String sql = "";
        // TODO 还有其它关系型数据库
        switch (platform) {
            case MYSQL:
                sql = "show databases";
                break;
            default:
                break;
        }
        List<ResultData> datas = relationalService.selectList(sql);
        if (datas != null && datas.size() > 1) {
            for (int i = 1; i < datas.size(); i++) {
                DBInfo info = new DBInfo();
                info.setPlatform(platform);
                info.setName(String.valueOf(datas.get(i).getValues().get(0)));
                list.add(info);
            }
        }
        return list;
    }

    @Override
    public List<TableInfo> getAllTables(ConnectionInfo info) throws Exception {
        DataSourceUtil.INSTANCE.setNowDataSource(info);
        return this.getAllNowTables();
    }

    @Override
    public List<ViewInfo> getAllViews(ConnectionInfo info) throws Exception {
        DataSourceUtil.INSTANCE.setNowDataSource(info);
        return this.getAllNowViews();
    }

    @Override
    public List<TableInfo> getAllNowTables() throws Exception {
        RelationalPlatform platform = DataSourceUtil.getNowPlatform();
        List<TableInfo> list = new LinkedList<>();
        String sql = "";
        // TODO 还有其它关系型数据库
        switch (platform) {
            case MYSQL:
                sql = "show tables";
                break;
            default:
                break;
        }
        List<ResultData> datas = relationalService.selectList(sql);
        if (datas != null && datas.size() > 1) {
            for (int i = 1; i < datas.size(); i++) {
                TableInfo table = new TableInfo();
                table.setName(String.valueOf(datas.get(i).getValues().get(0)));
                list.add(table);
            }
        }
        return list;
    }

    @Override
    public List<ViewInfo> getAllNowViews() throws Exception {
        RelationalPlatform platform = DataSourceUtil.getNowPlatform();
        List<ViewInfo> list = new LinkedList<>();
        String sql = "";
        // TODO 还有其它关系型数据库
        switch (platform) {
            case MYSQL:
                sql = "show table status where comment='view'";
                break;
            default:
                break;
        }
        List<ResultData> datas = relationalService.selectList(sql);
        if (datas != null && datas.size() > 1) {
            for (int i = 1; i < datas.size(); i++) {
                ViewInfo view = new ViewInfo();
                view.setName(String.valueOf(datas.get(i).getValues().get(0)));
                list.add(view);
            }
        }
        return list;
    }

    @Override
    public boolean createDB(DBInfo info) throws Exception {
        boolean ok = false;
        // 要考虑其他数据库
        String sql = "CREATE DATABASE " + info.getName();
        if (!StringUtils.isEmpty(info.getCharset())) {
            sql += " DEFAULT CHARSET " + info.getCharset();
        }
        try {
            relationalService.ddl(sql);
            ok = true;
        } catch (Exception e) {
            log.error("创建数据库失败", e);
        }
        return ok;
    }
}
