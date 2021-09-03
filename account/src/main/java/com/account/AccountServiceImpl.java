package com.account;

import com.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

public class AccountServiceImpl  implements AccountService {

    private JdbcTemplate jdbcTemplate;

    RLock lock=null;
    Jedis jedis=null;
    //{

        //jedis = new Jedis("192.168.43.125", 6379);
       // Config config = new Config();
        //config.useClusterServers() .addNodeAddress("redis://192.168.43.125:6379");
        /*config.useSingleServer().setAddress("redis://192.168.43.125:6379")
                .setConnectionMinimumIdleSize(1000)
                .setConnectionPoolSize(2000);*/


        // Sync and Async API
       // RedissonClient redisson = Redisson.create(config);

        // 4. Get Redis based implementation of java.util.concurrent.locks.Lock
        //lock = redisson.getLock("debit");
    //}


    //@Transactional
    public void debit(long order_no,String userId, int money) {
        boolean res = false;
        //try {
           // res = lock.tryLock(100, 10, TimeUnit.SECONDS);
            //if (res) {
                try {
                    String s = jedis.get(order_no + userId + money);
                    if(StringUtils.isNotEmpty(s)) return ;
                    System.out.println("order_no:"+order_no);
                    //com.account.CatchExceptionInfo execute = new com.account.MytestHystrixCommand(order_no, userId, money, jdbcTemplate).execute();
                    System.out.println("扣款开始！！！");
                    Object args[]={money,userId};
                    jdbcTemplate.update("update account_tbl set money=money - ? where user_id=?",args);
                    jedis.set(order_no + userId + money,"1");
                    //if(Objects.nonNull(execute.getException())) throw new RuntimeException(execute.getException());
                    throw new RuntimeException("test.....");
                    //System.out.println("扣款成功！！！");
                } finally {
                    lock.unlock();
                }
           // }
        //} catch (InterruptedException e) {
         //   e.printStackTrace();
        //}
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
