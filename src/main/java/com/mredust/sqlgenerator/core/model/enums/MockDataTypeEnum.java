package com.mredust.sqlgenerator.core.model.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模拟数据类型枚举
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Getter
public enum MockDataTypeEnum {
    
    STRING("字符串"),
    NAME("人名"),
    CITY("城市"),
    URL("网址"),
    EMAIL("邮箱"),
    IP("IP"),
    INTEGER("整数"),
    DECIMAL("小数"),
    UNIVERSITY("大学"),
    DATE("日期"),
    TIMESTAMP("时间戳"),
    PHONE("手机号");
    
    private final String value;
    
    MockDataTypeEnum(String value) {
        this.value = value;
    }
    
    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(MockDataTypeEnum::getValue).collect(Collectors.toList());
    }
    
    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static MockDataTypeEnum getEnumByValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (MockDataTypeEnum mockDataTypeEnum : MockDataTypeEnum.values()) {
            if (mockDataTypeEnum.value.equals(value)) {
                return mockDataTypeEnum;
            }
        }
        return null;
    }
    
}
