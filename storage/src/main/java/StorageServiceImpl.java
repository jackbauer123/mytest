import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StorageServiceImpl implements StorageService {



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

        // 2. Create Redisson instance

        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);

        // 4. Get Redis based implementation of java.util.concurrent.locks.Lock
        lock = redisson.getLock("deduct");

    }

    //@Transactional
    public void deduct(long order_no,String commodityCode, int count) {
        System.out.println("order_on: "+order_no);
        System.out.println("扣减库存开始！！！");
        boolean res = false;
        try {
            res = lock.tryLock(100, 10, TimeUnit.SECONDS);
            if (res) {
                try {
                    String s = jedis.get(order_no + commodityCode + count);
                    if(StringUtils.isNotEmpty(s)) return;
                    Object args[]={count,commodityCode};
                    jdbcTemplate.update("update storage_tbl set counts=counts - ? where commodity_code=?",args);
                    jedis.set(order_no+commodityCode+count,"1");
                    //throw new RuntimeException("failure");
                    System.out.println("扣减库存成功！！！");
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
