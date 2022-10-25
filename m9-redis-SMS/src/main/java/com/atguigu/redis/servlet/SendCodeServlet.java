package com.atguigu.redis.servlet;

import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

//处理发送验证码的Servlet
@WebServlet("/SendCodeServlet")
public class SendCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the phone number
        String phone_no = request.getParameter("phone_no");
        // check if the nr is empty
        if(phone_no == null || phone_no.equals("")) {
            return;
        }

        String codeKey = phone_no + ":code";

        // produce 6 nr verification code
        String code = myGetCode(6);
        System.out.println(phone_no + ":code " + code);

        // 因为验证码的 存货时间是 2 分钟， 所以放进redis 里面
        // 拼接 redis里面保存验证码的 key， 必须保证唯一性
        // 你刚刚上了 电商网站， 一下子就会出来好多商品库存啥的， 就是 redis 在后面的作用

        Jedis jedis = new Jedis("47.88.59.226",6666);
        jedis.auth("ZM03uq-64E}j-.:nj0+,>ujzvXqiB1p1FV8a}ryJv+-azR],-}6bi}o*5,#1YiHq");
        // save the code to redis and set live time to 120s
        jedis.setex(codeKey,120, code);
        //前台得到 true之后， 就会设置120s
        response.getWriter().write("true");

        jedis.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    // 随机生成验证码的方法
    private String myGetCode(int len) {
        String code = "";
        for (int i = 0; i < len; i++) {
            //生成0-10之间的数
            int rand = new Random().nextInt(10);
            code += rand;
        }
        return code;
    }

    //获取当天剩余秒数的方法
    private long getTheLeftSeconds(){
        //获取现在的时间
        LocalTime now = LocalTime.now();
        //获取当日23点59分59秒的时间
        LocalTime end = LocalTime.of(23, 59, 59);
        //获取end与now相差的秒数
        long millis = Duration.between(now, end).toMillis()/1000;
        return millis;
    }
}
