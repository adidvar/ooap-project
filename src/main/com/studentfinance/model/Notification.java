package main.com.studentfinance.model;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public abstract class Notification {
    protected String id;
    protected String message;
    protected Date triggerDate;
    protected boolean isDelivered;
    protected NotificationType type;
    protected NotificationStatus status;

    public Notification(String message) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.isDelivered = false;
        this.status = NotificationStatus.CREATED;
    }

    public void create() {
        this.status = NotificationStatus.CREATED;
        System.out.println("Notification created: " + this.message);
    }

    public boolean send() {
        try {
            // Simulating network operation
            if (!isInternetAvailable()) {
                this.status = NotificationStatus.FAILED;
                return false;
            }

            System.out.println("Sending notification: " + this.message);
            this.isDelivered = true;
            this.status = NotificationStatus.SENT;
            return true;
        } catch (Exception e) {
            this.status = NotificationStatus.FAILED;
            return false;
        }
    }

    private boolean isInternetAvailable() {
        // Simulation of internet connection check
        return Math.random() > 0.2; // 80% chance of success
    }

    public void cancel() {
        if (this.status != NotificationStatus.SENT &&
                this.status != NotificationStatus.ARCHIVED) {
            this.status = NotificationStatus.CANCELED;
            System.out.println("Notification canceled: " + this.message);
        }
    }

    public void schedule(Date triggerDate) {
        this.triggerDate = triggerDate;
        this.status = NotificationStatus.SCHEDULED;
        System.out.println("Notification scheduled for: " + triggerDate);
    }

    public void retry() {
        if (this.status == NotificationStatus.FAILED) {
            this.status = NotificationStatus.RETRY_SCHEDULED;
            System.out.println("Notification retry scheduled");
        }
    }

    public NotificationStatus getStatus() {
        return this.status;
    }

    public void snooze(Duration duration) {
        if (this.status == NotificationStatus.SCHEDULED) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.triggerDate);
            calendar.add(Calendar.SECOND, (int) duration.getSeconds());
            this.triggerDate = calendar.getTime();
            this.status = NotificationStatus.SNOOZED;
            System.out.println("Notification snoozed until: " + this.triggerDate);
        }
    }

    public void archive() {
        if (this.status == NotificationStatus.SENT || this.status == NotificationStatus.FAILED) {
            this.status = NotificationStatus.ARCHIVED;
            System.out.println("Notification archived");
        }
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getTriggerDate() {
        return triggerDate;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public NotificationType getType() {
        return type;
    }
}