package com.mredust.sqlgenerator.core.builder.generator;


import cn.hutool.core.date.DateUtil;
import com.mredust.sqlgenerator.core.schema.TableSchema.Field;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类型：不模拟
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
public class DefaultDataGenerator implements DataGenerator {
    /**
     * 生成
     *
     * @param field   字段信息
     * @param mockNum 行数
     * @return 生成的数据列表
     */
    @Override
    public List<String> dataGenerateHandle(Field field, int mockNum) {
        String mockParams = field.getMockParams();
        ArrayList<String> list = new ArrayList<>(mockNum);
        // 主键采用递增数据
        if (field.isPrimaryKey()) {
            if (StringUtils.isBlank(mockParams)) {
                mockParams = "1";
            }
            int initValue = Integer.parseInt(mockParams);
            for (int i = 0; i < mockNum; i++) {
                list.add(String.valueOf(initValue + i));
            }
            return list;
        }
        // 使用默认值
        String defaultValue = field.getDefaultValue();
        // 特殊逻辑，日期要伪造数据
        if ("CURRENT_TIMESTAMP".equals(defaultValue)) {
            defaultValue = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        }
        if (StringUtils.isNotBlank(defaultValue)) {
            for (int i = 0; i < mockNum; i++) {
                list.add(defaultValue);
            }
        }
        return list;
    }
}
