package com.mredust.sqlgenerator.exception;

import com.mredust.sqlgenerator.common.ResponseCode;
import lombok.Getter;

/**
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Getter
public class BusinessException extends RuntimeException {
    private final int code;
    private final String msg;
    
    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    
    public BusinessException(String msg) {
        this(ResponseCode.FAIL.getCode(), msg);
    }
    
    public BusinessException(ResponseCode responseCode) {
        this(responseCode.getCode(), responseCode.getMessage());
    }
    
    public BusinessException(ResponseCode responseCode, String msg) {
        this(responseCode.getCode(), msg);
    }
}
