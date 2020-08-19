import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class UigGeneratorProvider {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:uid/mybatis-spring.xml",
                "classpath:provider.xml"});
        System.out.println(context.getDisplayName() + ": here");
        context.start();
        System.out.println("服务已经启动...");
        System.in.read();
    }
}
