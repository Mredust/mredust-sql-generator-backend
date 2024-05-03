package com.mredust.sqlgenerator.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
public class PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当前页号
     */
    private long pageNum = 1;
    
    /**
     * 页面大小
     */
    private long pageSize = 10;
}
