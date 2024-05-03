package com.mredust.sqlgenerator.mapper;

import com.mredust.sqlgenerator.model.entity.TableInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author <a href="https://github.com/Mredust">Mredust</a>
* @description 针对表【table_info(表信息表)】的数据库操作Mapper
* @Entity com.mredust.sqlgenerator.model.entity.TableInfo
*/
@Mapper
public interface TableInfoMapper extends BaseMapper<TableInfo> {

}




