package com.mredust.sqlgenerator.core.builder;

import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.core.builder.sql.MySQLDialect;
import com.mredust.sqlgenerator.core.builder.sql.SQLDialect;
import com.mredust.sqlgenerator.core.builder.sql.SQLDialectFactory;
import com.mredust.sqlgenerator.core.model.enums.FieldTypeEnum;
import com.mredust.sqlgenerator.core.model.enums.MockTypeEnum;
import com.mredust.sqlgenerator.core.schema.TableSchema;
import com.mredust.sqlgenerator.core.schema.TableSchema.Field;
import com.mredust.sqlgenerator.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SQL 生成器
 * 策略模式:支持不同方言
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Slf4j
public class SQLBuilder {
    /**
     * 方言
     */
    private SQLDialect sqlDialect;
    
    public SQLBuilder() {
        this.sqlDialect = SQLDialectFactory.getDialect(MySQLDialect.class.getName());
    }
    
    public SQLBuilder(SQLDialect sqlDialect) {
        this.sqlDialect = sqlDialect;
    }
    
    public void setSqlDialect(SQLDialect sqlDialect) {
        this.sqlDialect = sqlDialect;
    }
    
    /**
     * 构建建表 SQL
     *
     * @param tableSchema 表结构
     * @return 建表SQL
     */
    public String buildCreateTableSql(TableSchema tableSchema) {
        // 初始模板
        String initCreateTableTemplate = "-- %s%s create table if not exists %s (%s %s %s) comment='%s';";
        // 构建表名
        String tableName = sqlDialect.wrapTableName(tableSchema.getTableName());
        // 字段构建
        List<Field> fieldList = tableSchema.getFieldList();
        StringBuilder fieldBuilder = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            fieldBuilder.append(buildCreateFieldSql(field));
            if (i != fieldList.size() - 1) {
                fieldBuilder.append(",");
                fieldBuilder.append(StringUtils.LF);
            }
        }
        String fieldInfo = fieldBuilder.toString();
        String tableComment = tableSchema.getTableComment();
        String annotation = tableName;
        if (StringUtils.isNotBlank(tableComment)) {
            annotation = tableComment;
        }
        return String.format(initCreateTableTemplate, annotation, StringUtils.LF, tableName, StringUtils.LF, fieldInfo, StringUtils.LF, tableComment);
    }
    
    public String buildInsertSql(TableSchema tableSchema, List<Map<String, Object>> dataList) {
        // 初始模板
        String initInsertDataTemplate = "insert into %s (%s) values (%s);";
        // 表名
        String tableName = sqlDialect.wrapTableName(tableSchema.getTableName());
        // 表字段
        List<Field> fieldList = tableSchema.getFieldList();
        // 过滤掉不模拟的字段
        fieldList = fieldList.stream()
                .filter(field -> {
                    MockTypeEnum mockTypeEnum = Optional.ofNullable(MockTypeEnum.getEnumByValue(field.getMockType())).orElse(MockTypeEnum.NONE);
                    return !MockTypeEnum.NONE.equals(mockTypeEnum);
                }).collect(Collectors.toList());
        StringBuilder resultStringBuilder = new StringBuilder();
        int total = dataList.size();
        for (int i = 0; i < total; i++) {
            Map<String, Object> dataRow = dataList.get(i);
            String keyStr = fieldList.stream()
                    .map(field -> sqlDialect.wrapFieldName(field.getFieldName()))
                    .collect(Collectors.joining(", "));
            String valueStr = fieldList.stream()
                    .map(field -> getValueStr(field, dataRow.get(field.getFieldName())))
                    .collect(Collectors.joining(", "));
            // 填充模板
            String result = String.format(initInsertDataTemplate, tableName, keyStr, valueStr);
            resultStringBuilder.append(result);
            // 最后一个字段后没有换行
            if (i != total - 1) {
                resultStringBuilder.append(StringUtils.LF);
            }
        }
        return resultStringBuilder.toString();
    }
    
    
    /**
     * 构建字段 SQL
     *
     * @param field 字段信息
     * @return 字段 SQL
     */
    private String buildCreateFieldSql(Field field) {
        if (field == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        String fieldName = sqlDialect.wrapFieldName(field.getFieldName());
        String fieldType = field.getFieldType();
        String fieldLength = field.getFieldLength();
        String defaultValue = field.getDefaultValue();
        boolean notNull = field.isNotNull();
        String comment = field.getComment();
        boolean isPrimaryKey = field.isPrimaryKey();
        boolean isAutoIncrement = field.isAutoIncrement();
        
        /* SQL 语句模板
          字段名 字段类型 字段长度 是否为空 默认值 是否自增 注释 是否主键
          column_name column_type(column_length) not null default 0 auto_increment comment '注释' primary key,
         */
        StringBuilder fieldBuilder = new StringBuilder();
        // 字段名
        fieldBuilder.append(fieldName);
        // 字段类型(字段长度) int bigint(1) varchar(255)
        if (StringUtils.isNotBlank(fieldLength)) {
            String temp = String.format("%s(%s)", fieldType, fieldLength);
            fieldBuilder.append(buildField(temp));
        } else {
            fieldBuilder.append(buildField(fieldType));
        }
        // 是否为空
        String notNullStr = notNull ? "not null" : "null";
        fieldBuilder.append(buildField(notNullStr));
        // 默认值
        if (StringUtils.isNotBlank(defaultValue)) {
            defaultValue = String.format("%s%s%s", "default", StringUtils.SPACE, defaultValue);
            fieldBuilder.append(buildField(defaultValue));
        }
        // 是否自增
        if (isAutoIncrement) {
            fieldBuilder.append(buildField("auto_increment"));
        }
        // 注释
        if (StringUtils.isNotBlank(comment)) {
            comment = String.format("comment%s'%s'", StringUtils.SPACE, comment);
            fieldBuilder.append(buildField(comment));
        }
        // 是否为主键
        if (isPrimaryKey) {
            fieldBuilder.append(buildField("primary key"));
        }
        return fieldBuilder.toString();
    }
    
    
    private static String buildField(String field) {
        return StringUtils.SPACE + field;
    }
    
    /**
     * 根据列的属性获取值字符串
     *
     * @param field
     * @param value
     * @return
     */
    public static String getValueStr(Field field, Object value) {
        if (field == null || value == null) {
            return "''";
        }
        FieldTypeEnum fieldTypeEnum = Optional.ofNullable(FieldTypeEnum.getEnumByValue(field.getFieldType()))
                .orElse(FieldTypeEnum.TEXT);
        String result = String.valueOf(value);
        switch (fieldTypeEnum) {
            case DATETIME:
            case TIMESTAMP:
                return "CURRENT_TIMESTAMP".equalsIgnoreCase(result) ? result : String.format("'%s'", value);
            case DATE:
            case TIME:
            case CHAR:
            case VARCHAR:
            case TINYTEXT:
            case TEXT:
            case MEDIUMTEXT:
            case LONGTEXT:
            case TINYBLOB:
            case BLOB:
            case MEDIUMBLOB:
            case LONGBLOB:
            case BINARY:
            case VARBINARY:
                return String.format("'%s'", value);
            default:
                return result;
        }
    }
}
