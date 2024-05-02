package com.mredust.sqlgenerator.core.builder.generator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.core.schema.TableSchema.Field;
import com.mredust.sqlgenerator.exception.BusinessException;
import com.mredust.sqlgenerator.model.entity.Dict;
import com.mredust.sqlgenerator.service.DictService;
import com.mredust.sqlgenerator.utils.SpringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型：词库
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
public class DictDataGenerator implements DataGenerator {
    
    private static final DictService dictService = SpringUtils.getBean(DictService.class);
    
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
        long id = Long.parseLong(mockParams);
        Dict dict = dictService.getById(id);
        if (dict == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND, "词库不存在");
        }
        List<String> dictList = new Gson().fromJson(dict.getContent(), new TypeToken<List<String>>() {
        }.getType());
        ArrayList<String> list = new ArrayList<>(mockNum);
        for (int i = 0; i < mockNum; i++) {
            String data = dictList.get(RandomUtils.nextInt(0, dictList.size()));
            list.add(data);
        }
        return list;
    }
}
