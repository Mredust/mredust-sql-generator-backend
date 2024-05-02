package com.mredust.sqlgenerator.core.builder.generator;


import com.mredust.sqlgenerator.core.schema.TableSchema.Field;

import java.util.List;

/**
 * 数据生成器
 *
 * @author Mredust
 */
public interface DataGenerator {
    /**
     * 生成
     *
     * @param field   字段信息
     * @param mockNum 行数
     * @return 生成的数据列表
     */
    List<String> dataGenerateHandle(Field field, int mockNum);
}
