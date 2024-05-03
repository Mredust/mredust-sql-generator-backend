package com.mredust.sqlgenerator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@RestController
@RequestMapping("/test")
public class TestController {
    
    @GetMapping
    public String test() {
        return "hello world";
    }
    
    @GetMapping("/test2")
    public String test2(long id) {
        return "result = " + id;
    }
}
