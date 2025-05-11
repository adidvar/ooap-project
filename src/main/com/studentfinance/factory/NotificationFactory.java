package main.com.studentfinance.factory;

import main.com.studentfinance.model.Notification;
import main.com.studentfinance.model.NotificationType;

public abstract class NotificationFactory {
    public abstract Notification createNotification(String message, NotificationType type);

    public Notification prepareNotification(String message, NotificationType type) {
        Notification notification = createNotification(message, type);
        notification.create();
        return notification;
    }
}
