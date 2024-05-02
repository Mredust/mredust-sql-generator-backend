package com.mredust.sqlgenerator.core.builder.generator;

import com.mredust.sqlgenerator.core.model.enums.MockDataTypeEnum;
import com.mredust.sqlgenerator.core.schema.TableSchema;
import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * 类型：随机
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
public class RandomDataGenerator implements DataGenerator {
    private static final Faker ZH_FAKER = new Faker(new Locale("zh-CN"));
    public static final Faker EN_FAKER = new Faker(new Locale("en-US"));
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
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
        List<String> list = new ArrayList<>(mockNum);
        for (int i = 0; i < mockNum; i++) {
            MockDataTypeEnum randomTypeEnum = Optional.ofNullable(
                            MockDataTypeEnum.getEnumByValue(mockParams))
                    .orElse(MockDataTypeEnum.STRING);
            String randomString = getRandomValue(randomTypeEnum);
            list.add(randomString);
        }
        return list;
    }
    
    private static String getRandomValue(MockDataTypeEnum mockDataTypeEnum) {
        String defaultValue = RandomStringUtils.randomAlphabetic(2, 6);
        if (mockDataTypeEnum == null) {
            return defaultValue;
        }
        switch (mockDataTypeEnum) {
            case NAME:
                return ZH_FAKER.name().name();
            case CITY:
                return ZH_FAKER.address().city();
            case EMAIL:
                return EN_FAKER.internet().emailAddress();
            case URL:
                return EN_FAKER.internet().url();
            case IP:
                return ZH_FAKER.internet().ipV4Address();
            case INTEGER:
                return String.valueOf(ZH_FAKER.number().randomNumber());
            case DECIMAL:
                return String.valueOf(RandomUtils.nextFloat(0, 100000));
            case UNIVERSITY:
                return ZH_FAKER.university().name();
            case DATE:
                return EN_FAKER.date()
                        .between(Timestamp.valueOf("2024-01-01 00:00:00"), Timestamp.valueOf("2025-01-01 00:00:00"))
                        .toLocalDateTime().format(DATE_TIME_FORMATTER);
            case TIMESTAMP:
                return String.valueOf(EN_FAKER.date()
                        .between(Timestamp.valueOf("2024-01-01 00:00:00"), Timestamp.valueOf("2025-01-01 00:00:00"))
                        .getTime());
            case PHONE:
                return ZH_FAKER.phoneNumber().cellPhone();
            default:
                return defaultValue;
        }
    }
}
