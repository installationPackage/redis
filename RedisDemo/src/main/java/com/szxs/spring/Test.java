package com.szxs.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean(UserService.class);
        String city = userService.getCity();
        System.out.println(city);

        List<String> citys = userService.getCitys();
        for (String c:citys        ) {
            System.out.println(c);
        }
    }
}
