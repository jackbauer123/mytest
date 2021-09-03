package com.account;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class AccountProvider {

        public static void main(String[] args) throws IOException {

            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:provider.xml","jdbc.xml"});
            System.out.println(context.getDisplayName() + ": here");
            context.start();
            System.out.println("服务已经启动...");
            System.in.read();
        }

}
