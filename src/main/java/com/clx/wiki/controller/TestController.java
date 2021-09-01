package com.clx.wiki.controller;

import com.clx.wiki.domain.Test;
import com.clx.wiki.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author clx
 * @create 2021/8/29 - 14:44
 */
@RestController
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Value("${test.hello:Test}")
    private String testHello;

    @Resource
    private TestService testService;

    @Resource
    private RedisTemplate redisTemplate;

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

    @RequestMapping("/redis/set/{key}/{value}")
    public String set(@PathVariable Long key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value, 3600, TimeUnit.SECONDS);
        LOG.info("key: {}, value: {}", key, value);
        return "success";
    }

    @RequestMapping("/redis/get/{key}")
    public Object get(@PathVariable Long key) {
        Object object = redisTemplate.opsForValue().get(key);
        LOG.info("key: {}, value: {}", key, object);
        return object;
    }

}
