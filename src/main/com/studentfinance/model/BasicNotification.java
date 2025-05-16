package main.com.studentfinance.model;

import java.util.Date;
import java.util.UUID;

public class BasicNotification implements NotificationComponent {
    protected String id;
    protected String message;
    protected Date triggerDate;
    protected NotificationType type;
    protected NotificationStatus status;
    protected boolean isDelivered;
    protected boolean internetAvailable = true;

    public BasicNotification(String message) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.triggerDate = new Date();
        this.type = NotificationType.INFO;
        this.status = NotificationStatus.PENDING;
        this.isDelivered = false;
    }

    public void setTriggerDate(Date triggerDate) {
        this.triggerDate = triggerDate;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    @Override
    public boolean send() {
        if (!internetAvailable) {
            status = NotificationStatus.FAILED;
            return false;
        }

        System.out.println("Відправка базового сповіщення: " + message);
        isDelivered = true;
        status = NotificationStatus.DELIVERED;
        return true;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Date getTriggerDate() {
        return this.triggerDate;
    }

    public void setInternetAvailable(boolean available) {
        this.internetAvailable = available;
    }
}
