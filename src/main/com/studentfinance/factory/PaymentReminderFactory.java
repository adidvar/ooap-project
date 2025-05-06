package main.com.studentfinance.factory;

import main.com.studentfinance.model.Notification;
import main.com.studentfinance.model.NotificationType;
import main.com.studentfinance.model.PaymentReminderNotification;

public class PaymentReminderFactory extends NotificationFactory {
    @Override
    public Notification createNotification(String message, NotificationType type) {
        return new PaymentReminderNotification(message);
    }
}
