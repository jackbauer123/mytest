import com.google.common.base.Objects;
import io.seata.core.exception.RmTransactionException;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AccountServiceImpl implements AccountService {

    private JdbcTemplate jdbcTemplate;

    RLock lock=null;
    Jedis jedis=null;
    {

        jedis = new Jedis("192.168.43.125", 6379);
        Config config = new Config();
        //config.useClusterServers() .addNodeAddress("redis://192.168.43.125:6379");
        config.useSingleServer().setAddress("redis://192.168.43.125:6379")
                .setConnectionMinimumIdleSize(1000)
                .setConnectionPoolSize(2000);


        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);

        // 4. Get Redis based implementation of java.util.concurrent.locks.Lock
        lock = redisson.getLock("debit");
    }


    //@Transactional
    public void debit(long order_no,String userId, int money) {
        System.out.println("扣款开始！！！");
        //if(k%10==0) {++k;throw new RuntimeException("account failure");}
        System.out.println("order_on"+order_no);
        boolean res = false;
        try {
            res = lock.tryLock(100, 10, TimeUnit.SECONDS);
            if (res) {
                try {
                    String s = jedis.get(order_no + userId + money);
                    System.out.println("s:::"+s);
                    if(StringUtils.isNotEmpty(s)) return;
                    Object args[]={money,userId};
                    jdbcTemplate.update("update account_tbl set money=money - ? where user_id=?",args);
                    jedis.set(order_no + userId + money,"1");
                    System.out.println("扣款成功！！！");
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }




    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
