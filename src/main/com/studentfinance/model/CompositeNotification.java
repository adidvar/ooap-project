package main.com.studentfinance.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompositeNotification implements NotificationComponent {
    private List<NotificationComponent> childNotifications = new ArrayList<>();
    private String groupName;
    private Date triggerDate;

    public CompositeNotification(String groupName) {
        this.groupName = groupName;
        this.triggerDate = new Date();
    }

    public void addNotification(NotificationComponent notification) {
        childNotifications.add(notification);
    }

    public void removeNotification(NotificationComponent notification) {
        childNotifications.remove(notification);
    }

    public void setTriggerDate(Date triggerDate) {
        this.triggerDate = triggerDate;

        // Оновлюємо дату запуску для всіх дочірніх сповіщень
        for (NotificationComponent child : childNotifications) {
            if (child instanceof BasicNotification) {
                ((BasicNotification) child).setTriggerDate(triggerDate);
            } else if (child instanceof CompositeNotification) {
                ((CompositeNotification) child).setTriggerDate(triggerDate);
            }
        }
    }

    @Override
    public boolean send() {
        boolean allSent = true;
        System.out.println("Відправка групи сповіщень: " + groupName);

        for (NotificationComponent notification : childNotifications) {
            boolean sent = notification.send();
            if (!sent) {
                allSent = false;
            }
        }

        return allSent;
    }

    @Override
    public String getMessage() {
        StringBuilder messages = new StringBuilder("Група сповіщень: " + groupName + "\n");

        for (NotificationComponent notification : childNotifications) {
            messages.append("- ").append(notification.getMessage()).append("\n");
        }

        return messages.toString();
    }

    @Override
    public Date getTriggerDate() {
        return this.triggerDate;
    }
}
