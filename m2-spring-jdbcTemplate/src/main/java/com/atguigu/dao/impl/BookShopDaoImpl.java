package com.atguigu.dao.impl;

import com.atguigu.dao.BookShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /*根据 isbn 来查询价格*/
    @Override
    public Integer findBookPriceByIsbn(Integer isbn) {
        String sql = "select price from tb2_book where isbn=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
    }

    @Override
    public void updateBookStock(Integer isbn) {
        // 修改库存
        String sql = "update tb2_book_stock set stock=stock-1 where isbn=?";
        jdbcTemplate.update(sql, isbn);

        // 验证库存
        String sql2 = "select stock from tb2_book_stock where isbn=?";
        Integer newStock = jdbcTemplate.queryForObject(sql2,Integer.class,isbn);
        if(newStock <= 0) {
            throw new RuntimeException("stock is not enough");
        }
    }

    @Override
    public void updateUserAccount(String username, Integer price) {
        // 检查余额是否充足
        String sql = "select balance from tb2_account where username=?";
        Integer balance = jdbcTemplate.queryForObject(sql, Integer.class, username);

        if(balance < price) {
            throw new RuntimeException("balance is not enough");
        }

        // 修改用户的余额
        String sql1 = "update tb2_account set balance=balance-? where username=?";
        jdbcTemplate.update(sql1, price,username);
    }
}
