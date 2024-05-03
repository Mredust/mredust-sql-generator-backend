package com.mredust.sqlgenerator.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.mredust.sqlgenerator.common.BaseResponse;
import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.common.Result;
import com.mredust.sqlgenerator.core.GeneratorFacade;
import com.mredust.sqlgenerator.core.builder.TableSchemaBuilder;
import com.mredust.sqlgenerator.core.model.vo.GenerateVO;
import com.mredust.sqlgenerator.core.schema.TableSchema;
import com.mredust.sqlgenerator.core.schema.TableSchema.Field;
import com.mredust.sqlgenerator.exception.BusinessException;
import com.mredust.sqlgenerator.model.dto.sql.GenerateSchemaRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SQL 相关接口
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
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
     * @param generateSchemaRequest SQL
     * @return
     */
    @PostMapping("/analyze")
    public BaseResponse<TableSchema> getSchemaBySql(@RequestBody GenerateSchemaRequest generateSchemaRequest) {
        if (generateSchemaRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        return Result.success(TableSchemaBuilder.buildTableSchema(generateSchemaRequest.getSql()));
    }
    
    /**
     * 下载 Excel模拟数据
     *
     * @param generateVO
     * @param response
     */
    @PostMapping("/download/excel-data")
    public void downloadExcelData(@RequestBody GenerateVO generateVO, HttpServletResponse response) {
        TableSchema tableSchema = generateVO.getTableSchema();
        String tableName = tableSchema.getTableName();
        String tableComment = tableSchema.getTableComment();
        String excelName = tableName;
        if (StringUtils.isNotBlank(tableComment)) {
            excelName = String.format("%s数据", tableComment);
        }
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String filename = URLEncoder.encode(excelName, "utf-8").replace("\\+", "%20");
            response.setHeader("content-disposition", "attachment;filename*=utf-8''" + filename + ".xlsx");
            response.setHeader("Access-Control-Expose-Headers", "content-disposition");
            // 设置表头
            List<List<String>> headlist = new ArrayList<>();
            for (Field field : tableSchema.getFieldList()) {
                String fieldName = field.getFieldName();
                String comment = field.getComment();
                String headName = fieldName;
                if (StringUtils.isNotBlank(comment)) {
                    headName = comment;
                }
                List<String> head = Collections.singletonList(headName);
                headlist.add(head);
            }
            List<String> fieldnamelist = tableSchema.getFieldList().stream()
                    .map(Field::getFieldName).collect(Collectors.toList());
            // 设置数据
            List<List<Object>> datalist = new ArrayList<>();
            for (Map<String, Object> data : generateVO.getDataList()) {
                List<Object> dataRow = fieldnamelist.stream().map(data::get).collect(Collectors.toList());
                datalist.add(dataRow);
            }
            // 这里需要设置不关闭流
            EasyExcelFactory.write(response.getOutputStream())
                    .autoCloseStream(Boolean.FALSE)
                    .head(headlist)
                    .sheet(tableName + "表")
                    .doWrite(datalist);
        } catch (Exception e) {
            // 重置 response
            response.reset();
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "下载失败");
        }
    }
}
