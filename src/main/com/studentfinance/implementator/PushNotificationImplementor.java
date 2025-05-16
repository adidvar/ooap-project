package main.com.studentfinance.implementator;

import main.com.studentfinance.model.NotificationType;

public class PushNotificationImplementor implements NotificationImplementor {
    @Override
    public boolean sendNotification(String message, String recipient, NotificationType type) {
        if (!isDeliveryChannelAvailable()) {
            System.out.println("Push-канал недоступний. Сповіщення не відправлено.");
            return false;
        }

        System.out.println("Відправка Push-сповіщення до " + recipient + ":");
        System.out.println("Тип: " + type);
        System.out.println("Повідомлення: " + message);
        return true;
    }

    @Override
    public boolean isDeliveryChannelAvailable() {
        // В реальному додатку тут була б перевірка доступності сервісу Push-нотифікацій
        return true;
    }
}
