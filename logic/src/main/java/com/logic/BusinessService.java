package com.logic;

public interface BusinessService {

    public void purchase(long order_no,String userId, String commodityCode, int orderCount);
}
