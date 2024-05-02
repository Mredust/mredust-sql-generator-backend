package com.mredust.sqlgenerator.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mredust.sqlgenerator.model.dto.dict.DictQueryRequest;
import com.mredust.sqlgenerator.model.entity.Dict;
import com.mredust.sqlgenerator.service.DictService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class DictServiceImplTest {
    
    @Resource
    private DictService dictService;
    
    @Test
    void getDictListByPageTest() {
        DictQueryRequest dictQueryRequest = new DictQueryRequest();
        dictQueryRequest.setName("电脑");
        dictQueryRequest.setContent("主键，鼠标");
        
        Page<Dict> page = dictService.getDictListByPage(dictQueryRequest);
        System.out.println(page.getRecords());
        
    }
}
