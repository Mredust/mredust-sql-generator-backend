package com.mredust.sqlgenerator.model.dto.sql;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
public class GenerateSchemaRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * SQL
     */
    private String sql;
}
