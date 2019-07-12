package com.souche.sql.analysis.intercept;

import com.souche.sql.analysis.enums.ExplainTypeEnum;
import com.souche.sql.analysis.helper.LruCache;
import com.souche.sql.analysis.model.ExplainVO;
import com.souche.optimus.dao.BasicDao;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.*;

/**
 * @author: shichao
 * @date: 2019/6/24
 * @description: Mybatis sql拦截器插件,只针对查询语句
 */
@Intercepts({@Signature(args = {Statement.class, ResultHandler.class}, method = "query", type = StatementHandler.class)})
public class SqlInterceptor implements Interceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BasicDao basicDao;
    private LinkedHashMap lruCache;

    private static final String SELECT_PRE = "select";


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (null == lruCache) {
            lruCache = LruCache.getInstance(8);
        }
        long startTime = System.currentTimeMillis();
        StatementHandler statementHandler = (StatementHandler) target;
        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long sqlCost = endTime - startTime;
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            logger.info("SqlInterceptor start to  sql:{}", sql);
            if (null != lruCache.get(sql)) {
                Object parameterObject = boundSql.getParameterObject();
                List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();

                // 格式化Sql语句，去除换行符，替换参数
                sql = formatSql(sql, parameterObject, parameterMappingList);
                logger.info("SqlInterceptor start to explain sql:{}", sql);

                if (sql.startsWith(SELECT_PRE)) {
                    String explainSql = "explain " + sql;
                    List<ExplainVO> explainVOS = null;
                    try {
                        explainVOS = basicDao.selectListBySql(explainSql, null, ExplainVO.class);
                    } catch (Exception e) {
                        logger.error("获取SQL执行计划异常：{},SQL:{}", e, explainSql);
                    }
                    if (null != explainVOS && !explainVOS.isEmpty()) {
                        for (ExplainVO explainVO : explainVOS) {
                            if (ExplainTypeEnum.ALL.getType().equals(explainVO.getType())) {
                                logger.warn("警告！警告！,该SQL语句的执行方式是全表扫描，一定要及时优化哦！！！,SQL:{}", sql);
                                logger.warn("SQL：[" + sql + "]，执行耗时[" + sqlCost + "ms]");
                                break;
                            }
                        }
                    }
                }
            }

        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private String formatSql(String sql, Object parameterObject, List<ParameterMapping> parameterMappingList) {
        // 输入判断是否为空
        if ("".equals(sql) || sql.length() == 0) {
            return "";
        }
        // 美化sql
        sql = beautifySql(sql);

        // 不传参数的场景，直接把Sql美化一下返回出去
        if (parameterObject == null || parameterMappingList == null || parameterMappingList.size() == 0) {
            return sql;
        }

        // 定义一个没有替换过占位符的sql，用于出异常时返回
        String sqlWithoutReplacePlaceholder = sql;
        try {
            Class<?> parameterObjectClass = parameterObject.getClass();
            if (isMap(parameterObjectClass)) {
                // 如果参数是Map则直接强转，通过map.get(key)方法获取真正的属性值
                // 这里主要是为了处理<insert>、<delete>、<update>、<select>时传入parameterType为map的场景
                Map<?, ?> paramMap = (Map<?, ?>) parameterObject;
                sql = handleMapParameter(sql, paramMap, parameterMappingList);
            } else {
                // 通用场景，比如传的是一个自定义的对象或者八种基本数据类型之一或者String
                sql = handleCommonParameter(sql, parameterMappingList, parameterObjectClass, parameterObject);
            }

        } catch (Exception e) {
            // 占位符替换过程中出现异常，则返回没有替换过占位符但是格式美化过的sql，这样至少保证sql语句比BoundSql中的sql更好看
            return sqlWithoutReplacePlaceholder;
        }

        return sql;
    }

    /**
     * 处理通用场景
     *
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private String handleCommonParameter(String sql, List<ParameterMapping> parameterMappingList,
                                         Class<?> parameterObjectClass, Object parameterObject) throws Exception {
        for (ParameterMapping parameterMapping : parameterMappingList) {
            String propertyValue = null;
            // 基本数据类型或者基本数据类型的包装类，直接toString即可获取其真正的参数值，其余直接取paramterMapping中的property属性即可
            if (isPrimitiveOrPrimitiveWrapper(parameterObjectClass)) {
                propertyValue = parameterObject.toString();
            } else {
                String propertyName = parameterMapping.getProperty();

                Field field = parameterObjectClass.getDeclaredField(propertyName);
                // 要获取Field中的属性值，这里必须将私有属性的accessible设置为true
                field.setAccessible(true);
                propertyValue = String.valueOf(field.get(parameterObject));
                if (parameterMapping.getJavaType().isAssignableFrom(String.class)) {
                    propertyValue = "\"" + propertyValue + "\"";
                }
            }

            sql = sql.replaceFirst("\\?", propertyValue);
        }

        return sql;
    }

    /**
     * 处理Map场景
     */
    private String handleMapParameter(String sql, Map<?, ?> paramMap, List<ParameterMapping> parameterMappingList) {
        for (ParameterMapping parameterMapping : parameterMappingList) {
            Object propertyName = parameterMapping.getProperty();
            Object propertyValue = paramMap.get(propertyName);
            if (propertyValue != null) {
                if (propertyValue.getClass().isAssignableFrom(String.class)) {
                    propertyValue = "\"" + propertyValue + "\"";
                }

                sql = sql.replaceFirst("\\?", propertyValue.toString());
            }
        }
        return sql;
    }

    /**
     * @param sql
     * @param col
     * @Description: 处理List场景
     */
    private String handleListParameter(String sql, Collection<?> col) {
        if (col != null && col.size() != 0) {
            for (Object obj : col) {
                String value = null;
                Class<?> objClass = obj.getClass();

                // 只处理基本数据类型、基本数据类型的包装类、String这三种
                // 如果是复合类型也是可以的，不过复杂点且这种场景较少，写代码的时候要判断一下要拿到的是复合类型中的哪个属性
                if (isPrimitiveOrPrimitiveWrapper(objClass)) {
                    value = obj.toString();
                } else if (objClass.isAssignableFrom(String.class)) {
                    value = "\"" + obj.toString() + "\"";
                }

                sql = sql.replaceFirst("\\?", value);
            }
        }

        return sql;
    }

    private String beautifySql(String sql) {
        sql = sql.replaceAll("[\\s\n ]+", " ");
        return sql;
    }

    /**
     * 是否基本数据类型或者基本数据类型的包装类
     */
    private boolean isPrimitiveOrPrimitiveWrapper(Class<?> parameterObjectClass) {
        return parameterObjectClass.isPrimitive() || (parameterObjectClass.isAssignableFrom(Byte.class)
                || parameterObjectClass.isAssignableFrom(Short.class)
                || parameterObjectClass.isAssignableFrom(Integer.class)
                || parameterObjectClass.isAssignableFrom(Long.class)
                || parameterObjectClass.isAssignableFrom(Double.class)
                || parameterObjectClass.isAssignableFrom(Float.class)
                || parameterObjectClass.isAssignableFrom(Character.class)
                || parameterObjectClass.isAssignableFrom(Boolean.class));
    }

    /**
     * 是否Map的实现类
     */
    private boolean isMap(Class<?> parameterObjectClass) {
        Class<?>[] interfaceClasses = parameterObjectClass.getInterfaces();
        for (Class<?> interfaceClass : interfaceClasses) {
            if (interfaceClass.isAssignableFrom(Map.class)) {
                return true;
            }
        }

        return false;
    }
}
