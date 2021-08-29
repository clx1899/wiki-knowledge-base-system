package com.clx.wiki.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clx
 * @create 2021/8/29 - 14:44
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }


}
