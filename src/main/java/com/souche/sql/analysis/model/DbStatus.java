package com.souche.sql.analysis.model;

import com.souche.optimus.dao.annotation.SqlTable;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: shichao
 * @date: 2019/7/11
 * @description: 数据库状态
 */
@SqlTable("information_schema.TABLES")
public class DbStatus implements Serializable {
    private static final long serialVersionUID = -4152970002993820912L;

    /**
     * 数据表所属的数据库名
     */
    private String TABLE_SCHEMA;

    /**
     * 表名称
     */
    private String TABLE_NAME;

    /**
     * 表类型[system view|base table]
     */
    private String TABLE_TYPE;

    /**
     * 使用的数据库引擎[MyISAM|CSV|InnoDB]
     */
    private String ENGINE;

    /**
     * 版本，默认值10
     */
    private Long VERSION;

    /**
     * 行格式[Compact|Dynamic|Fixed]
     */
    private String ROW_FORMAT;

    /**
     * 表里所存多少行数据
     */
    private Long TABLE_ROWS;

    /**
     * 平均行长度
     */
    private Long AVG_ROW_LENGTH;

    /**
     * 数据长度
     */
    private Long DATA_LENGTH;

    /**
     * 最大数据长度
     */
    private Long MAX_DATA_LENGTH;

    /**
     * 索引长度
     */
    private Long INDEX_LENGTH;

    /**
     * 空间碎片
     */
    private Long DATA_FREE;

    /**
     * 做自增主键的自动增量当前值
     */
    private Long AUTO_INCREMENT;

    /**
     * 表的创建时间
     */
    private Timestamp CREATE_TIME;

    /**
     * 表的更新时间
     */
    private Timestamp UPDATE_TIME;

    /**
     *表的检查时间
     */
    private Timestamp CHECK_TIME;

    /**
     * 表的字符校验编码集
     */
    private String TABLE_COLLATION;

    /**
     * 校验和
     */
    private Long CHECKSUM;

    /**
     * 创建选项
     */
    private String CREATE_OPTIONS;

    /**
     * 表描述
     */
    private String TABLE_COMMENT;


    public String getTABLE_SCHEMA() {
        return TABLE_SCHEMA;
    }

    public void setTABLE_SCHEMA(String TABLE_SCHEMA) {
        this.TABLE_SCHEMA = TABLE_SCHEMA;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public String getTABLE_TYPE() {
        return TABLE_TYPE;
    }

    public void setTABLE_TYPE(String TABLE_TYPE) {
        this.TABLE_TYPE = TABLE_TYPE;
    }

    public String getENGINE() {
        return ENGINE;
    }

    public void setENGINE(String ENGINE) {
        this.ENGINE = ENGINE;
    }

    public Long getVERSION() {
        return VERSION;
    }

    public void setVERSION(Long VERSION) {
        this.VERSION = VERSION;
    }

    public String getROW_FORMAT() {
        return ROW_FORMAT;
    }

    public void setROW_FORMAT(String ROW_FORMAT) {
        this.ROW_FORMAT = ROW_FORMAT;
    }

    public Long getTABLE_ROWS() {
        return TABLE_ROWS;
    }

    public void setTABLE_ROWS(Long TABLE_ROWS) {
        this.TABLE_ROWS = TABLE_ROWS;
    }

    public Long getAVG_ROW_LENGTH() {
        return AVG_ROW_LENGTH;
    }

    public void setAVG_ROW_LENGTH(Long AVG_ROW_LENGTH) {
        this.AVG_ROW_LENGTH = AVG_ROW_LENGTH;
    }

    public Long getDATA_LENGTH() {
        return DATA_LENGTH;
    }

    public void setDATA_LENGTH(Long DATA_LENGTH) {
        this.DATA_LENGTH = DATA_LENGTH;
    }

    public Long getMAX_DATA_LENGTH() {
        return MAX_DATA_LENGTH;
    }

    public void setMAX_DATA_LENGTH(Long MAX_DATA_LENGTH) {
        this.MAX_DATA_LENGTH = MAX_DATA_LENGTH;
    }

    public Long getINDEX_LENGTH() {
        return INDEX_LENGTH;
    }

    public void setINDEX_LENGTH(Long INDEX_LENGTH) {
        this.INDEX_LENGTH = INDEX_LENGTH;
    }

    public Long getDATA_FREE() {
        return DATA_FREE;
    }

    public void setDATA_FREE(Long DATA_FREE) {
        this.DATA_FREE = DATA_FREE;
    }

    public Long getAUTO_INCREMENT() {
        return AUTO_INCREMENT;
    }

    public void setAUTO_INCREMENT(Long AUTO_INCREMENT) {
        this.AUTO_INCREMENT = AUTO_INCREMENT;
    }

    public Timestamp getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Timestamp CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public Timestamp getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(Timestamp UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public Timestamp getCHECK_TIME() {
        return CHECK_TIME;
    }

    public void setCHECK_TIME(Timestamp CHECK_TIME) {
        this.CHECK_TIME = CHECK_TIME;
    }

    public String getTABLE_COLLATION() {
        return TABLE_COLLATION;
    }

    public void setTABLE_COLLATION(String TABLE_COLLATION) {
        this.TABLE_COLLATION = TABLE_COLLATION;
    }

    public Long getCHECKSUM() {
        return CHECKSUM;
    }

    public void setCHECKSUM(Long CHECKSUM) {
        this.CHECKSUM = CHECKSUM;
    }

    public String getCREATE_OPTIONS() {
        return CREATE_OPTIONS;
    }

    public void setCREATE_OPTIONS(String CREATE_OPTIONS) {
        this.CREATE_OPTIONS = CREATE_OPTIONS;
    }

    public String getTABLE_COMMENT() {
        return TABLE_COMMENT;
    }

    public void setTABLE_COMMENT(String TABLE_COMMENT) {
        this.TABLE_COMMENT = TABLE_COMMENT;
    }

    @Override
    public String toString() {
        return "DbStatus{" +
                "TABLE_SCHEMA='" + TABLE_SCHEMA + '\'' +
                ", TABLE_NAME='" + TABLE_NAME + '\'' +
                ", TABLE_TYPE='" + TABLE_TYPE + '\'' +
                ", ENGINE='" + ENGINE + '\'' +
                ", VERSION=" + VERSION +
                ", ROW_FORMAT='" + ROW_FORMAT + '\'' +
                ", TABLE_ROWS=" + TABLE_ROWS +
                ", AVG_ROW_LENGTH=" + AVG_ROW_LENGTH +
                ", DATA_LENGTH=" + DATA_LENGTH +
                ", MAX_DATA_LENGTH=" + MAX_DATA_LENGTH +
                ", INDEX_LENGTH=" + INDEX_LENGTH +
                ", DATA_FREE=" + DATA_FREE +
                ", AUTO_INCREMENT=" + AUTO_INCREMENT +
                ", CREATE_TIME=" + CREATE_TIME +
                ", UPDATE_TIME=" + UPDATE_TIME +
                ", CHECK_TIME=" + CHECK_TIME +
                ", TABLE_COLLATION='" + TABLE_COLLATION + '\'' +
                ", CHECKSUM=" + CHECKSUM +
                ", CREATE_OPTIONS='" + CREATE_OPTIONS + '\'' +
                ", TABLE_COMMENT='" + TABLE_COMMENT + '\'' +
                '}';
    }
}
