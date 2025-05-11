package main.com.studentfinance.service;

import main.com.studentfinance.factory.BudgetAlertFactory;
import main.com.studentfinance.factory.NotificationFactory;
import main.com.studentfinance.factory.PaymentReminderFactory;
import main.com.studentfinance.model.Notification;
import main.com.studentfinance.model.NotificationType;
import main.com.studentfinance.model.Payment;
import main.com.studentfinance.model.Student;

import java.util.Calendar;
import java.util.Date;

public class NotificationManager {
    private static NotificationManager instance;
    private final NotificationFactory paymentReminderFactory;
    private final NotificationFactory budgetAlertFactory;

    private NotificationManager() {
        this.paymentReminderFactory = new PaymentReminderFactory();
        this.budgetAlertFactory = new BudgetAlertFactory();
    }

    public static synchronized NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

    public void scheduleNotification(Payment payment) {
        if (payment == null || payment.getDueDate() == null) {
            System.out.println("Cannot schedule notification: invalid payment data");
            return;
        }

        try {
            // Create first notification (3 days before due date)
            Calendar cal = Calendar.getInstance();
            cal.setTime(payment.getDueDate());
            cal.add(Calendar.DAY_OF_MONTH, -3);
            Date reminderDate = cal.getTime();

            String reminderMessage = String.format(
                    "Reminder: Payment of %.2f due in 3 days for '%s'",
                    payment.getAmount(),
                    payment.getDescription()
            );

            main.com.studentfinance.model.Notification earlyReminder = paymentReminderFactory.prepareNotification(
                    reminderMessage,
                    NotificationType.PAYMENT_REMINDER
            );
            earlyReminder.schedule(reminderDate);
            payment.addNotification(earlyReminder);

            // Create second notification (on due date)
            String dueMessage = String.format(
                    "Payment due today: %.2f for '%s'",
                    payment.getAmount(),
                    payment.getDescription()
            );

            main.com.studentfinance.model.Notification dueReminder = paymentReminderFactory.prepareNotification(
                    dueMessage,
                    NotificationType.PAYMENT_REMINDER
            );
            dueReminder.schedule(payment.getDueDate());
            payment.addNotification(dueReminder);

            System.out.println("Notifications scheduled successfully for payment: " + payment.getDescription());
        } catch (Exception e) {
            System.out.println("Error scheduling notifications: " + e.getMessage());
        }
    }

    public void checkDueDates() {
        System.out.println("Checking due dates for scheduled notifications...");
        // Implementation would include checking database for notifications with trigger dates
    }

    public boolean sendNotification(Notification notification) {
        if (notification == null) {
            return false;
        }

        boolean success = notification.send();

        if (!success) {
            // Schedule retry in 1 hour (exception handling as per use case)
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 1);
            notification.schedule(cal.getTime());
            notification.retry();
        }

        return success;
    }

    public void sendPaymentReminder(Student student) {
        if (student == null) {
            return;
        }

        System.out.println("Sending payment reminders to student: " + student.getName());
        // This would typically pull payments from the student and send reminders
    }

    // Additional methods for user preferences would be here
    public void getUserPreferences() {
        // Implementation for getting user notification preferences
    }

    public void setUserPreferences() {
        // Implementation for setting user notification preferences
    }
}