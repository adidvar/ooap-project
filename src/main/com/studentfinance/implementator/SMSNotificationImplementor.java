package main.com.studentfinance.implementator;

import main.com.studentfinance.model.NotificationType;

public class SMSNotificationImplementor implements NotificationImplementor {
    @Override
    public boolean sendNotification(String message, String recipient, NotificationType type) {
        if (!isDeliveryChannelAvailable()) {
            System.out.println("SMS-шлюз недоступний. Сповіщення не відправлено.");
            return false;
        }

        System.out.println("Відправка SMS до " + recipient + ":");
        System.out.println("Повідомлення: " + type + ": " + message);
        return true;
    }

    @Override
    public boolean isDeliveryChannelAvailable() {
        // В реальному додатку тут була б перевірка доступності SMS-шлюзу
        return true;
    }
}
