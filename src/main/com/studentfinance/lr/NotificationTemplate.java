package main.com.studentfinance.lr;

import java.util.Date;

public abstract class NotificationTemplate {
    public final boolean processNotification() {
        System.out.println("TEMPLATE METHOD: Executing notification process steps in order");
        prepareNotification();
        boolean result = sendNotification();
        logNotification(result);
        return result;
    }

    // Steps to be implemented by subclasses
    protected abstract void prepareNotification();
    protected abstract boolean sendNotification();

    // Common step with default implementation
    protected void logNotification(boolean success) {
        System.out.println("TEMPLATE METHOD: Common step - Logging notification status: " +
                (success ? "sent successfully" : "failed to send") +
                " at " + new Date());
    }
}
