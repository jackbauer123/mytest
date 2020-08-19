import com.baidu.fsg.uid.UidGenerator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Consumer {

    public static void main(String[] args) throws Exception {
        //测试常规服务
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        int second = LocalTime.now().getSecond();
        System.out.println("consumer start:"+ second);
        UidGenerator uidGenerator = (UidGenerator)context.getBean("uidGenerator");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        BusinessService businessService = (BusinessService)context.getBean("businessService");
        for (int i = 0; i < 100; i++) {
             executorService.execute(()->businessService.purchase(uidGenerator.getUID(),"jack","001",1));
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("consumer end:"+(LocalTime.now().getSecond()-second));

    }

}
