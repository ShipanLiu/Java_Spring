package com.atguigu.service.impl;

import com.atguigu.service.BookShopService;
import com.atguigu.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cashierService")
public class CashierServiceImpl implements CashierService {

    @Autowired
    @Qualifier("bookShopService")
    private BookShopService bookShopService;



    @Override
    @Transactional
    public void checkOut(String username, List<Integer> isbns) {
        for(Integer isbn : isbns) {
            bookShopService.purchase(username, isbn);
        }
    }
}
