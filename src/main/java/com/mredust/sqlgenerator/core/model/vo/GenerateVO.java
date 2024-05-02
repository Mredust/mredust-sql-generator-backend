package com.mredust.sqlgenerator.core.model.vo;

import com.mredust.sqlgenerator.core.schema.TableSchema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 生成的返回值
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
public class GenerateVO implements Serializable {
    
    /**
     * 表结构
     */
    private TableSchema tableSchema;
    
    /**
     * 建表 SQL
     */
    private String createSql;
    
    /**
     * 模拟数据
     */
    private List<Map<String, Object>> dataList;
    
    /**
     * 插入数据 SQL
     */
    private String insertSql;
    
    /**
     * 数据 json
     */
    private String dataJson;
    
    /**
     * js 模型代码
     */
    private String javascriptTypeCode;
    
    private static final long serialVersionUID = 1L;
}
