package com.atguigu;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class testConnectRedis {



    /*
    * 要想连接， 需要关闭linux 的防火墙
    * 修改 redis.conf 文件
    *
    * */

    @Test
    public void testRedisConnection() {
        Jedis jedis = new Jedis("47.88.59.226",6379);
        String ping = jedis.ping();  //
        System.out.println("ping = "+ping);  // 假如连接成功之后， 会返回一个 pong
    }




    /*
    * 用 java 代码测试一下  redis 的五大类型。
    *
    *
    * */
}
