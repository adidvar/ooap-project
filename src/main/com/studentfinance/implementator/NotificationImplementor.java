package main.com.studentfinance.implementator;

import main.com.studentfinance.model.NotificationType;

public interface NotificationImplementor {
    boolean sendNotification(String message, String recipient, NotificationType type);
    boolean isDeliveryChannelAvailable();
}
