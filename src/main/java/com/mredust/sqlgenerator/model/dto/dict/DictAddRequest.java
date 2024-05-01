package com.mredust.sqlgenerator.model.dto.dict;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
public class DictAddRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 词库名称
     */
    private String name;
    
    /**
     * 词库内容
     */
    private String content;
}
