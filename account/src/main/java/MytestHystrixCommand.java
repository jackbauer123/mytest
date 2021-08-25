
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.redisson.api.RLock;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

public class MytestHystrixCommand /*extends HystrixCommand<CatchExceptionInfo>*/ {

    JdbcTemplate jdbcTemplate;
    long order_no;
    String userId;
    int money;

    /*public MytestHystrixCommand(long order_no,String userId,int money,JdbcTemplate jdbcTemplate) {
        //super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerForceClosed(true)));

        this.money=money;
        this.order_no=order_no;
        this.userId=userId;
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    protected CatchExceptionInfo run() {
        // a real example would do work like a network call here

        System.out.println("扣款开始！！！");
        Object args[]={money,userId};
        jdbcTemplate.update("update account_tbl set money=money - ? where user_id=?",args);
        int i = RandomUtils.nextInt(10);
        throw new RuntimeException("测试异常！！！");
        //return new CatchExceptionInfo();
    }

    @Override
    protected CatchExceptionInfo getFallback() {
        //System.out.println("打印异常开始。。。。。。。。。。。。。。。");
        //System.out.println(this.getFailedExecutionException());
        //System.out.println(this.getExecutionException());
        //System.out.println("打印异常结束。。。。。。。。。。。。。。。");
        return new CatchExceptionInfo(this.getExecutionException(),this.getFallbackMethodName());

    }*/


}
