package main.com.studentfinance.factory;

import main.com.studentfinance.decorator.BudgetAlertDecorator;
import main.com.studentfinance.decorator.LoggingDecorator;
import main.com.studentfinance.decorator.PaymentReminderDecorator;
import main.com.studentfinance.decorator.RetryDecorator;
import main.com.studentfinance.model.*;

public class EnhancedNotificationFactory {
    public static NotificationComponent createBasicNotification(String message, NotificationType type) {
        BasicNotification notification = new BasicNotification(message);
        notification.setType(type);
        return notification;
    }

    public static NotificationComponent createLoggedNotification(String message, NotificationType type) {
        NotificationComponent basic = createBasicNotification(message, type);
        return new LoggingDecorator(basic);
    }

    public static NotificationComponent createRetryableNotification(
            String message, NotificationType type, int maxRetries, int delayMinutes) {
        NotificationComponent basic = createBasicNotification(message, type);
        return new RetryDecorator(basic, maxRetries, delayMinutes);
    }

    public static NotificationComponent createBudgetAlert(
            String message, String budgetName, double threshold, double currentAmount) {
        NotificationComponent basic = createBasicNotification(message, NotificationType.ALERT);
        return new BudgetAlertDecorator(basic, budgetName, threshold, currentAmount);
    }

    public static NotificationComponent createPaymentReminder(
            String message, Payment payment, int daysBeforeDue) {
        NotificationComponent basic = createBasicNotification(message, NotificationType.PAYMENT);
        return new PaymentReminderDecorator(basic, payment, daysBeforeDue);
    }

    public static NotificationComponent createComprehensivePaymentReminder(
            String message, Payment payment, int daysBeforeDue) {
        // Створюємо базове сповіщення з логуванням та повторними спробами
        NotificationComponent basic = createBasicNotification(message, NotificationType.PAYMENT);
        NotificationComponent logged = new LoggingDecorator(basic);
        NotificationComponent retryable = new RetryDecorator(logged, 3, 60);

        // Додаємо специфіку нагадування про платіж
        return new PaymentReminderDecorator(retryable, payment, daysBeforeDue);
    }

    public static CompositeNotification createGroupedNotifications(String groupName) {
        return new CompositeNotification(groupName);
    }
}
