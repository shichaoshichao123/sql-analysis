package com.souche.sql.analysis.assistant;

import com.souche.sql.analysis.model.DbStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: shichao
 * @date: 2019/7/11
 * @description: 默认的数据库助手服务
 */
public class DefaultDbAssistant extends AbstractDbAssistant {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取指定表的数据量大小
     * 单位是MB
     *
     * @param tableName
     * @return
     */
    public int getTableDataSize(String tableName) {
        DbStatus dbStatus = dbStatuses.get(tableName);
        if (null == dbStatus) {
            dbStatus = getDbStatusByTableName(tableName);
        }
        logger.info("dbStatus:{}", dbStatus);
        if (null != dbStatus) {
            Long dataLength = dbStatus.getDATA_LENGTH();
            Long indexLength = dbStatus.getINDEX_LENGTH();

            return (int) (dataLength + indexLength / 1024 / 1024);
        }
        return 0;

    }
}
