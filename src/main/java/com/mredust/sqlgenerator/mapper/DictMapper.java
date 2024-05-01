package com.mredust.sqlgenerator.mapper;

import com.mredust.sqlgenerator.model.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Mredust
* @description 针对表【dict(词库表)】的数据库操作Mapper
* @createDate 2024-05-01 21:32:03
* @Entity com.mredust.sqlgenerator.model.entity.Dict
*/
@Mapper
public interface DictMapper extends BaseMapper<Dict> {

}




