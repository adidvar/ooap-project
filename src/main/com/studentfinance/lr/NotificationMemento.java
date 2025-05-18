package main.com.studentfinance.lr;

import java.util.Date;

public class NotificationMemento {
    private final String message;
    private final Date triggerDate;
    private final boolean isDelivered;

    public NotificationMemento(String message, Date triggerDate, boolean isDelivered) {
        System.out.println("MEMENTO: Creating memento to store notification state");
        this.message = message;
        this.triggerDate = triggerDate;
        this.isDelivered = isDelivered;
    }

    // Getters for restoring state
    public String getMessage() {
        return message;
    }

    public Date getTriggerDate() {
        return triggerDate;
    }

    public boolean isDelivered() {
        return isDelivered;
    }
}
