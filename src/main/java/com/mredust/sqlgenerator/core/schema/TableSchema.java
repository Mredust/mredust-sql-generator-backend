package com.mredust.sqlgenerator.core.schema;

import lombok.Data;

import java.util.List;

/**
 * 表结构
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 * @date: 2024/4/30 9:37
 * @version: 1.0
 */
@Data
public class TableSchema {
    /**
     * 数据库名
     */
    private String databaseName;
    
    /**
     * 表名
     */
    private String tableName;
    
    /**
     * 表注释
     */
    private String tableComment;
    
    /**
     * 模拟数据条数
     */
    private Integer mockNum;
    
    /**
     * 列信息列表
     */
    private List<Field> fieldList;
    
    /**
     * 列信息
     */
    @Data
    public static class Field {
        /**
         * 字段名
         */
        private String fieldName;
        
        /**
         * 字段类型
         */
        private String fieldType;
        
        /**
         * 字段长度
         */
        private String fieldLength;
        /**
         * 默认值
         */
        private String defaultValue;
        
        /**
         * 是否非空
         */
        private boolean notNull;
        
        /**
         * 注释（字段中文名）
         */
        private String comment;
        
        
        /**
         * 是否为主键
         */
        private boolean primaryKey;
        
        /**
         * 是否自增
         */
        private boolean autoIncrement;
        
        /**
         * 模拟数据类型
         */
        private String mockType;
        
        /**
         * 模拟参数
         */
        private String mockParams;
        
        /**
         * 附加条件
         */
        private String onUpdate;
    }
}
