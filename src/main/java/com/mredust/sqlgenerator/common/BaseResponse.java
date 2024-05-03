package com.mredust.sqlgenerator.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int code;
    private T data;
    private String msg;
    
    public BaseResponse(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    
    public BaseResponse(ResponseCode responseCode) {
        this(responseCode.getCode(), null, responseCode.getMessage());
    }
    
    public BaseResponse(ResponseCode responseCode, T data) {
        this(responseCode.getCode(), data, responseCode.getMessage());
    }
    
    public BaseResponse(ResponseCode responseCode, String msg) {
        this(responseCode.getCode(), null, msg);
    }
    
    
}
