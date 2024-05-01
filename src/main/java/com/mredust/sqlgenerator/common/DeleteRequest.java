package com.mredust.sqlgenerator.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
public class DeleteRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * id
     */
    private Integer id;
}
