package com.clx.wiki.controller;

import com.clx.wiki.domain.Test;
import com.clx.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author clx
 * @create 2021/8/29 - 14:44
 */
@RestController
public class TestController {
    @Value("${test.hello:Test}")
    private String testHello;

    @Resource
    private TestService testService;

    /*
    GET,POST,PUT,DELETE

    只是简单的使用@RequestMapping注解，表示这个接口支持所有的请求方式
     */
    @GetMapping("/hello")
    public String hello(){

        return "hello world" + testHello;
    }


    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "hello world, post!" + name;
    }

    @GetMapping("/test/list")
    public List<Test> list(){
        return testService.list();
    }

}
