package com.mredust.sqlgenerator.controller;

import com.mredust.sqlgenerator.common.BaseResponse;
import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.common.Result;
import com.mredust.sqlgenerator.core.GeneratorFacade;
import com.mredust.sqlgenerator.core.builder.TableSchemaBuilder;
import com.mredust.sqlgenerator.core.model.vo.GenerateVO;
import com.mredust.sqlgenerator.core.schema.TableSchema;
import com.mredust.sqlgenerator.exception.BusinessException;
import com.mredust.sqlgenerator.model.dto.sql.GenerateSchemaRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SQL 相关接口
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 * @date: 2024/4/30 8:53
 * @version: 1.0
 */
@RestController
@RequestMapping("/sql")
public class SqlController {
    
    /**
     * 根据 schema 获取 sql
     *
     * @param tableSchema 表结构
     * @return 生成结果
     */
    @PostMapping("/build")
    public BaseResponse<GenerateVO> generateSqlBySchema(@RequestBody TableSchema tableSchema) {
        return Result.success(GeneratorFacade.generateData(tableSchema));
    }
    
    /**
     * 根据 sql 获取 schema
     *
     * @param generateSchemaRequest
     * @return
     */
    @PostMapping("/analyze")
    public BaseResponse<TableSchema> getSchemaBySql(@RequestBody GenerateSchemaRequest generateSchemaRequest) {
        if (generateSchemaRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        return Result.success(TableSchemaBuilder.buildTableSchema(generateSchemaRequest.getSql()));
    }
}
