package com.mredust.sqlgenerator.core.builder.generator;

import com.mredust.sqlgenerator.core.schema.TableSchema;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型：固定
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
public class FixedDataGenerator implements DataGenerator {
    /**
     * 生成
     *
     * @param field   字段信息
     * @param mockNum 行数
     * @return 生成的数据列表
     */
    @Override
    public List<String> dataGenerateHandle(TableSchema.Field field, int mockNum) {
        String mockParams = field.getMockParams();
        if (StringUtils.isBlank(mockParams)) {
            mockParams = "4";
        }
        List<String> list = new ArrayList<>(mockNum);
        for (int i = 0; i < mockNum; i++) {
            list.add(mockParams);
        }
        return list;
    }
}
