package com.atguigu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class testConnectRedis {

    Jedis jedis = new Jedis("47.88.59.226",6666);

    /*
    * 要想连接， 需要关闭linux 的防火墙
    * 修改 redis.conf 文件
    *
    * */
    @Before
    public void before() {
        jedis.auth("ZM03uq-64E}j-.:nj0+,>ujzvXqiB1p1FV8a}ryJv+-azR],-}6bi}o*5,#1YiHq");
    }

    @After
    public void after() {
        jedis.close();
    }

    @Test
    public void testRedisConnection() {
        String ping = jedis.ping();  //
        System.out.println("ping = "+ping);  // 假如连接成功之后， 会返回一个 pong
    }




    /*
    * 用 java 代码测试一下  redis 的五大类型。
    *
    *
    * */

    @Test
    public void testRedis() {
        Set<String> keys = jedis.keys("*");
        System.out.println("keys = " + keys);

        Boolean k1 = jedis.exists("k1");
        System.out.println("k1 exists = " + k1);

        String k2 = jedis.type("k2");
        System.out.println("k2 type = " + k2);

        // list
        List<String> k3 = jedis.lrange("k3", 0, -1);
        for(String str : k3) {
            System.out.println(str);
        }

        // set
        Set<String> k4 = jedis.smembers("k4");
        for (String str : k4) {
            System.out.print(" " + str);
        }
        System.out.println(k4);

        //hash
        Set<String> k5_keys = jedis.hkeys("k5");
        List<String> k5_values = jedis.hvals("k5");
        System.out.println(k5_keys);
        for (String str : k5_keys) {
            System.out.print(" " + str);
        }
        System.out.println(k5_values);

        // zset
        Set<String> k6 = jedis.zrange("k6", 0, -1);
        for(String str : k6) {
            System.out.print(" " + str );
        }
    }
}
