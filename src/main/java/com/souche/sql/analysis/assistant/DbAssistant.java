package com.souche.sql.analysis.assistant;

import com.souche.sql.analysis.model.DbStatus;

/**
 * @author: shichao
 * @date: 2019/7/11
 * @description: 数据库状态助手服务
 */
public interface DbAssistant {

    /**
     * 获取指定表的数据库状态
     *
     * @return
     */
    DbStatus getDbStatusByTableName(String tableName);
}
