package com.atguigu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;

/**
 *
 */
public class SecKill_redis {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("47.88.59.226",6666);
		jedis.auth("ZM03uq-64E}j-.:nj0+,>ujzvXqiB1p1FV8a}ryJv+-azR],-}6bi}o*5,#1YiHq");
		System.out.println(jedis.ping());
		jedis.close();
	}

	//秒杀过程
	public static boolean doSecKill(String uid,String prodid) throws IOException {
		// stock key and user key
		String stock_key = "handy:" + prodid + ":stock";
		String user_key = "handy:" + prodid + ":user";

		Jedis jedis = new Jedis("47.88.59.226",6666);
		jedis.auth("ZM03uq-64E}j-.:nj0+,>ujzvXqiB1p1FV8a}ryJv+-azR],-}6bi}o*5,#1YiHq");


		// add watch lock
		jedis.watch(stock_key);


		// get stock
		String stock = jedis.get(stock_key);

		if("0".equals(stock)) {
			System.out.println("gone");
			return false;
		}

		// 开启事务
		Transaction multi = jedis.multi();

		// if the stock is not null

		// stock --
		multi.decr(stock_key);
		// save user
		multi.sadd(user_key, uid);


		// 执行
		List<Object> exec = multi.exec();

		if(exec == null || exec.size() == 0) {
			System.err.println("秒杀失败");
			// 说明系统出错了。
			return false;
		}
		return true;

	}
}
















