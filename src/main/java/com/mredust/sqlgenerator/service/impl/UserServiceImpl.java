package com.mredust.sqlgenerator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
<<<<<<< HEAD
import com.mredust.sqlgenerator.mapper.UserMapper;
import com.mredust.sqlgenerator.model.entity.User;
import com.mredust.sqlgenerator.service.UserService;
=======
import com.mredust.sqlgenerator.model.entity.User;
import com.mredust.sqlgenerator.service.UserService;
import com.mredust.sqlgenerator.mapper.UserMapper;
>>>>>>> 8e5adab5d174881345a618e37c82e88ab21a513f
import org.springframework.stereotype.Service;

/**
 * @author Mredust
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-04-29 22:43:58
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}




