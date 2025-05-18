package main.com.studentfinance.factory;

import main.com.studentfinance.implementator.*;
import main.com.studentfinance.model.NotificationType;
import main.com.studentfinance.model.Payment;
import main.com.studentfinance.service.ExternalNotificationAdapter;
import main.com.studentfinance.service.LegacyNotificationAdapter;
import main.com.studentfinance.service.ThirdPartyNotificationService;

public class BridgedNotificationFactory {
    // Створення базового сповіщення з різними реалізаціями
    public static NotificationAbstraction createBasicNotification(String message,
                                                                  String recipient,
                                                                  NotificationType type,
                                                                  String implementationType) {
        NotificationImplementor implementor = getImplementor(implementationType);
        return new BasicNotificationAbstraction(implementor, message, recipient, type);
    }

    // Створення сповіщення про оплату з різними реалізаціями
    public static NotificationAbstraction createPaymentNotification(Payment payment,
                                                                    String recipient,
                                                                    String implementationType) {
        NotificationImplementor implementor = getImplementor(implementationType);
        return new PaymentNotificationAbstraction(implementor, payment, recipient);
    }

    // Допоміжний метод для отримання реалізації
    private static NotificationImplementor getImplementor(String type) {
        switch (type.toLowerCase()) {
            case "push":
                return new PushNotificationImplementor();
            case "email":
                return new EmailNotificationImplementor();
            case "sms":
                return new SMSNotificationImplementor();
            case "external":
                return new ExternalNotificationAdapter(new ThirdPartyNotificationService());
            case "legacy":
                return new LegacyNotificationAdapter();
            default:
                return new PushNotificationImplementor(); // За замовчуванням
        }
    }
}
