package com.mredust.sqlgenerator.controller;

import com.mredust.sqlgenerator.common.BaseResponse;
import com.mredust.sqlgenerator.common.DeleteRequest;
import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.common.Result;
import com.mredust.sqlgenerator.exception.BusinessException;
import com.mredust.sqlgenerator.model.dto.dict.DictAddRequest;
import com.mredust.sqlgenerator.model.dto.dict.DictQueryRequest;
import com.mredust.sqlgenerator.model.dto.dict.DictUpdateRequest;
import com.mredust.sqlgenerator.model.entity.Dict;
import com.mredust.sqlgenerator.service.DictService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 词库 相关接口
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@RestController
@RequestMapping("/dict")
public class DictController {
    
    @Resource
    private DictService dictService;
    
    /**
     * 添加词库
     *
     * @param dictAddRequest 添加请求
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addDict(@RequestBody DictAddRequest dictAddRequest) {
        if (dictAddRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictAddRequest, dict);
        dictService.dictContentHandle(dict);
        boolean saveResult = dictService.save(dict);
        if (!saveResult) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR);
        }
        return Result.success(dict.getId());
    }
    
    /**
     * 删除词库
     *
     * @param deleteRequest 删除请求
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResponse<Boolean> deleteDict(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        Integer id = deleteRequest.getId();
        Dict dict = dictService.getById(id);
        if (dict == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        boolean removeResult = dictService.removeById(id);
        if (!removeResult) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR);
        }
        return Result.success("删除成功");
    }
    
    /**
     * 更新词库
     *
     * @param dictUpdateRequest 更新请求
     * @return
     */
    @PutMapping("/update")
    public BaseResponse<Boolean> updateDict(@RequestBody DictUpdateRequest dictUpdateRequest) {
        if (dictUpdateRequest == null || dictUpdateRequest.getId() <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        Dict dict = dictService.getById(dictUpdateRequest.getId());
        if (dict == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        BeanUtils.copyProperties(dictUpdateRequest, dict);
        dictService.dictContentHandle(dict);
        boolean updateResult = dictService.updateById(dict);
        if (!updateResult) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR);
        }
        return Result.success("更新成功");
    }
    
    /**
     * 获取词库详情
     *
     * @param id 词库id
     * @return
     */
    @GetMapping
    public BaseResponse<Dict> getDictById(@RequestParam("id") Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        Dict dict = dictService.getById(id);
        if (dict == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        return Result.success(dict);
    }
    
    /**
     * 获取词库分页列表
     *
     * @param dictQueryRequest 查询请求
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<Dict>> getDictListByPage(@RequestBody DictQueryRequest dictQueryRequest) {
        if (dictQueryRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        List<Dict> dictList = dictService.getDictListByPage(dictQueryRequest);
        return Result.success(dictList);
    }
}
