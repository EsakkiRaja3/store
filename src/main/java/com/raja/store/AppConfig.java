package com.raja.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Value("${notification.serviceName}")
    private String serviceName;

    @Bean
    public NotificationService sms() {
        return new SmsNotificationService();
    }

    @Bean
    public NotificationService chat() {
        return new ChatNotificationService();
    }

    @Bean
    public NotificationService notificationService() {
        if(serviceName.equals("chat")) {
            return chat();
        }
        return sms();
    }

    @Bean
    @Scope("prototype") // This wil create new instances whenever it is called
    // @Scope("request") // This will create a bean only when the http request is being called
    // @Scope("session") // Bean get created for each http session.
    public NotificationManager notificationManager(NotificationService notificationService) {
        return new NotificationManager(notificationService);
    }
}
