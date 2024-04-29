package com.mredust.sqlgenerator.common;

/**
 * @author: Mredust
 * @date: 2024/4/13 17:37
 * @version: 1.0
 */
public class Result {
    private Result() {
    }
    
    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(ResponseCode.SUCCESS);
    }
    
    public static <T> BaseResponse<T> success(String msg) {
        return new BaseResponse<>(ResponseCode.SUCCESS, msg);
    }
    
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(ResponseCode.SUCCESS, data);
    }
    
    public static <T> BaseResponse<T> success(ResponseCode responseCode) {
        return new BaseResponse<>(responseCode);
    }
    
    public static <T> BaseResponse<T> success(ResponseCode responseCode, T data) {
        return new BaseResponse<>(responseCode, data);
    }
    
    public static <T> BaseResponse<T> fail() {
        return new BaseResponse<>(ResponseCode.FAIL);
    }
    
    public static <T> BaseResponse<T> fail(String msg) {
        return new BaseResponse<>(ResponseCode.FAIL, msg);
    }
    
    public static <T> BaseResponse<T> fail(Integer code, String msg) {
        return new BaseResponse<>(code, null, msg);
    }
    
    public static <T> BaseResponse<T> fail(ResponseCode responseCode) {
        return new BaseResponse<>(responseCode);
    }
    
    public static <T> BaseResponse<T> fail(ResponseCode responseCode, String msg) {
        return new BaseResponse<>(responseCode, msg);
    }
    
}
