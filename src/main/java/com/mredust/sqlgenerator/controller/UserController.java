package com.mredust.sqlgenerator.controller;

import com.mredust.sqlgenerator.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="https://github.com/Mredust">Mredust</a>
 * @date: 2024/4/29 22:46
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    
    
}
