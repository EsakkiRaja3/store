package com.raja.store;

public class NotificationManager {
    private final NotificationService notificationService;

    NotificationManager(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotification() {
        notificationService.sendNotification("Hello World", "test@test.com");
    }
}
