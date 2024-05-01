package com.mredust.sqlgenerator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/Mredust">Mredust</a>
 * @date: 2024/4/29 22:46
 * @version: 1.0
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
