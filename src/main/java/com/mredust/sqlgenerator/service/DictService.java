package com.mredust.sqlgenerator.service;

import com.mredust.sqlgenerator.model.dto.dict.DictQueryRequest;
import com.mredust.sqlgenerator.model.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Mredust
 * @description 针对表【dict(词库表)】的数据库操作Service
 * @createDate 2024-05-01 21:32:03
 */
public interface DictService extends IService<Dict> {
    /**
     * 处理词库数据
     *
     * @param dict 词库数据
     */
    void dictContentHandle(Dict dict);
    
    /**
     * 获取分页词典列表
     *
     * @param dictQueryRequest
     * @return
     */
    List<Dict> getDictListByPage(DictQueryRequest dictQueryRequest);
    
    
}
