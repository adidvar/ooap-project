package main.com.studentfinance.factory;

import main.com.studentfinance.model.Notification;
import main.com.studentfinance.model.NotificationType;

public class BudgetAlertFactory extends NotificationFactory{
    @Override
    public Notification createNotification(String message, NotificationType type) {
        return new BudgetAlertNotification(message);
    }
}
