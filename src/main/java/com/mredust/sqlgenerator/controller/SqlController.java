package com.mredust.sqlgenerator.controller;

import com.mredust.sqlgenerator.common.BaseResponse;
import com.mredust.sqlgenerator.common.Result;
import com.mredust.sqlgenerator.core.GeneratorFacade;
import com.mredust.sqlgenerator.core.model.vo.GenerateVO;
import com.mredust.sqlgenerator.core.schema.TableSchema;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SQL 相关接口
 * @author <a href="https://github.com/Mredust">Mredust</a>
 * @date: 2024/4/30 8:53
 * @version: 1.0
 */
@RestController
@RequestMapping("/sql")
public class SqlController {
    
    @PostMapping("/build")
    public BaseResponse<GenerateVO> generateBySchema(@RequestBody TableSchema tableSchema) {
        return Result.success(GeneratorFacade.generateData(tableSchema));
    }
}
