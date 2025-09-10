package com.raja.store;

public class ChatNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message,  String recipientEmail) {
        System.out.println("Chat Notification Service: " + message);
    }
}
