package com.clx.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clx
 * @create 2021/8/29 - 14:44
 */
@RestController
public class TestController {

    /*
    GET,POST,PUT,DELETE

    只是简单的使用@RequestMapping注解，表示这个接口支持所有的请求方式
     */
    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }


}
