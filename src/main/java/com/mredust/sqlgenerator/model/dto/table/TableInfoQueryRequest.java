package com.mredust.sqlgenerator.model.dto.table;

import com.mredust.sqlgenerator.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TableInfoQueryRequest extends PageRequest implements Serializable {
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
