package com.mredust.sqlgenerator.exception;

/**
 * SQL Schema异常
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
public class SchemaException extends RuntimeException {
    public SchemaException() {
    }
    
    public SchemaException(String message) {
        super(message);
    }
    
    public SchemaException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public SchemaException(Throwable cause) {
        super(cause);
    }
    
    public SchemaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
