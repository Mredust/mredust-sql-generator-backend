package com.mredust.sqlgenerator.core.model.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模拟类型枚举
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Getter
public enum MockTypeEnum {

    NONE("不模拟"),
    FIXED("固定"),
    RANDOM("随机"),
    DICT("词库");

    private final String value;

    MockTypeEnum(String value) {
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(MockTypeEnum::getValue).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static MockTypeEnum getEnumByValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (MockTypeEnum mockTypeEnum : MockTypeEnum.values()) {
            if (mockTypeEnum.value.equals(value)) {
                return mockTypeEnum;
            }
        }
        return null;
    }
    
}
