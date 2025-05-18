package main.com.studentfinance.service;

import main.com.studentfinance.implementator.NotificationImplementor;
import main.com.studentfinance.model.NotificationType;

public class ExternalNotificationAdapter implements NotificationImplementor {
    private ExternalNotificationService externalService;

    public ExternalNotificationAdapter(ExternalNotificationService externalService) {
        this.externalService = externalService;
    }

    @Override
    public boolean sendNotification(String message, String recipient, NotificationType type) {
        // Визначаємо пріоритет на основі типу нашого сповіщення
        int priority = convertTypeToPriority(type);

        // Відправляємо через зовнішній сервіс
        try {
            externalService.pushAlert(recipient, message, priority);
            return true;
        } catch (Exception e) {
            System.out.println("Помилка адаптера: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean isDeliveryChannelAvailable() {
        return externalService.checkServiceStatus();
    }

    // Допоміжний метод для конвертації нашого типу сповіщення в формат зовнішнього сервісу
    private int convertTypeToPriority(NotificationType type) {
        switch (type) {
            case ALERT:
                return 1; // Високий пріоритет
            case WARNING:
                return 2; // Середній пріоритет
            case PAYMENT:
                return 2; // Середній пріоритет
            case INFO:
            default:
                return 3; // Низький пріоритет
        }
    }
}
