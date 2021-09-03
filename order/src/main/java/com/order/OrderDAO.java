package com.order;

import com.bean.Order;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderDAO {

    private  JdbcTemplate jdbcTemplate;

    public Order insert(Order order){
        Object args[] ={order.order_no,order.userId,order.commodityCode,order.count,order.money};
        jdbcTemplate.update("insert into order_tbl (id,user_id,commodity_code,counts,money) " +
                "values (?,?,?,?,?)",args);
        //throw new RuntimeException("order failure");
        System.out.println("订单成功！！！");
        return order;
    }

   public OrderDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
   }
}
