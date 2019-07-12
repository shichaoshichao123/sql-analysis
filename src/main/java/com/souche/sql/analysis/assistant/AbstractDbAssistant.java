package com.souche.sql.analysis.assistant;

import com.souche.optimus.common.util.StringUtil;
import com.souche.optimus.dao.query.QueryObj;
import com.souche.sql.analysis.model.DbStatus;
import com.souche.optimus.dao.BasicDao;
import com.souche.sql.analysis.constant.SqlData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: shichao
 * @date: 2019/7/11
 * @description: 基础数据库助手服务
 */
public abstract class AbstractDbAssistant implements DbAssistant {
    @Autowired
    private BasicDao basicDao;
    Map<String, DbStatus> dbStatuses = new ConcurrentHashMap(4);

    @Override
    public DbStatus getDbStatusByTableName(String tableName) {
        if (StringUtil.isEmpty(tableName)) {
            return null;
        }
        DbStatus dbStatus = new DbStatus();
        dbStatus.setTABLE_NAME(tableName);
        QueryObj queryObj = new QueryObj();
        queryObj.setQuerydo(dbStatus);
        List<DbStatus> result = basicDao.findListByQuery(queryObj,DbStatus.class);
        if (null != result) {
            dbStatuses = null;
        }
        return result.get(0);
    }
}
