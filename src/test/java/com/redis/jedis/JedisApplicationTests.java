package com.redis.jedis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@SpringBootTest
class JedisApplicationTests {

    Jedis jedis = new Jedis("172.16.1.110", 6379);

    @Test
    void contextLoads() {
    }

    @Test
//    @Disabled
    void jedisTest() {

        String pong = jedis.ping();
        System.out.println(pong);
    }

    @Test
    void keyTest() {
        jedis.mset("k1","v1","k2","v2","k3","1");
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            jedis.sadd("keys", key);
//            System.out.println(key);
        }
        Set<String> set = jedis.smembers("keys");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("@@@@@@@");
        jedis.incrBy("k3",10);
        System.out.println(jedis.get("k3"));
        System.out.println("~~~~~~~~~~~~~~");
        jedis.rpush("k4:list", "a","b","c","d");
        List<String> lrange = jedis.lrange("k4:list", 0, -1);
        for (String s : lrange) {
            System.out.print(s + " ");
        }
        jedis.unlink("k4:list");
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~");
        jedis.sadd("k5:set", "a","b","c","d","a","aa");
        Set<String> smembers = jedis.smembers("k5:set");
        for (String s : smembers) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~");
        jedis.hset("user:101", "id", "001");
        jedis.hset("user:101", "name", "zhangsan");
        jedis.hset("user:101", "age", "18");
        Map<String, String> map = new HashMap<>();
        map.put("id", "002");
        map.put("name", "lisi");
        map.put("age", "20");
        jedis.hmset("user:102", map);
        List<String> hvals101 = jedis.hvals("user:101");
        for (String s : hvals101) {
            System.out.print(s + " ");
        }
        System.out.println("\n!!!!");
        List<String> hvals102 = jedis.hvals("user:102");
        for (String s : hvals102) {
            System.out.print(s + " ");
        }
        System.out.println("\n!!!!!!!!!");
        jedis.zadd("k6:zset", 10, "c");
        jedis.zadd("k6:zset", 10, "c");
        jedis.zadd("k6:zset", 10, "c++");
        jedis.zadd("k6:zset", 100, "php");
        jedis.zadd("k6:zset", 50, "java");
        Set<Tuple> tuples = jedis.zrangeByScoreWithScores("k6:zset", 0, 100);
        for (Tuple tuple : tuples) {
            System.out.println(tuple);
        }
        jedis.flushDB();
        final Set<String> keys1 = jedis.keys("*");
        System.out.println(keys1 + "~~~");
    }

}
