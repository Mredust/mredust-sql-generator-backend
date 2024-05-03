package com.mredust.sqlgenerator.core.builder;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.mredust.sqlgenerator.core.builder.generator.DataGenerator;
import com.mredust.sqlgenerator.core.builder.generator.DataGeneratorFactory;
import com.mredust.sqlgenerator.core.model.enums.MockTypeEnum;
import com.mredust.sqlgenerator.core.schema.TableSchema;
import com.mredust.sqlgenerator.core.schema.TableSchema.Field;

import java.util.*;

/**
 * 模拟数据构建
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */

public class DataBuilder {
    
    private DataBuilder() {
    
    }
    
    public static List<Map<String, Object>> generateData(TableSchema tableSchema, int mockNum) {
        ArrayList<Map<String, Object>> dataList = new ArrayList<>();
        List<Field> fieldList = tableSchema.getFieldList();
        if (mockNum <= 0) {
            mockNum = 20;
        }
        // 初始化数据
        for (int i = 0; i < mockNum; i++) {
            dataList.add(new HashMap<>());
        }
        for (Field field : fieldList) {
            MockTypeEnum mockTypeEnum = Optional.ofNullable(MockTypeEnum.getEnumByValue(field.getMockType())).orElse(MockTypeEnum.NONE);
            DataGenerator dataGenerator = DataGeneratorFactory.getGenerator(mockTypeEnum);
            List<String> mockDataList = dataGenerator.dataGenerateHandle(field, mockNum);
            String fieldName = field.getFieldName();
            if (CollectionUtils.isNotEmpty(mockDataList)) {
                for (int i = 0; i < mockNum; i++) {
                    dataList.get(i).put(fieldName, mockDataList.get(i));
                }
            }
        }
        return dataList;
    }
}
