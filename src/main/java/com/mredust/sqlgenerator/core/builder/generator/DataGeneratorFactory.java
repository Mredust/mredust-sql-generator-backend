package com.mredust.sqlgenerator.core.builder.generator;

import com.mredust.sqlgenerator.core.model.enums.MockTypeEnum;

import java.util.EnumMap;
import java.util.Optional;

/**
 * 模拟数据生成器工厂
 * 单例（饿汉式） + 工厂
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
public class DataGeneratorFactory {
    private DataGeneratorFactory() {
    }
    
    private static final EnumMap<MockTypeEnum, DataGenerator> MOCK_TYPE_ENUM_DATA_GENERATOR_ENUM_MAP = new EnumMap<>(MockTypeEnum.class);
    
    static {
        MOCK_TYPE_ENUM_DATA_GENERATOR_ENUM_MAP.put(MockTypeEnum.NONE, new DefaultDataGenerator());
        MOCK_TYPE_ENUM_DATA_GENERATOR_ENUM_MAP.put(MockTypeEnum.FIXED, new FixedDataGenerator());
        MOCK_TYPE_ENUM_DATA_GENERATOR_ENUM_MAP.put(MockTypeEnum.RANDOM, new RandomDataGenerator());
        MOCK_TYPE_ENUM_DATA_GENERATOR_ENUM_MAP.put(MockTypeEnum.DICT, new DictDataGenerator());
    }
    
    /**
     * 获取实例
     *
     * @param mockTypeEnum 模拟类型枚举
     * @return 生成器
     */
    public static DataGenerator getGenerator(MockTypeEnum mockTypeEnum) {
        mockTypeEnum = Optional.ofNullable(mockTypeEnum).orElse(MockTypeEnum.NONE);
        return MOCK_TYPE_ENUM_DATA_GENERATOR_ENUM_MAP.get(mockTypeEnum);
    }
}
