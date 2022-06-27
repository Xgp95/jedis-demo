package com.redis.jedis.seckill;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Random;

/**
 * @author: Xugp
 * @date: 2022/6/21 17:07
 * @description:
 */
@Controller
public class SecKillController {


    String userid = new Random().nextInt(50000) + "";

    @RequestMapping(value = "/doseckill",method = RequestMethod.POST)
    public String doseckill(@RequestParam("prodid") String prodid) {
//        String prodid =request.getParameter("prodid");
        System.out.println("*******");
        boolean isSuccess = false;
        try {
            isSuccess = SecKill_redis.doSecKill(userid, prodid);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        boolean isSuccess= SecKill_redisByScript.doSecKill(userid,prodid);
//        response.getWriter().print(isSuccess);
        return isSuccess + "";
    }

}
