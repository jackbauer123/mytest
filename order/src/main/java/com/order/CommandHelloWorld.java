package com.order;

public class CommandHelloWorld /*extends HystrixCommand<com.order.CatchExceptionInfo> */{

    /*private  com.service.AccountService accountService ;

    private  com.order.OrderBean orderBean;

    public com.order.CommandHelloWorld() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));

    }

    @Override
    protected com.order.CatchExceptionInfo run() {
        // a real example would do work like a network call here
        accountService.debit(orderBean.order_no,orderBean.userId,orderBean.orderMoney);
        return new com.order.CatchExceptionInfo();
    }

    @Override
    protected com.order.CatchExceptionInfo getFallback() {
        return new com.order.CatchExceptionInfo(this.getExecutionException(),this.getFallbackMethodName());
    }

    public void setOrderBean(com.order.OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public void setAccountService(com.service.AccountService accountService) {
        this.accountService = accountService;
    }*/
}