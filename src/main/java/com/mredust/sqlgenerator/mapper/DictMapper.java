package com.mredust.sqlgenerator.mapper;

import com.mredust.sqlgenerator.model.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author <a href="https://github.com/Mredust">Mredust</a>
* @description 针对表【dict(词库表)】的数据库操作Mapper
* @Entity com.mredust.sqlgenerator.model.entity.Dict
*/
@Mapper
public interface DictMapper extends BaseMapper<Dict> {

}




