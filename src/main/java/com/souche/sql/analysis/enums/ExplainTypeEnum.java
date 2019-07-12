package com.souche.sql.analysis.enums;

/**
 * @author: shichao
 * @date: 2019/6/21
 * @description: Sql执行计划的访问类型
 */
public enum ExplainTypeEnum {
    /**
     * type显示的是访问类型，是较为重要的一个指标，结果值从好到坏依次是：
     * system > const > eq_ref > ref > fulltext > ref_or_null > index_merge > unique_subquery
     * > index_subquery > range > index > ALL ，
     * 一般来说，得保证查询至少达到range级别，最好能达到ref。
     */
    SYSTEM(0, "system"),
    CONST(1, "const"),
    EQ_REF(2, "eq_ref"),
    REF(3, "ref"),
    FULL_TEXT(4, "fulltext"),
    REF_OR_NULL(5, "ref_or_null"),
    INDEX_MERGE(6, "index_merge"),
    UNIQUE_SUB_QUERY(7, "unique_subquery"),
    INDEX_SUB_QUERY(8, "index_subquery"),
    RANGE(9, "range"),
    INDEX(10, "index"),
    ALL(11, "ALL"),;
    private Integer code;
    private String type;

    ExplainTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public static ExplainTypeEnum getByCode(Integer code) {
        for (ExplainTypeEnum each : ExplainTypeEnum.values()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }

    public static ExplainTypeEnum getByType(String type) {
        for (ExplainTypeEnum each : ExplainTypeEnum.values()) {
            if (each.getType().equals(type)) {
                return each;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
