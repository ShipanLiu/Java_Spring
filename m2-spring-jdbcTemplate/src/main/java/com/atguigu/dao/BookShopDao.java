package com.atguigu.dao;

public interface BookShopDao {
    public Integer findBookPriceByIsbn(Integer isbn);
    // throw exception if stock < 0
    public void updateBookStock(Integer isbn);
    // if the price is not enough, then throw exception
    public void updateUserAccount(String username, Integer price);
}
