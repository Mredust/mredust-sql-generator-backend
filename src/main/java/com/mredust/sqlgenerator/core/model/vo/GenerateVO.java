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
 * @date: 2024/4/30 9:37
 * @version: 1.0
 */
@Data
public class GenerateVO implements Serializable {
    
    private TableSchema tableSchema;
    
    private String createSql;
    
    private List<Map<String, Object>> dataList;
    
    private String insertSql;
    
    private String dataJson;
    
    private String javaEntityCode;
    
    private String javaObjectCode;
    
    private String typescriptTypeCode;
    
    private static final long serialVersionUID = 1L;
}
