package main.com.studentfinance.implementator;

import main.com.studentfinance.model.NotificationType;

public class EmailNotificationImplementor implements NotificationImplementor {
    @Override
    public boolean sendNotification(String message, String recipient, NotificationType type) {
        if (!isDeliveryChannelAvailable()) {
            System.out.println("Email-сервер недоступний. Сповіщення не відправлено.");
            return false;
        }

        System.out.println("Відправка Email до " + recipient + ":");
        System.out.println("Тема: " + type);
        System.out.println("Повідомлення: " + message);
        return true;
    }

    @Override
    public boolean isDeliveryChannelAvailable() {
        // В реальному додатку тут була б перевірка доступності email-сервера
        return true;
    }
}
