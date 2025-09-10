package com.raja.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
//        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        // ConfigurableApplicationContext is used to perform things like pre destroy.
        OrderService orderService = context.getBean(OrderService.class);
        // OrderService orderService2 = context.getBean(OrderService.class);
        // This will create only one instance even though it was initialized twice because it is singleton
        orderService.placeOrder();
        NotificationManager notificationManager = context.getBean(NotificationManager.class);
        notificationManager.sendNotification();
        UserService userService = context.getBean(UserService.class);
        userService.registerUser(new User(123L, "testemail@email.com", "12345", "Raja"));
//        userService.registerUser(new User(123L, "testemail@email.com", "12345", "Raja"));
        context.close();
        // We need to close it if we use ConfigurableApplicationContext
    }

}
