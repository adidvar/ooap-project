package main.com.studentfinance.service;

import main.com.studentfinance.model.Payment;

import javax.management.Notification;
import java.util.List;
import java.util.Map;

public class NotificationManager {
    private List<Notification> notifications;

    public static NotificationManager getInstance() {
        return null;
    }

    public void scheduleNotification(Payment payment) {
        // Implementation
    }

    public void checkDueDates() {
        // Implementation
    }

    public void sendNotification(Notification notification) {
        // Implementation
    }

    public Map<String, Object> getUserPreferences() {
        // Implementation
        return null;
    }

    public void setUserPreferences(Map<String, Object> preferences) {
        // Implementation
    }

    // Additional methods, dependencies injection, etc.
}