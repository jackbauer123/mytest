package com.bean;

import java.io.Serializable;

public class Order implements Serializable {

    public String userId ;
    public String commodityCode ;
    public int count;
    public int money;
    public long order_no;
}
