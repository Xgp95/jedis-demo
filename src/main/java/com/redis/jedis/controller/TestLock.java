package com.redis.jedis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: Xugp
 * @date: 2022/6/27 15:23
 * @description:
 */
@Controller
public class TestLock {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/testLock")
    public void testLock(HttpServletResponse httpServletResponse) throws IOException {
        String value = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", value,20, TimeUnit.SECONDS);

//        System.out.println(redisTemplate);
//        System.out.println(lock);
        if (lock) {
            Object num = redisTemplate.opsForValue().get("num");

            if (StringUtils.isEmpty(num)) {
                return;
            }
            int i = Integer.parseInt(num + "");

            redisTemplate.opsForValue().set("num", ++i);

            if (value.equals(redisTemplate.opsForValue().get("lock"))) {
                redisTemplate.delete("lock");
            }
            httpServletResponse.getWriter().write(value + "^^^" + i);
        }
    }
}
