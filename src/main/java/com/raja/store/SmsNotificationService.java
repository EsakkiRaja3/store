package com.raja.store;

public class SmsNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message,  String recipientEmail) {
        System.out.println("SMS Notification Service: " + message);
    }
}
