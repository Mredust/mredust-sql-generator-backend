package com.mredust.sqlgenerator.core;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.mredust.sqlgenerator.core.builder.DataBuilder;
import com.mredust.sqlgenerator.core.builder.JsonBuilder;
import com.mredust.sqlgenerator.core.builder.SQLBuilder;
import com.mredust.sqlgenerator.core.model.vo.GenerateVO;
import com.mredust.sqlgenerator.core.schema.TableSchema;
import com.mredust.sqlgenerator.core.schema.TableSchema.Field;
import com.mredust.sqlgenerator.exception.SchemaException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>数据生成器</p>
 * <p>外观模式，统一生成</p>
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 * @date: 2024/4/30 9:41
 * @version: 1.0
 */
public class GeneratorFacade {
    private GeneratorFacade() {
    }
    
    public static GenerateVO generateData(TableSchema tableSchema) {
        // 校验
        validSchema(tableSchema);
        // 构建sql
        SQLBuilder sqlBuilder = new SQLBuilder();
        // 建表SQL
        String sql = sqlBuilder.buildCreateTableSql(tableSchema);
        // 生成模拟数据
        Integer mockNum = tableSchema.getMockNum();
        List<Map<String, Object>> dataList = DataBuilder.generateData(tableSchema, mockNum);
        // 生成插入 SQL
        String insertSql = sqlBuilder.buildInsertSql(tableSchema, dataList);
        // 生成数据 json
        String dataJson = JsonBuilder.buildJson(dataList);
        // 封装返回
        GenerateVO generateVO = new GenerateVO();
        generateVO.setTableSchema(tableSchema);
        generateVO.setCreateSql(sql);
        generateVO.setDataList(dataList);
        generateVO.setInsertSql(insertSql);
        generateVO.setDataJson(dataJson);
        return generateVO;
    }
    
    /**
     * 校验表结构
     *
     * @param tableSchema 表结构数据
     */
    public static void validSchema(TableSchema tableSchema) {
        if (tableSchema == null) {
            throw new SchemaException("表结构数据不能为空");
        }
        String tableName = tableSchema.getTableName();
        if (StringUtils.isBlank(tableName)) {
            throw new SchemaException("表名不能为空");
        }
        
        Integer mockNum = tableSchema.getMockNum();
        if (mockNum != null && (mockNum < 0 || mockNum > 100)) {
            throw new SchemaException("模拟数据条数输入错误");
        }
        
        List<Field> fieldList = tableSchema.getFieldList();
        if (CollectionUtils.isEmpty(fieldList)) {
            throw new SchemaException("字段列表不能为空");
        }
        for (Field field : fieldList) {
            validField(field);
        }
    }
    
    
    /**
     * 校验字段
     *
     * @param field 字段信息
     */
    private static void validField(Field field) {
        String fieldName = field.getFieldName();
        String fieldType = field.getFieldType();
        if (StringUtils.isBlank(fieldName)) {
            throw new SchemaException("字段名不能为空");
        }
        if (StringUtils.isBlank(fieldType)) {
            throw new SchemaException("字段类型不能为空");
        }
    }
}
