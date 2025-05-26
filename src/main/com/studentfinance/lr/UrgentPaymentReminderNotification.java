package main.com.studentfinance.lr;

import java.util.Date;

public class UrgentPaymentReminderNotification extends NotificationTemplate {
    private String recipient;
    private String message;
    private Date dueDate;

    public UrgentPaymentReminderNotification(String recipient, String message, Date dueDate) {
        this.recipient = recipient;
        this.message = message;
        this.dueDate = dueDate;
    }

    @Override
    protected void prepareNotification() {
        System.out.println("TEMPLATE METHOD: Subclass implementation - Preparing URGENT payment reminder for: " + recipient);
    }

    @Override
    protected boolean sendNotification() {
        System.out.println("TEMPLATE METHOD: Subclass implementation - URGENT! Sending payment reminder: \"" + message + "\" for due date: " + dueDate);
        // Simulating successful notification delivery
        return true;
    }

    @Override
    protected void logNotification(boolean success) {
        System.out.println("TEMPLATE METHOD: Overridden common step - URGENT notification " +
                (success ? "sent successfully" : "failed to send") +
                " at " + new Date() + " - Requires acknowledgment");
    }
}
