package main.com.studentfinance.service;

import main.com.studentfinance.implementator.NotificationImplementor;
import main.com.studentfinance.model.NotificationType;

public class LegacyNotificationAdapter implements NotificationImplementor {
    private class LegacyMessenger {
        public void sendMsg(String phone, String text) {
            System.out.println("Стара система: Відправка на номер " + phone);
            System.out.println("Повідомлення: " + text);
        }

        public boolean isSystemUp() {
            return true;
        }
    }

    private LegacyMessenger legacyMessenger;

    public LegacyNotificationAdapter() {
        this.legacyMessenger = new LegacyMessenger();
    }

    @Override
    public boolean sendNotification(String message, String recipient, NotificationType type) {
        // Додаємо префікс типу до повідомлення
        String formattedMessage = "[" + type.toString() + "] " + message;

        try {
            legacyMessenger.sendMsg(recipient, formattedMessage);
            return true;
        } catch (Exception e) {
            System.out.println("Помилка в старій системі: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean isDeliveryChannelAvailable() {
        return legacyMessenger.isSystemUp();
    }
}
