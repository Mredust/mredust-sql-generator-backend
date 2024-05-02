package com.mredust.sqlgenerator.model.dto.dict;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
public class DictUpdateRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;
    /**
     * 词库名称
     */
    private String name;
    
    /**
     * 词库内容
     */
    private String content;
}
