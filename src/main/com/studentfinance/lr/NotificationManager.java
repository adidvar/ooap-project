package main.com.studentfinance.lr;

import main.com.studentfinance.model.Notification;
import main.com.studentfinance.model.Payment;
import main.com.studentfinance.model.Student;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NotificationManager implements NotificationMediator {
    private static NotificationManager instance;
    private final List<PaymentObserver> observers = new ArrayList<>();
    private final NotificationHistory notificationHistory = new NotificationHistory();

    private NotificationManager() {
        System.out.println("MEDIATOR: NotificationManager singleton instance created");
    }

    public static NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

    @Override
    public void registerObserver(PaymentObserver observer) {
        observers.add(observer);
        System.out.println("MEDIATOR: Observer registered with the mediator");
    }

    @Override
    public void schedulePaymentReminders(Payment payment, Student student) {
        System.out.println("MEDIATOR: Mediator scheduling payment reminders for student: " + student.getName());

        // Create first notification (3 days before)
        Calendar cal = Calendar.getInstance();
        cal.setTime(payment.getDueDate());
        cal.add(Calendar.DAY_OF_MONTH, -3);
        Date reminderDate = cal.getTime();

        String message = "Payment reminder: " + payment.getDescription() +
                " for amount: " + payment.getAmount() + " due in 3 days";

        System.out.println("MEDIATOR: Creating early reminder notification");
        Notification notification1 = new Notification(message, reminderDate);
        String notificationId = "notification_" + payment.getId() + "_3days";
        notificationHistory.saveNotification(notificationId, notification1);

        // Create due date notification
        String urgentMessage = "URGENT Payment reminder: " + payment.getDescription() +
                " for amount: " + payment.getAmount() + " due TODAY";

        System.out.println("MEDIATOR: Creating due date notification");
        Notification notification2 = new Notification(urgentMessage, payment.getDueDate());
        String urgentNotificationId = "notification_" + payment.getId() + "_dueDate";
        notificationHistory.saveNotification(urgentNotificationId, notification2);

        System.out.println("MEDIATOR: Payment reminders scheduled successfully");
    }

    @Override
    public void sendNotification(NotificationTemplate notification) {
        System.out.println("MEDIATOR: Mediator delegating notification sending to template method");
        notification.processNotification();
    }

    // Method to check for due dates and send notifications
    public void checkDueDates() {
        System.out.println("MEDIATOR: Mediator checking due dates for scheduled notifications");
        // In a real system, this would query the database for notifications
        // with triggerDates that match the current time

        // For demonstration, let's restore a notification from memento
        System.out.println("MEDIATOR: Demonstration - Restoring a notification from memento");
        Notification restoredNotification = new Notification("Placeholder", new Date());
        notificationHistory.restoreNotification("notification_P001_3days", restoredNotification);
        System.out.println("MEDIATOR: Restored notification: " + restoredNotification);
    }
}
