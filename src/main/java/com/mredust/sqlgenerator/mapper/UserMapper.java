package com.mredust.sqlgenerator.mapper;

import com.mredust.sqlgenerator.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Mredust
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2024-04-29 22:43:58
* @Entity generator.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




