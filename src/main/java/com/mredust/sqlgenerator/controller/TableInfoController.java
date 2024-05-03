package com.mredust.sqlgenerator.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mredust.sqlgenerator.common.BaseResponse;
import com.mredust.sqlgenerator.common.DeleteRequest;
import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.common.Result;
import com.mredust.sqlgenerator.exception.BusinessException;
import com.mredust.sqlgenerator.model.dto.table.TableInfoAddRequest;
import com.mredust.sqlgenerator.model.dto.table.TableInfoQueryRequest;
import com.mredust.sqlgenerator.model.dto.table.TableInfoUpdateRequest;
import com.mredust.sqlgenerator.model.entity.TableInfo;
import com.mredust.sqlgenerator.service.TableInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 表 相关接口
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@RestController
@RequestMapping("/table-info")
public class TableInfoController {
    
    @Resource
    private TableInfoService tableInfoService;
    
    /**
     * 添加表
     *
     * @param tableInfoAddRequest 添加请求
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addTableInfo(@RequestBody TableInfoAddRequest tableInfoAddRequest) {
        if (tableInfoAddRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        TableInfo tableInfo = new TableInfo();
        BeanUtils.copyProperties(tableInfoAddRequest, tableInfo);
        tableInfoService.tableInfoContentHandle(tableInfo);
        boolean saveResult = tableInfoService.save(tableInfo);
        if (!saveResult) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR);
        }
        return Result.success(tableInfo.getId());
    }
    
    /**
     * 删除表
     *
     * @param deleteRequest 删除请求
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResponse<Boolean> deleteTableInfo(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        Integer id = deleteRequest.getId();
        TableInfo tableInfo = tableInfoService.getById(id);
        if (tableInfo == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        boolean removeResult = tableInfoService.removeById(id);
        if (!removeResult) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR);
        }
        return Result.success("删除成功");
    }
    
    /**
     * 更新表
     *
     * @param tableInfoUpdateRequest 更新请求
     * @return
     */
    @PutMapping("/update")
    public BaseResponse<Boolean> updateTableInfo(@RequestBody TableInfoUpdateRequest tableInfoUpdateRequest) {
        if (tableInfoUpdateRequest == null || tableInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        if (tableInfoService.getById(tableInfoUpdateRequest.getId()) == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        TableInfo tableInfo = new TableInfo();
        BeanUtils.copyProperties(tableInfoUpdateRequest, tableInfo);
        tableInfoService.tableInfoContentHandle(tableInfo);
        boolean updateResult = tableInfoService.updateById(tableInfo);
        if (!updateResult) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR);
        }
        return Result.success("更新成功");
    }
    
    /**
     * 获取表详情
     *
     * @param id 表id
     * @return
     */
    @GetMapping
    public BaseResponse<TableInfo> getTableInfoById(@RequestParam("id") Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        TableInfo tableInfo = tableInfoService.getById(id);
        if (tableInfo == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        return Result.success(tableInfo);
    }
    
    /**
     * 获取表分页列表
     *
     * @param tableInfoQueryRequest 查询请求
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<Page<TableInfo>> getTableInfoListByPage(TableInfoQueryRequest tableInfoQueryRequest) {
        if (tableInfoQueryRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        Page<TableInfo> pageList = tableInfoService.getTableInfoListByPage(tableInfoQueryRequest);
        return Result.success(pageList);
    }
}
