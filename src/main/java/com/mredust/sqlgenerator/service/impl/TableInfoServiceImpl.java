package com.mredust.sqlgenerator.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.google.gson.Gson;
import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.core.GeneratorFacade;
import com.mredust.sqlgenerator.core.schema.TableSchema;
import com.mredust.sqlgenerator.exception.BusinessException;
import com.mredust.sqlgenerator.model.dto.table.TableInfoQueryRequest;
import com.mredust.sqlgenerator.model.entity.TableInfo;
import com.mredust.sqlgenerator.service.TableInfoService;
import com.mredust.sqlgenerator.mapper.TableInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.mredust.sqlgenerator.constant.TableInfoConstant.TABLE_INFO_CONTENT_MAX_LENGTH;
import static com.mredust.sqlgenerator.constant.TableInfoConstant.TABLE_INFO_NAME_MAX_LENGTH;

/**
 * @author Mredust
 * @description 针对表【table_info(表信息表)】的数据库操作Service实现
 * @createDate 2024-05-02 11:16:33
 */
@Slf4j
@Service
public class TableInfoServiceImpl extends ServiceImpl<TableInfoMapper, TableInfo> implements TableInfoService {
    
    @Resource
    private TableInfoMapper tableInfoMapper;
    
    /**
     * 表信息内容处理
     *
     * @param tableInfo
     */
    @Override
    public void tableInfoContentHandle(TableInfo tableInfo) {
        if (tableInfo == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        String content = tableInfo.getContent();
        String name = tableInfo.getName();
        if (StringUtils.isAnyBlank(name, content)) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(name) && name.length() > TABLE_INFO_NAME_MAX_LENGTH) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "表名称过长");
        }
        if (StringUtils.isNotBlank(content) && content.length() > TABLE_INFO_CONTENT_MAX_LENGTH) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "内容过长");
        }
        try {
            TableSchema tableSchema = new Gson().fromJson(content, TableSchema.class);
            GeneratorFacade.validSchema(tableSchema);
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "内容格式错误");
        }
    }
    
    /**
     * 分页查询表信息列表
     *
     * @param tableInfoQueryRequest
     * @return
     */
    @Override
    public List<TableInfo> getTableInfoListByPage(TableInfoQueryRequest tableInfoQueryRequest) {
        long pageNum = tableInfoQueryRequest.getPageNum();
        long pageSize = tableInfoQueryRequest.getPageSize();
        String name = tableInfoQueryRequest.getName();
        String content = tableInfoQueryRequest.getContent();
        Page<TableInfo> page = new Page<>(pageNum, pageSize);
        List<TableInfo> tableInfoList = Db.lambdaQuery(TableInfo.class)
                .like(StringUtils.isNotEmpty(name), TableInfo::getName, name)
                .like(StringUtils.isNotEmpty(content), TableInfo::getContent, content)
                .orderBy(true, false, TableInfo::getCreateTime)
                .page(page).getRecords();
        log.info("getTableInfoListByPage tableInfoList: {}", tableInfoList);
        return tableInfoList;
    }
}




