package com.logic;

import com.baidu.fsg.uid.UidGenerator;
import com.service.OrderService;
import com.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;

public class BusinessServiceImpl implements BusinessService {

    private StorageService storageService;

    private OrderService orderService;

    private UidGenerator uidGenerator;

    public BusinessServiceImpl(){}


    /**
     * purchase
     */
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-demo-tx")
    public void purchase(long order_no,String userId, String commodityCode, int orderCount) {
        orderService.create(order_no,userId, commodityCode, orderCount);
        System.out.println("deduct....."+order_no);
        storageService.deduct(order_no,commodityCode, orderCount);

    }

    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setUidGenerator(UidGenerator uidGenerator) {
        this.uidGenerator = uidGenerator;
    }
}