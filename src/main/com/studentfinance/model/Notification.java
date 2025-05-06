package main.com.studentfinance.model;

import java.util.Date;

public class Notification {
    private String id;
    private String message;
    private Date triggerDate;
    private Boolean isDelivered;
    private NotificationType type;

    public void create() {
        // Implementation
    }

    public void send() {
        // Implementation
    }

    public void cancel() {
        // Implementation
    }

    public void snooze(long duration) {
        // Implementation
    }

    // getters, setters, constructors
}