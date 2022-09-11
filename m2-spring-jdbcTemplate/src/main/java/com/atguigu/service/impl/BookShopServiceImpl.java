package com.atguigu.service.impl;
/*
* 这里就是 业务，   指定管理的 业务
*
*
* */

import com.atguigu.dao.BookShopDao;
import com.atguigu.service.BookShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {


    /*首先你要  定义一个 BookShopDao*/
    @Autowired
    @Qualifier("bookShopDao")  // 就是 在 autowired 的基础上指定一下 id
    private BookShopDao bookShopDao;


    /*
    * 买书 -》 查询book价格  -》 修改库存  -》 修改余额
    *
    * */
    //@Transactional(propagation = Propagation.REQUIRED) // 假如有事务已经存在了，purchase这个方法就 用 这个事务。（多次 purchase 要么成功， 要么失败）
                                                        // REQUIRED 是默认的
    @Transactional(propagation = Propagation.REQUIRES_NEW,
        isolation = Isolation.REPEATABLE_READ,
        timeout = -1,  // -1 表示没有设置强制回滚时间
        readOnly = false)  // 每次调用 purchase 方法的时候， 都 开启一个新的事务。（多个 purchase 之间的 成功失败没有关系）
    @Override
    public void purchase(String username, Integer isbn) {
        // find the price
        Integer bookPrice = bookShopDao.findBookPriceByIsbn(isbn);
        // change the stock
        bookShopDao.updateBookStock(isbn);
        // change the balance of the user
        bookShopDao.updateUserAccount(username, bookPrice);

        System.out.println(username + " bought " + isbn + " if this purchase is successful or not, it depends on REQUIRED or REQUIRED_NEW");
    }
}
