import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloWorld extends HystrixCommand<CatchExceptionInfo> {

    private  AccountService accountService ;

    private  OrderBean orderBean;

    public CommandHelloWorld() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));

    }

    @Override
    protected CatchExceptionInfo run() {
        // a real example would do work like a network call here
        accountService.debit(orderBean.order_no,orderBean.userId,orderBean.orderMoney);
        return new CatchExceptionInfo();
    }

    @Override
    protected CatchExceptionInfo getFallback() {
        return new CatchExceptionInfo(this.getExecutionException(),this.getFallbackMethodName());
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}