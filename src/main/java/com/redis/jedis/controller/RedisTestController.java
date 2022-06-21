package com.redis.jedis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Xugp
 * @date: 2022/6/17 15:43
 * @description:
 */
@Controller
public class RedisTestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/redisTest")
    @ResponseBody
    public String redisTest() {
        redisTemplate.opsForValue().set("name", "jack");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(redisTemplate);
        return name;
    }

}
