package com.mredust.sqlgenerator.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mredust.sqlgenerator.model.dto.table.TableInfoQueryRequest;
import com.mredust.sqlgenerator.model.entity.TableInfo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
* @author <a href="https://github.com/Mredust">Mredust</a>
* @description 针对表【table_info(表信息表)】的数据库操作Service
*/
public interface TableInfoService extends IService<TableInfo> {
    
    /**
     * 表信息内容处理
     * @param tableInfo
     */
    void tableInfoContentHandle(TableInfo tableInfo);
    
    /**
     * 分页查询表信息列表
     * @param tableInfoQueryRequest
     * @return
     */
    Page<TableInfo> getTableInfoListByPage(TableInfoQueryRequest tableInfoQueryRequest);
}
