package com.mredust.sqlgenerator.core.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

/**
 * json 生成器
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
public class JsonBuilder {
    private JsonBuilder() {
    }
    
    /**
     * 构造数据 json
     *
     * @param dataList 数据列表
     * @return 生成的 json 数组字符串
     */
    public static String buildJson(List<Map<String, Object>> dataList) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(dataList);
    }
}
