package com.mredust.sqlgenerator.core.builder.sql;

/**
 * SQL 方言
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
public interface SQLDialect {
    /**
     * 封装表名
     *
     * @param tableName 表名
     * @return
     */
    String wrapTableName(String tableName);
    
    /**
     * 解析表名
     *
     * @param tableName 表名
     * @return
     */
    String parseTableName(String tableName);
    
    /**
     * 封装字段名
     *
     * @param fieldName 字段名
     * @return
     */
    String wrapFieldName(String fieldName);
    
    /**
     * 解析字段名
     *
     * @param fieldName 字段名
     * @return
     */
    String parseFieldName(String fieldName);
    
    
}
