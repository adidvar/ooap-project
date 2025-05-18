package main.com.studentfinance.lr;

import java.util.Date;

public class PaymentReminderNotification extends NotificationTemplate {
    private String recipient;
    private String message;
    private Date dueDate;

    public PaymentReminderNotification(String recipient, String message, Date dueDate) {
        this.recipient = recipient;
        this.message = message;
        this.dueDate = dueDate;
    }

    @Override
    protected void prepareNotification() {
        System.out.println("TEMPLATE METHOD: Subclass implementation - Preparing standard payment reminder for: " + recipient);
    }

    @Override
    protected boolean sendNotification() {
        System.out.println("TEMPLATE METHOD: Subclass implementation - Sending payment reminder: \"" + message + "\" for due date: " + dueDate);
        // Simulating successful notification delivery
        return true;
    }
}
