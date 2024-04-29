package com.mredust.sqlgenerator.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/Mredust">Mredust</a>
 * @date: 2024/4/26 18:27
 * @version: 1.0
 */
@Data
public class DeleteRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
}
