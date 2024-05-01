package com.mredust.sqlgenerator.model.dto.table;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
public class TableInfoAddRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 表名称
     */
    private String name;
    
    /**
     * 表内容
     */
    private String content;
}
