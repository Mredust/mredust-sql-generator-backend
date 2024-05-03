package com.mredust.sqlgenerator.exception;


import com.mredust.sqlgenerator.common.BaseResponse;
import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    public BaseResponse baseException(BusinessException e) {
        log.error("baseException：{}", e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }
    
    @ExceptionHandler(SchemaException.class)
    public BaseResponse baseException(SchemaException e) {
        log.error("baseException：{}", e.getMessage());
        return Result.fail(e.getMessage());
    }
    
    
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeException(RuntimeException e) {
        log.error("runtimeException：{}", e.getMessage());
        return Result.fail(ResponseCode.FAIL, e.getMessage());
    }
    
}

