package com.souche.sql.analysis.model;

import java.io.Serializable;

/**
 * @author: shichao
 * @date: 2019/7/9
 * @description: 用于展现Sql分析结果
 */
public class ExplainVO implements Serializable {
    private static final long serialVersionUID = 7072875016648262712L;
    /**
     * Sql执行计划ID
     */
    private Integer id;

    /**
     * 查询类型
     */
    private String selectType;

    /**
     * 使用的表
     */
    private String table;

    /**
     * 执行计划分区
     */
    private String partitions;

    /**
     * 执行计划类型
     */
    private String type;

    /**
     * 可能使用的键
     */
    private String possible_keys;

    /**
     * 使用的key
     */
    private String key;

    /**
     * 键长度
     */
    private String kenLen;

    /**
     * 参考的索引键
     */
    private String ref;

    /**
     * 扫描的行数
     */
    private String rows;

    /**
     * 结果遍历比例，比例越大越好
     */
    private String filtered;

    /**
     * 执行计划扩展信息
     */
    private String extra;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getPartitions() {
        return partitions;
    }

    public void setPartitions(String partitions) {
        this.partitions = partitions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPossible_keys() {
        return possible_keys;
    }

    public void setPossible_keys(String possible_keys) {
        this.possible_keys = possible_keys;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKenLen() {
        return kenLen;
    }

    public void setKenLen(String kenLen) {
        this.kenLen = kenLen;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getFiltered() {
        return filtered;
    }

    public void setFiltered(String filtered) {
        this.filtered = filtered;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ExplainVO{" +
                "id=" + id +
                ", selectType='" + selectType + '\'' +
                ", table='" + table + '\'' +
                ", partitions='" + partitions + '\'' +
                ", type='" + type + '\'' +
                ", possible_keys='" + possible_keys + '\'' +
                ", key='" + key + '\'' +
                ", kenLen='" + kenLen + '\'' +
                ", ref='" + ref + '\'' +
                ", rows='" + rows + '\'' +
                ", filtered='" + filtered + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
