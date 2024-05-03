package com.mredust.sqlgenerator.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.google.gson.Gson;
import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.exception.BusinessException;
import com.mredust.sqlgenerator.model.dto.dict.DictQueryRequest;
import com.mredust.sqlgenerator.model.entity.Dict;
import com.mredust.sqlgenerator.service.DictService;
import com.mredust.sqlgenerator.mapper.DictMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mredust.sqlgenerator.constant.DictConstant.DICT_CONTENT_MAX_LENGTH;
import static com.mredust.sqlgenerator.constant.DictConstant.DICT_NAME_MAX_LENGTH;

/**
 * @author Mredust
 * @description 针对表【dict(词库表)】的数据库操作Service实现
 * @createDate 2024-05-01 21:32:03
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    
    @Resource
    private DictMapper dictMapper;
    
    /**
     * 处理词库数据
     *
     * @param dict 词库数据
     */
    @Override
    public void dictContentHandle(Dict dict) {
        if (dict == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        String name = dict.getName();
        String content = dict.getContent();
        if (StringUtils.isAnyBlank(name, content)) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        if (StringUtils.isNotBlank(name) && name.length() > DICT_NAME_MAX_LENGTH) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "词库名称过长");
        }
        if (StringUtils.isNotBlank(content) && content.length() > DICT_CONTENT_MAX_LENGTH) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "词库内容过长");
        }
        // 对content处理
        try {
            String[] words = content.split("[,，]");
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].trim();
            }
            List<String> wordList = Arrays.stream(words)
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());
            dict.setContent(new Gson().toJson(wordList));
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "内容格式错误");
        }
    }
    
    /**
     * 获取分页词典列表
     *
     * @param dictQueryRequest
     * @return
     */
    @Override
    public Page<Dict> getDictListByPage(DictQueryRequest dictQueryRequest) {
        long pageNum = dictQueryRequest.getPageNum();
        long pageSize = dictQueryRequest.getPageSize();
        String name = dictQueryRequest.getName();
        String content = dictQueryRequest.getContent();
        Page<Dict> page = new Page<>(pageNum, pageSize);
        return Db.lambdaQuery(Dict.class)
                .like(StringUtils.isNotEmpty(name), Dict::getName, name)
                .like(StringUtils.isNotEmpty(content), Dict::getContent, content)
                .orderBy(true, false, Dict::getCreateTime)
                .page(page);
    }
}




