package com.souche.sql.analysis.annotation;

import com.souche.sql.analysis.enums.ExplainTypeEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SqlExplain {

    /**
     * 需要分析的SQL语句，默认为空
     * @return
     */
    String sql() default "";

    /**
     * 预期的SQL访问类型
     * @return
     */
    ExplainTypeEnum type() default ExplainTypeEnum.RANGE;

}
