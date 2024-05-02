package com.mredust.sqlgenerator.core.builder;

import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLPrimaryKey;
import com.alibaba.druid.sql.ast.statement.SQLTableElement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlCreateTableParser;
import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.core.builder.sql.MySQLDialect;
import com.mredust.sqlgenerator.core.model.enums.MockTypeEnum;
import com.mredust.sqlgenerator.core.schema.TableSchema;
import com.mredust.sqlgenerator.core.schema.TableSchema.Field;
import com.mredust.sqlgenerator.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 表结构生成器
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Slf4j
@Component
public class TableSchemaBuilder {
    private TableSchemaBuilder() {
    }
    
    private static final MySQLDialect SQL_DIALECT = new MySQLDialect();
    
    /**
     * 根据 SQL 生成表结构
     *
     * @param sql SQL语句
     * @return 表结构
     */
    public static TableSchema buildTableSchema(String sql) {
        if (StringUtils.isBlank(sql)) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        try {
            MySqlCreateTableParser parserSql = new MySqlCreateTableParser(sql);
            SQLCreateTableStatement sqlCreateTableStatement = parserSql.parseCreateTable();
            TableSchema tableSchema = new TableSchema();
            // 解析表结构
            tableSchema.setDatabaseName(sqlCreateTableStatement.getSchema());
            tableSchema.setTableName(SQL_DIALECT.parseTableName(sqlCreateTableStatement.getTableName()));
            tableSchema.setTableComment(SQL_DIALECT.parseTableName(sqlCreateTableStatement.getTableName()));
            String tableComment = null;
            if (sqlCreateTableStatement.getComment() != null) {
                tableComment = sqlCreateTableStatement.getComment().toString();
                if (tableComment.length() > 2) {
                    tableComment = tableComment.substring(1, tableComment.length() - 1);
                }
            }
            tableSchema.setTableComment(tableComment);
            // 解析字段结构
            ArrayList<Field> fieldList = new ArrayList<>();
            for (SQLTableElement sqlTableElement : sqlCreateTableStatement.getTableElementList()) {
                if (sqlTableElement instanceof SQLPrimaryKey) {
                    SQLPrimaryKey sqlPrimaryKey = (SQLPrimaryKey) sqlTableElement;
                    String primaryFieldName = SQL_DIALECT.parseFieldName(sqlPrimaryKey.getColumns().get(0).toString());
                    fieldList.forEach(field -> {
                        if (field.getFieldName().equals(primaryFieldName)) {
                            field.setPrimaryKey(true);
                        }
                    });
                } else if (sqlTableElement instanceof SQLColumnDefinition) {
                    SQLColumnDefinition columnDefinition = (SQLColumnDefinition) sqlTableElement;
                    Field field = new Field();
                    field.setFieldName(SQL_DIALECT.parseFieldName(columnDefinition.getNameAsString()));
                    String columnFieldType = columnDefinition.getDataType().toString();
                    int index = columnFieldType.indexOf("(");
                    if (index != -1) {
                        field.setFieldType(columnFieldType.substring(0, index));
                        field.setFieldLength(columnFieldType.substring(index + 1, columnFieldType.indexOf(")")));
                    } else {
                        field.setFieldType(columnFieldType);
                    }
                    String defaultValue = null;
                    if (columnDefinition.getDefaultExpr() != null) {
                        defaultValue = columnDefinition.getDefaultExpr().toString();
                    }
                    field.setDefaultValue(defaultValue);
                    field.setNotNull(columnDefinition.containsNotNullConstaint());
                    String comment = null;
                    if (columnDefinition.getComment() != null) {
                        comment = columnDefinition.getComment().toString();
                        if (comment.length() > 2) {
                            comment = comment.substring(1, comment.length() - 1);
                        }
                    }
                    field.setComment(comment);
                    field.setPrimaryKey(columnDefinition.isPrimaryKey());
                    field.setAutoIncrement(columnDefinition.isAutoIncrement());
                    String onUpdate = null;
                    if (columnDefinition.getOnUpdate() != null) {
                        onUpdate = columnDefinition.getOnUpdate().toString();
                    }
                    field.setOnUpdate(onUpdate);
                    field.setMockType(MockTypeEnum.NONE.getValue());
                    fieldList.add(field);
                }
            }
            tableSchema.setFieldList(fieldList);
            return tableSchema;
        } catch (Exception e) {
            log.error("SQL 解析错误", e);
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "请确认 SQL 语句正确");
        }
    }
}
