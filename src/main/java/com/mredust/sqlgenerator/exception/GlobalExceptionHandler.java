package com.mredust.sqlgenerator.exception;


import com.mredust.sqlgenerator.common.BaseResponse;
import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: Mredust
 * @date: 2024/4/12 18:38
 * @version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> baseException(BusinessException e) {
        log.error("baseException：{}", e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }
    
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeException(RuntimeException e) {
        log.error("runtimeException：{}", e.getMessage());
        return Result.fail(ResponseCode.FAIL, e.getMessage());
    }
    
}

