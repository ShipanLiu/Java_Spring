package com.atguigu;

import com.atguigu.dao.BookShopDao;
import com.atguigu.dao.impl.BookShopDaoImpl;
import com.atguigu.service.BookShopService;
import com.atguigu.service.CashierService;
import com.atguigu.service.impl.BookShopServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//@ContextConfiguration(locations = "classpath:applicationContext_transactionManager.xml")
//@RunWith(SpringJUnit4ClassRunner.class)

// 重新  junit5 , 方式1
//@ContextConfiguration(locations = "classpath:applicationContext_transactionManager.xml")
//@ExtendWith(SpringExtension.class)

// junit5 方式2
@SpringJUnitConfig(locations = "classpath:applicationContext_transactionManager.xml")
public class Test02_Transaction {

    @Autowired
    @Qualifier("bookShopService")
    private BookShopService bookShopService;

    @Autowired
    @Qualifier("bookShopDao")
    private BookShopDao bookShopDao;

    @Autowired
    @Qualifier("cashierService")
    private CashierService cashierService;


    /*  问题： 当余额不足的时候， 库存不因该更改*/

    @Test
    public void testTransaction() {
        System.out.println(bookShopDao.findBookPriceByIsbn(1001));

//        bookShopService.purchase("chenglong",1002);
    }




    @Test // chenglong  一次性 买连本书 1001， 1002
    public void testCashier() {
        List<Integer> isbns = new ArrayList<>();
        isbns.add(1001);
        isbns.add(1002);

        cashierService.checkOut("chenglong", isbns);

    }
}
